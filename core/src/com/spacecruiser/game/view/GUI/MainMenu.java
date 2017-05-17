package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.GameModel;

import static com.spacecruiser.game.controller.GameController.ARENA_HEIGHT;

/**
 * A view for the main menu.
 */

public class MainMenu extends ScreenAdapter {

    /**
     *  The default width for this screen buttons.
     */
    private final static float BTN_WIDTH = 100;

    /**
     *  The default height for this screen buttons.
     */
    private final static float BTN_HEIGHT = 100;

    /**
     *  The game this screen belongs to.
     */
    private SpaceCruiser game;

    /**
     *  The stage that manages inputs and is drawn.
     */
    private Stage stage;

    /**
     *  The table that contains the buttons.
     */
    private Table table;

    /**
     *  The main menu buttons.
     */
    private ImageButton playBtn, settingsBtn, exitBtn;


    private Camera camera;


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public MainMenu(SpaceCruiser game){
        this.game = game;
        this.stage = new Stage();
        this.table = new Table();
        table.setFillParent(true);
        createCamera();
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        Gdx.input.setInputProcessor(stage);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        drawBackground();
        game.getBatch().end();

        stage.act();
        stage.draw();
    }




    @Override
    public void show(){

        createPlayBtn();
        table.add(playBtn).size(BTN_WIDTH,BTN_HEIGHT);
        table.row();

        createSettingsBtn();
        table.add(settingsBtn).size(BTN_WIDTH,BTN_HEIGHT);
        table.row();

        createExitBtn();
        table.add(exitBtn).size(BTN_WIDTH,BTN_HEIGHT);

        stage.addActor(table);
    }


    private void createCamera() {
        OrthographicCamera camera = new OrthographicCamera(GameView.VIEWPORT_WIDTH / GameView.PIXEL_TO_METER,
                GameView.VIEWPORT_WIDTH / GameView.PIXEL_TO_METER * ((float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));

        camera.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);

        camera.update();

        this.camera = camera;
    }

    public void createPlayBtn(){
        playBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/GreenButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/GreenButton-Hover.png"))));

        playBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                startGame();
            }
        });
    }

    public void createSettingsBtn(){
        settingsBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/YellowButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/YellowButton-Hover.png"))));

        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new SettingsMenu(game));
            }
        });
    }

    public void createExitBtn(){
        exitBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/RedButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/RedButton-Hover.png"))));

        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        if(game.getGameScreen() == null){
            GameModel model = new GameModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2, 100, 100);
            GameView gameView = new GameView(game, model, new GameController(model));
            game.setGameScreen(gameView);
        }

        game.setScreen(game.getGameScreen());
    }

    /**
     * Draws the background.
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("images/main-menu.png", Texture.class);
        game.getBatch().draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }
}
