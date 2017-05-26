package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.spacecruiser.game.SpaceCruiser;

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
     *  The main menu buttons.
     */
    private ImageButton playBtn, settingsBtn, exitBtn;


    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public MainMenu(SpaceCruiser game){
        this.game = game;
    }

    /**
     * Renders this screen.
     *
     * @param delta time since last renders in seconds.
     */
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        Gdx.input.setInputProcessor(stage);
        stage.getCamera().update();
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);

        game.getBatch().begin();
        drawBackground();
        game.getBatch().end();

        stage.act();
        stage.draw();
    }


    @Override
    public void show(){
        createStage();
    }

    @Override
    public void resize(int width, int height){
        stage.getViewport().update(width,height);
    }

    /**
     *  Creates this menu stage.
     */
    public void createStage(){
        stage = new Stage(new FitViewport(SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH,new OrthographicCamera()));
        Table table = new Table();
        table.setFillParent(true);

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



    /**
     *  Creates the play button.
     */
    public void createPlayBtn(){
        playBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/GreenButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/GreenButton-Hover.png"))));

        playBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.getScreenManager().drawScreen(ScreenManager.ActiveScreen.GAME);
                //game.getScreenManager().drawScreen(ScreenManager.ActiveScreen.GAMEOVER);
            }
        });
    }

    /**
     *  Creates the settings button.
     */
    public void createSettingsBtn(){
        settingsBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/YellowButton-Active.png"))),
                new TextureRegionDrawable(new TextureRegion((Texture)game.getAssetManager().get("images/YellowButton-Hover.png"))));

        settingsBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.getScreenManager().drawScreen(ScreenManager.ActiveScreen.SETTINGS);
            }
        });
    }

    /**
     *  Creates the exit button
     */
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
     * Draws the background.
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("images/main-menu.png", Texture.class);
        game.getBatch().draw(background,0,0,SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH);
    }
}
