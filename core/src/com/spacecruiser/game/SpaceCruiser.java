package com.spacecruiser.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spacecruiser.game.view.GUI.Splash;

/**
 * The game main class.
 */
public class SpaceCruiser extends Game {


	private SpriteBatch batch;
	private AssetManager assetManager;
    private Music soundtrack;
	private Screen gameScreen;

    /**
     * Creates the game. Initializes the sprite batch and asset manager.
     * Also starts the game until we have a main menu.
     */
	@Override
	public void create () {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
        gameScreen = null;


        createMusicPlayer();

        //begin with splash screen
        this.setScreen(new Splash(this));
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

	public Screen getGameScreen(){ return gameScreen;}

    public void setGameScreen(Screen gameScreen){ this.gameScreen = gameScreen; }

    /**
     *  Returns the music player responsible for playing the game soundtrack.
     *
     * @return the music player
     */
	public Music getMusicPlayer(){return this.soundtrack; }
}