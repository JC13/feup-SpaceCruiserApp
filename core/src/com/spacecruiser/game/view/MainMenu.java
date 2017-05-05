package com.spacecruiser.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.spacecruiser.game.SpaceCruiser;

/**
 * A view for the main menu
 */

public class MainMenu extends ScreenAdapter {


    private static final float DEF_BUTTON_WIDTH = 100;
    private static final float DEF_BUTTON_HEIGHT = 75;

    private static final float PLAY_BTN_Y = 200;
    private static final float EXIT_BTN_Y = 100;


    private SpaceCruiser game;


    public MainMenu(SpaceCruiser game){

        this.game = game;

        loadAssets();
    }


    @Override
    public void render(float delta){

        handleInputs();

        Gdx.gl.glClearColor( 103/255f, 69/255f, 117/255f, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();

        drawBackground();
        game.getBatch().draw((Texture)game.getAssetManager().get("exit-button.png"),
                                    Gdx.graphics.getWidth() / 2 - DEF_BUTTON_WIDTH / 2,
                                    EXIT_BTN_Y,
                                    DEF_BUTTON_WIDTH, DEF_BUTTON_HEIGHT);

        game.getBatch().draw((Texture)game.getAssetManager().get("play-button.jpg"),
                                Gdx.graphics.getWidth() / 2 - DEF_BUTTON_WIDTH/2,
                                PLAY_BTN_Y,
                                DEF_BUTTON_WIDTH, DEF_BUTTON_HEIGHT);

        game.getBatch().end();

    }

    private void handleInputs(){

        isPlayPressed();
        isExitPressed();

    }

    private void isPlayPressed(){

        int x = (int) (Gdx.graphics.getWidth() / 2 - DEF_BUTTON_WIDTH / 2);
        int y =  Gdx.graphics.getHeight() - Gdx.input.getY();

        if(Gdx.input.isTouched() && Gdx.input.getX() > x && Gdx.input.getX() < x + DEF_BUTTON_WIDTH)
            if(y > PLAY_BTN_Y && y < PLAY_BTN_Y + DEF_BUTTON_HEIGHT){
                game.startGame();
            }
    }

    private void isExitPressed(){

        int x = (int) (Gdx.graphics.getWidth() / 2 - DEF_BUTTON_WIDTH / 2);
        int y =  Gdx.graphics.getHeight() - Gdx.input.getY();

        if(Gdx.input.isTouched() && Gdx.input.getX() > x && Gdx.input.getX() < x + DEF_BUTTON_WIDTH)
            if(y > EXIT_BTN_Y && y < EXIT_BTN_Y + DEF_BUTTON_HEIGHT){
                System.exit(1);
            }

    }


    /**
     * Loads the assets needed by this screen.
     */
    private void loadAssets() {
        this.game.getAssetManager().load( "exit-button.png" , Texture.class);
        this.game.getAssetManager().load( "play-button.jpg" , Texture.class);

        this.game.getAssetManager().load( "main-menu.png" , Texture.class);

        this.game.getAssetManager().finishLoading();
    }

    private void drawBackground() {
        Texture background = game.getAssetManager().get("main-menu.png", Texture.class);
        game.getBatch().draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
