package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.spacecruiser.game.SpaceCruiser;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 *  Represents the initial splash screen
 */

public class Splash extends ScreenAdapter{

    /**
     *  The game this screen belongs to.
     */
    private SpaceCruiser game;

    /**
     *  The splash image.
     */
    private Image splashActor;

    /**
     *  The stage that is drawn.
     */
    private Stage stage;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public Splash(SpaceCruiser game){
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

        stage.act();
        stage.draw();
    }

    /**
     * Called when this screen becomes the current screen for the game.
     */
    @Override
    public void show(){

        stage = new Stage();

        splashActor = new Image(new TextureRegion(new Texture(Gdx.files.internal("images/splashScreen.png"))));
        splashActor.setFillParent(true);


        SequenceAction actions = new SequenceAction(sequence(fadeIn(2f), delay(1.5f),
                run(new Runnable() {
                    @Override
                    public void run() {
                        loadAssets();
                    }
                }),
                fadeOut(2f),
                run(new Runnable(){
                     @Override
                    public void run(){
                         game.getScreenManager().drawScreen(ScreenManager.ActiveScreen.MENU);
                    }
        })));

        splashActor.addAction(actions);
        stage.addActor(splashActor);
    }

    /**
     *  Loads all the game assets using the game asset manager.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "images/spaceship-no-thrust.png" , Texture.class);
        this.game.getAssetManager().load( "images/spaceship-thrust.png" , Texture.class);

        this.game.getAssetManager().load( "images/asteroid-big.png" , Texture.class);
        this.game.getAssetManager().load( "images/asteroid-medium.png" , Texture.class);

        this.game.getAssetManager().load( "images/background.png" , Texture.class);
        this.game.getAssetManager().load( "images/settingsBackground.jpg" , Texture.class);


        this.game.getAssetManager().load( "images/bonus-shield.png" , Texture.class);
        this.game.getAssetManager().load( "images/bonus-points.png" , Texture.class);


        this.game.getAssetManager().load( "images/GreenButton-Active.png" , Texture.class);
        this.game.getAssetManager().load( "images/GreenButton-Hover.png" , Texture.class);

        this.game.getAssetManager().load( "images/YellowButton-Active.png" , Texture.class);
        this.game.getAssetManager().load( "images/YellowButton-Hover.png" , Texture.class);

        this.game.getAssetManager().load( "images/RedButton-Active.png" , Texture.class);
        this.game.getAssetManager().load( "images/RedButton-Hover.png" , Texture.class);

        this.game.getAssetManager().load( "images/BlackButton-Active.png" , Texture.class);
        this.game.getAssetManager().load( "images/BlackButton-Hover.png" , Texture.class);

        this.game.getAssetManager().load( "images/spaceship-thrust-shielded.png" , Texture.class);

        this.game.getAssetManager().load( "images/main-menu.png" , Texture.class);

        this.game.getAssetManager().load( "skins/uiskin.png" , Texture.class);
        this.game.getAssetManager().load( "skins/uiskin.atlas" , TextureAtlas.class);

        this.game.getAssetManager().load( "images/gameoverBackground.jpg" , Texture.class);
        this.game.getAssetManager().load( "images/helpmenu.jpg" , Texture.class);


        this.game.getAssetManager().finishLoading();
    }

}
