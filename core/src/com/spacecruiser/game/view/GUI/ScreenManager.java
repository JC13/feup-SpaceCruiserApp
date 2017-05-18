package com.spacecruiser.game.view.GUI;

import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.GameModel;

/**
 *  A state machine that manages the screen to be rendered.
 */

public class ScreenManager {

    /**
     *  Simulates the different states, each one corresponding to a screen.
     */
    public enum ActiveScreen {SPLASH,MENU,SETTINGS,GAME};

    /**
     *  The game where the screen is meant to be set.
     */
    private SpaceCruiser game;

    /**
     *  A game view. There can only be one.
     */
    private GameView gameView;

    /**
     *  The current screen to be rendered by the game.
     */
    private ActiveScreen screenToRender;


    /**
     *  The manager constructor.
     *
     * @param game the game where the screen will be set
     */
    public ScreenManager(SpaceCruiser game){
        this.game = game;
        this.gameView = null;
        screenToRender = ActiveScreen.SPLASH;
    }

    /**
     *  Sets the screen to render to the screen passed as argument
     *
     * @param screen the screen to be rendered
     */
    public void update(ActiveScreen screen){
        screenToRender = screen;
    }

    /**
     *  Sets the screen to be rendered by the game.
     */
    public void drawScreen(){

        switch(screenToRender){

            case SPLASH:
                game.setScreen(new Splash(game));
                break;

            case MENU:
                game.setScreen(new MainMenu(game));
                break;

            case SETTINGS:
                game.setScreen(new SettingsMenu(game));
                break;

            case GAME:
                if(gameView == null){
                    GameModel model = new GameModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2, 100, 100);
                    this.gameView = new GameView(game, model, new GameController(model));
                }
                game.setScreen(this.gameView);
                break;

        }
    }
}
