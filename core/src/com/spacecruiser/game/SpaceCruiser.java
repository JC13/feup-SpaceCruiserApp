package com.spacecruiser.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacecruiser.game.view.GUI.ScreenManager;

/**
 * The game main class.
 */
public class SpaceCruiser extends Game {

    public final static float VIEWPORT_WIDTH = 1400;
    public final static float VIEWPORT_HEIGTH = 900;

    /**
     *  A sprite batch to draw components, passed along the screens.
     */
	private SpriteBatch batch;

    /**
     *  An asset manager to load assets and dispose of them at the end of the applicaton.
     */
    private AssetManager assetManager;

    /**
     *  A music player that plays the main theme.
     */
    private Music soundtrack;

    /**
     *  The screen manager, controling what needs to be rendered by the game.
     */
    private ScreenManager screenManager;

    /**
     * Creates the game. Initializes the sprite batch and asset manager.
     * Also starts the game until we have a main menu.
     */
	@Override
	public void create () {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        screenManager = new ScreenManager(this);

        createMusicPlayer();

        //begin with splash screen
        screenManager.drawScreen(ScreenManager.ActiveScreen.SPLASH);
    }


    /**
     * Disposes of all assets.
     */
    @Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
		soundtrack.dispose();
	}

    /**
     *  Creates the music player responsible for playing the game soundtrack.
     *  Sets volume to half power, indicates that music should loop and plays it.
     */
	public void createMusicPlayer(){
        soundtrack = Gdx.audio.newMusic(Gdx.files.internal("audio/dubstep.mp3"));
        soundtrack.setLooping(true);
        soundtrack.setVolume(0.5f);
        soundtrack.play();
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

    /**
     *  Returns the screen manager.
     *
     * @return the screen manager.
     */
    public ScreenManager getScreenManager(){
        return  this.screenManager;
    }

    /**
     *  Returns the music player responsible for playing the game soundtrack.
     *
     * @return the music player
     */
	public Music getMusicPlayer(){return this.soundtrack;}

}