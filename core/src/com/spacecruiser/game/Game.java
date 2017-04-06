package com.spacecruiser.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Game extends ApplicationAdapter {

	SpriteBatch batch;
	Texture helloImg;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		helloImg = new Texture(Gdx.files.internal("helloworld.jpg"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(helloImg,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		helloImg.dispose();
	}
}
