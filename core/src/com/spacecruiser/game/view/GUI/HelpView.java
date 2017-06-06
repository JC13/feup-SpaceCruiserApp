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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.spacecruiser.game.SpaceCruiser;

/**
 *  A help view, instructions for the game.
 */
public class HelpView extends ScreenAdapter {


    private final static float BTN_WIDTH = 100;
    private final static float BTN_HEIGHT = 100;

    /**
     * The game.
     */
    private SpaceCruiser game;

    /**
     * The stage in which the elements table is to be put and drawn.
     */
    private Stage stage;

    /**
     * The "Back to Main Menu" Button
     */
    private ImageButton backBtn;

    /**
     * Constructor of the help sreen with tha game instructions.
     * @param game The current game.
     */
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

    /**
     * Creates the stage with a specific size and adds the "Back To Main Menu" button to the table.
     */
    private void createStage(){
        stage = new Stage(new FillViewport(SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH,new OrthographicCamera()));

        Table table = new Table();
        table.setFillParent(true);

        table.padLeft(Align.center-150);
        table.padRight(Align.center);
        table.padTop(Align.center+400);

        createBackBtn();
        table.add(backBtn).size(BTN_WIDTH,BTN_HEIGHT)./*padTop(Gdx.graphics.getHeight()+ 3*BTN_HEIGHT).padLeft(Gdx.graphics.getWidth()+ 4* BTN_WIDTH)*/padRight(-Gdx.graphics.getWidth());

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
     * Draws the HelpScreen with a specific image.
     */
    private void drawInstructions(){
        Texture help = game.getAssetManager().get("images/helpmenu.png");
        game.getBatch().draw(help,0,0,SpaceCruiser.MENU_VIEWPORT_WIDTH,SpaceCruiser.MENU_VIEWPORT_HEIGTH);
    }
}
