package com.spacecruiser.game.view.GUI;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.GameModel;
import com.spacecruiser.game.model.entities.AsteroidModel;
import com.spacecruiser.game.model.entities.BonusModel;
import com.spacecruiser.game.view.entities.BigAsteroidView;
import com.spacecruiser.game.view.entities.MediumAsteroidView;
import com.spacecruiser.game.view.entities.PointsView;
import com.spacecruiser.game.view.entities.ShieldView;
import com.spacecruiser.game.view.entities.ShipView;

import java.util.List;

import static com.spacecruiser.game.controller.GameController.ARENA_HEIGHT;
import static com.spacecruiser.game.controller.GameController.ARENA_WIDTH;


/**
 * A view representing the game screen. Draws all the other views and
 * controls the camera.
 */
public class GameView extends ScreenAdapter {


    private static final float BTN_WIDTH = 100;

    private static final float BTN_HEIGHT = 100;

    /**
     * Used to debug the position of the physics fixtures
     */
    private static final boolean DEBUG_PHYSICS = false;

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    public static final float VIEWPORT_WIDTH = 20;

    /**
     *  Adjust camera focus, shifting spaceship in the game view
     */
    private static final float DISTANCE_TO_SPACESHIP = 3;

    /**
     * The game this screen belongs to.
     */
    private final SpaceCruiser game;

    /**
     * The model drawn by this screen.
     */
    private final GameModel model;

    /**
     * The physics controller for this game.
     */
    private final GameController controller;

    /**
     * The camera used to show the viewport.
     */
    private final OrthographicCamera camera;

    /**
     * A ship view used to draw ships.
     */
    private final ShipView shipView;

    /**
     * A big asteroid view used to draw big asteroids.
     */
    private final BigAsteroidView bigAsteroidView;

    /**
     * A medium asteroid view used to draw medium asteroids.
     */
    private final MediumAsteroidView mediumAsteroidView;

    /**
     * A shield view to draw the bonus shield.
     */
    private final ShieldView bonusShieldView;

    /**
     * Extra points bonus view to draw.
     */
    private final PointsView bonusPointsView;

    /**
     * A renderer used to debug the physical fixtures.
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugCamera;

    private ImageButton backBtn;

    private Table table;

    private Stage stage;

    private GameHUD hud;


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     * @param model The model to be drawn
     * @param controller The physics controller
     */
    public GameView(SpaceCruiser game, GameModel model, GameController controller) {
        this.game = game;
        this.model = model;
        this.controller = controller;
        this.hud = new GameHUD();
        this.stage = new Stage();
        this.table = new Table();
        table.setFillParent(true);


        shipView = new ShipView(game);
        bigAsteroidView = new BigAsteroidView(game);
        mediumAsteroidView = new MediumAsteroidView(game);
        bonusShieldView = new ShieldView(game);
        bonusPointsView = new PointsView(game);

        createBackBtn();
        table.add(backBtn).size(BTN_WIDTH,BTN_HEIGHT);
        stage.addActor(table);

        camera = createCamera();
    }

    /**
     * Creates the camera used to show the viewport.
     *
     * @return the camera
     */
    private OrthographicCamera createCamera() {
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH / PIXEL_TO_METER, VIEWPORT_WIDTH / PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        if (DEBUG_PHYSICS) {
            debugRenderer = new Box2DDebugRenderer();
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
        }

        return camera;
    }


    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta) {
        handleInputs(delta);

        controller.update(delta);
        controller.increaseScore(delta,model);

        camera.position.set(model.getShip().getX() / PIXEL_TO_METER,
                            (model.getShip().getY() + DISTANCE_TO_SPACESHIP + ARENA_HEIGHT*PIXEL_TO_METER) / PIXEL_TO_METER,
                            0);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawBackground();
        drawEntities();
        game.getBatch().end();

        if (DEBUG_PHYSICS) {
            debugCamera = camera.combined.cpy();
            debugCamera.scl(1 / PIXEL_TO_METER);
            debugRenderer.render(controller.getWorld(), debugCamera);
        }

        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();

        System.out.println("Current score: " + model.getScore()); // TEST TO SEE SCORE
        hud.setScore(model.getScore());
        hud.getStage().act();
        hud.getStage().draw();

    }



    /**
     * Handles any inputs and passes them to the controller.
     *
     * @param delta time since last time inputs where handled in seconds
     */
    private void handleInputs(float delta) {

        //desktop inputs
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            controller.rotateLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            controller.rotateRight(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            controller.accelerate(delta);
        }
    
        //mobile inputs
        if(Gdx.input.isTouched()){
            if(Gdx.input.getX() < Gdx.graphics.getWidth()/2)
                controller.rotateLeft(delta);
            else
                controller.rotateRight(delta);
        }
    }

    public void createBackBtn(){
        backBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/BlackButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/BlackButton-Hover.png"))));

        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new MainMenu(game));
            }
        });
    }


    /**
     * Draws the entities to the screen.
     */
    private void drawEntities() {
        List<AsteroidModel> asteroids = model.getAsteroids();
        for (AsteroidModel asteroid : asteroids) {
            if (asteroid.getSize() == AsteroidModel.AsteroidSize.BIG) {
                bigAsteroidView.update(asteroid);
                bigAsteroidView.draw(game.getBatch());
            } else if (asteroid.getSize() == AsteroidModel.AsteroidSize.MEDIUM) {
                mediumAsteroidView.update(asteroid);
                mediumAsteroidView.draw(game.getBatch());
            }
        }

        List<BonusModel> bonus = model.getBonus();
        for (BonusModel b : bonus){
            if (b.getType() == BonusModel.BonusType.SHIELD) {
                bonusShieldView.update(b);
                bonusShieldView.draw(game.getBatch());
            }

            if (b.getType() == BonusModel.BonusType.POINTS){
                bonusPointsView.update(b);
                bonusPointsView.draw(game.getBatch());
            }
        }

        shipView.update(model.getShip());
        shipView.draw(game.getBatch());
    }

    /**
     * Draws the background.
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("images/background.png", Texture.class);
        background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(background, 0, 0, 0, 0, (int)(ARENA_WIDTH / PIXEL_TO_METER), (int) (ARENA_HEIGHT / PIXEL_TO_METER));
    }

    public int getScore(){
        return this.model.getScore();
    }
}