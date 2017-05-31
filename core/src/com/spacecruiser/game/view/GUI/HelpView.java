package com.spacecruiser.game.view.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
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
 *  A help view, instructions for the game.
 */
public class HelpView extends ScreenAdapter {


    private final static float BTN_WIDTH = 100;
    private final static float BTN_HEIGHT = 100;


    private SpaceCruiser game;

    private Stage stage;
    private ImageButton backBtn;

    public HelpView(SpaceCruiser game){
        this.game = game;
    }


    @Override
    public void show(){
        createStage();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        game.getBatch().begin();
        drawInstructions();
        game.getBatch().end();

        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width,int height){
        stage.getViewport().update(width,height);
    }

    private void createStage(){
        stage = new Stage(new FitViewport(SpaceCruiser.GAME_VIEWPORT_WIDTH,SpaceCruiser.GAME_VIEWPORT_HEIGHT,new OrthographicCamera()));

        Table table = new Table();

        createBackBtn();
        table.add(backBtn).size(BTN_WIDTH,BTN_HEIGHT).padLeft(SpaceCruiser.MENU_VIEWPORT_WIDTH - BTN_WIDTH)
                .padTop(SpaceCruiser.MENU_VIEWPORT_HEIGTH - BTN_HEIGHT);
        stage.addActor(table);
    }

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

    private void drawInstructions(){
        Texture help = game.getAssetManager().get("images/helpmenu.jpg");
        game.getBatch().draw(help,0,0,SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH);
    }
}
