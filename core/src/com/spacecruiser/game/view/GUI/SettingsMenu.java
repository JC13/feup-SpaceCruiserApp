package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.spacecruiser.game.SpaceCruiser;


/**
 *  The HUD to change settings.
 */

public class SettingsMenu extends ScreenAdapter{

    /**
     *  Default slider width.
     */
    private final static float SLIDER_WIDTH = 300;

    /**
     *  Default slider height.
     */
    private final static float SLIDER_HEIGHT = 100;

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
     *  The volume slider.
     */
    private Slider musicVolume;

    /**
     *  The back button.
     */
    private ImageButton backBtn;


    public SettingsMenu(SpaceCruiser game){
        this.game = game;
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
        stage.getCamera().update();
        game.getBatch().setProjectionMatrix(stage.getCamera().combined);

        game.getBatch().begin();
        drawBackground();
        game.getBatch().end();

        stage.act();
        stage.draw();

        game.getMusicPlayer().setVolume(musicVolume.getValue());
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
     * Creates the stage in which the elements table is put and adds this elements to it.
     */
    public void createStage(){
        this.stage = new Stage(new FillViewport(SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH,new OrthographicCamera()));
        Table table = new Table();
        table.setFillParent(true);

        createVolumeSlider();
        table.add(musicVolume).size(SLIDER_WIDTH,SLIDER_HEIGHT);

        table.row();

        createBackBtn();
        table.add(backBtn).size(BTN_WIDTH,BTN_HEIGHT);

        stage.addActor(table);
    }


    /**
     * Creates the back button with the respective image to be shown on the screen and
     * sets its action when clicked.
     */
    public void createBackBtn(){
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
     * Creates the volume slider.
     */
    public void createVolumeSlider(){
        Skin sliderSkin = new Skin(Gdx.files.internal("skins/uiskin.json"), (TextureAtlas)game.getAssetManager().get("skins/uiskin.atlas"));
        sliderSkin.add("uiskin", game.getAssetManager().get("skins/uiskin.png"));
        this.musicVolume = new Slider(0f,1f,1/10000f,false, sliderSkin);
        this.musicVolume.setValue(game.getMusicPlayer().getVolume());
    }


    /**
     *  Draws the settings menu background.
     */
    public void drawBackground() {
        Texture background = game.getAssetManager().get("images/settingsBackground.jpg", Texture.class);
        game.getBatch().draw(background,0,0,SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH);
    }




}
