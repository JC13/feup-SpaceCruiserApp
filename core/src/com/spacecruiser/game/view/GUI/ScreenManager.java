package com.spacecruiser.game.view.GUI;

import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.GameModel;

/**
 *  A screen manager that manages the screen to be rendered and saves a copy of each screen
 *  to avoid creating and destroying java objects (high cost action).
 */

public class ScreenManager {

    /**
     *  Simulates the different states, each one corresponding to a screen.
     */
    public enum ActiveScreen {SPLASH,MENU,SETTINGS,GAME,GAMEOVER}

    /**
     *  The game where the screen is meant to be set.
     */
    private SpaceCruiser game;

    /**
     *  A game view. There can only be one.
     */
    private GameView gameView;

    /**
     *  The main menu. Saving it to avoid creating new menu objects.
     */
    private MainMenu mainmenu;

    /**
     *  The settings menu. Saving it to avoid creating new settings menu objects.
     */
    private SettingsMenu settingsmenu;

    private GameModel model;


    /**
     *  The manager constructor.
     *
     * @param game the game where the screen will be set
     */
    public ScreenManager(SpaceCruiser game){
        this.game = game;

        this.gameView = null;
        this.mainmenu = null;
        this.settingsmenu = null;
    }


    /**
     *  Sets the screen to be rendered by the game.
     */
    public void drawScreen(ActiveScreen screen){

        switch(screen){

            case SPLASH:
                game.setScreen(new Splash(game));
                break;

            case MENU:
                if(mainmenu == null){
                    mainmenu = new MainMenu(game);
                }
                game.setScreen(this.mainmenu);
                break;

            case SETTINGS:
                if(settingsmenu == null){
                    settingsmenu = new SettingsMenu(game);
                }
                game.setScreen(this.settingsmenu);
                break;

            case GAME:
                if(gameView == null){
                    GameModel model = new GameModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2,
                            SpaceCruiser.ASTEROIDS_COUNT, SpaceCruiser.POINTS_COUNT, SpaceCruiser.SHIELDS_COUNT);
                    this.gameView = new GameView(game, model, new GameController(model));
                }
                game.setScreen(this.gameView);
                break;

            case GAMEOVER:
                game.setScreen(new GameOver(game,model));
                break;
        }
    }
}
