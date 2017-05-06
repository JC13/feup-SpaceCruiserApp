package com.spacecruiser.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.GameModel;
import com.spacecruiser.game.view.GameView;
import com.spacecruiser.game.view.MainMenu;

/**
 * The game main class.
 */
public class SpaceCruiser extends Game {

	private SpriteBatch batch;
	private AssetManager assetManager;

    /**
     * Creates the game. Initializes the sprite batch and asset manager.
     * Also starts the game until we have a main menu.
     */
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();

        //begin with menu screen
        setScreen(new MainMenu(this));

	}



    /**
     * Disposes of all assets.
     */
    @Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

    /**
     * Returns the asset manager used to load all textures and sounds.
     *
     * @return the asset manager
     */
	public AssetManager getAssetManager() {
		return assetManager;
	}

    /**
     * Returns the sprite batch used to improve drawing performance.
     *
     * @return the sprite batch
     */
	public SpriteBatch getBatch() {
		return batch;
	}
}