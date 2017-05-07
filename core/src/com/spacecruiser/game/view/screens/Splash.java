package com.spacecruiser.game.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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

    private SpaceCruiser game;

    private Image splashActor;
    private Stage stage;




    public Splash(SpaceCruiser game){
        this.game = game;
    }

    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        stage.act();
        stage.draw();
    }


    @Override
    public void show(){

        stage = new Stage();

        splashActor = new Image(new TextureRegion(new Texture("splash.png")));
        splashActor.setFillParent(true);


        SequenceAction actions = new SequenceAction(sequence(fadeIn(2f), delay(1.5f), fadeOut(2f), run(new Runnable(){
            @Override
            public void run(){
                game.setScreen(new MainMenu(game));
            }
        })));

        splashActor.addAction(actions);
        stage.addActor(splashActor);
    }


}
