package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.model.GameModel;

/**
 * Represents the in game HUD holding game related information
 */

public class GameHUD {

    private final static float HUD_VIEWPORT_WIDTH = 100;
    private final static float HUD_VIEWPORT_HEIGHT = 100;


    private Stage stage;
    private Viewport viewport;
    private Table table;
    private Label scoreLbl, scoreInfo;
    private int score;


    public GameHUD(){
        score = 0;
        scoreLbl = new Label("SCORE",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreInfo = new Label(String.format("%04d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        viewport = new FitViewport(HUD_VIEWPORT_WIDTH,HUD_VIEWPORT_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport);
        table = new Table();
        table.top().setFillParent(true);
        //table.setDebug(true);
        table.add(scoreLbl).expandX();
        table.add(scoreInfo).expandX();
        stage.addActor(table);
    }


    public void update(float score){
        this.score = (int)score;

        scoreInfo.setText(String.format("%04d",this.score));

        stage.act();
        stage.draw();
    }

}
