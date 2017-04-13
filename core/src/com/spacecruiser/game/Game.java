package com.spacecruiser.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game extends ApplicationAdapter {

	AssetManager manager;
	SpriteBatch batch;

    Texture pikachu;

    Animation pikachuAttack;

    private float elapsedTime;


	
	@Override
	public void create () {
		batch = new SpriteBatch();

        pikachu = new Texture("pikachuQuickAttack.jpg");
        pikachuAttack = new Animation(.25f,createAnimationFrames(pikachu,2,4));

		manager = new AssetManager();
        manager.load("pikachuQuickAttack.jpg", Texture.class);
		manager.finishLoading();
	}

	@Override
	public void render () {

        elapsedTime += Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

        batch.draw((TextureRegion) pikachuAttack.getKeyFrame(elapsedTime,true),0,0);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}


	public TextureRegion[] createAnimationFrames(Texture animation, int col, int lin){

        TextureRegion temp[][] = TextureRegion.split(animation,animation.getWidth() / col, animation.getHeight() / lin);
        TextureRegion frames[] = new TextureRegion[col*lin];
        System.arraycopy(temp[0],0,frames,0,2);

        return frames;
    }
}
