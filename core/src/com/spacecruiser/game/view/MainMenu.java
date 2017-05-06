package com.spacecruiser.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.GameModel;

/**
 * A view for the main menu
 */

public class MainMenu extends ScreenAdapter {

    private SpaceCruiser game;
    private Stage stage;
    private Table table;


    private TextButton.TextButtonStyle btnStyle;
    private TextButton playBtn, exitBtn;



    public MainMenu(SpaceCruiser game){

        this.game = game;
        loadAssets();

        this.stage = new Stage();
        this.table = new Table();
        table.center();
        table.setFillParent(true);
        table.setWidth(Gdx.graphics.getWidth());
        table.setHeight(Gdx.graphics.getHeight());
    }


    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
    }


    @Override
    public void show(){

        drawBackground();

        btnStyle = new TextButton.TextButtonStyle();
        btnStyle.font = new BitmapFont();
        btnStyle.fontColor = com.badlogic.gdx.graphics.Color.BLUE;

        playBtn = new TextButton("Play Game", btnStyle);
        playBtn.pad(10);
        playBtn.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent e, float x, float y){
               startGame();
           }
        });
        table.add(playBtn);
        table.row();

        exitBtn = new TextButton("Exit", btnStyle);
        exitBtn.pad(20);
        exitBtn.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent e, float x, float y){
               Gdx.app.exit();
           }
        });
        table.add(exitBtn);

        stage.addActor(table);
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

    /**
     * Starts the game.
     */
    private void startGame() {
        GameModel model = new GameModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2, 100, 100);
        game.setScreen(new GameView(game, model, new GameController(model)));
    }

    /**
     * Draws the background
     */
    private void drawBackground() {
        Texture background = game.getAssetManager().get("main-menu.png", Texture.class);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.getBatch().end();
    }
}
