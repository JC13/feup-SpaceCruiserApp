package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.model.GameModel;

//import sun.jvm.hotspot.memory.Space;

/**
 * Game over screen to save score, points and shields picked and asteroids destroyed.
 */

public class GameOver extends ScreenAdapter {

    private final static float BTN_WIDTH = 100;
    private final static float BTN_HEIGHT = 100;

    /**
     * The current game.
     */
    private SpaceCruiser game;

    /**
     * The game with its elements put in position and their info.
     */
    private GameModel model;

    /**
     * Lables to the respective info to be shown on this screen.
     */
    private Label scoreLbl, scoreInfo, asteroidLbl, asteroidInfo, shieldsLbl, shieldsInfo, ptsLbl, ptsInfo;

    /**
     * The stage in which the table with the elements of this screen are added.
     */
    private Stage stage;

    /**
     * The "back to Main Menu" button.
     */
    private ImageButton backBtn;


    /**
     * Constructor of the GameOver screen, which creates the labels and sets their own settings.
     * @param game  The game that ended.
     * @param model The model game with the elements in their position and it respective info.
     */
    public GameOver(SpaceCruiser game, GameModel model){
        this.game = game;
        this.model = model;

        scoreLbl = new Label("SCORE",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreInfo = new Label(String.format("%04d",(int)model.getScore()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLbl.setFontScale(2,2);     scoreInfo.setFontScale(2,2);

        asteroidLbl = new Label("ASTEROIDS DESTROYED",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        asteroidInfo = new Label(String.format("%04d",model.getAsteroidsDestroyed()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        asteroidLbl.setFontScale(2,2);     asteroidInfo.setFontScale(2,2);

        shieldsLbl = new Label("SHIELDS PICKED UP",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        shieldsInfo = new Label(String.format("%04d", model.getShieldsPicked()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        shieldsLbl.setFontScale(2,2);     shieldsInfo.setFontScale(2,2);

        ptsLbl = new Label("POINTS PICKED UP",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ptsInfo = new Label(String.format("%04d",model.getPtsPicked()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ptsLbl.setFontScale(2,2);     ptsInfo.setFontScale(2,2);

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage.getCamera().update();
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);

        game.getBatch().begin();
        drawbackground();
        game.getBatch().end();

        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width,height);
    }

    @Override
    public void show(){
        this.stage = new Stage(new FitViewport(SpaceCruiser.MENU_VIEWPORT_WIDTH, SpaceCruiser.MENU_VIEWPORT_HEIGTH, new OrthographicCamera()));

        Table table = new Table();
        table.setFillParent(true);
        table.padLeft(Align.center-150);
        table.padRight(Align.center);
        table.padTop(Align.center+400);

        table.add(scoreLbl).expandX();
        table.add(scoreInfo).expandX();
        table.row();


        table.add(asteroidLbl).expandX();
        table.add(asteroidInfo).expandX();
        table.row();

        table.add(shieldsLbl).expandX();
        table.add(shieldsInfo).expandX();
        table.row();

        table.add(ptsLbl).expandX();
        table.add(ptsInfo).expandX();
        table.row();

        createBackBtn();
        table.add(backBtn).size(BTN_WIDTH,BTN_HEIGHT).padRight(-Gdx.graphics.getWidth());

        //table.padTop(SCRN_SET_TOP).padLeft(SCRN_SET_LEFT);


        stage.addActor(table);
    }

    /**
     * Creates the back button with the respective image to be shown on the screen and
     * sets its action when clicked.
     */
    private void createBackBtn(){
        backBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/BlackButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/BlackButton-Hover.png"))));

        backBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.getScreenManager().drawScreen(ScreenManager.ActiveScreen.MENU);
            }
        });
    }

    /**
     * Draws the GameOver background with a specific image.
     */
    private void drawbackground(){
        Texture background = game.getAssetManager().get("images/gameOverScreen.png");
        game.getBatch().draw(background,0,0,SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH);
    }
}
