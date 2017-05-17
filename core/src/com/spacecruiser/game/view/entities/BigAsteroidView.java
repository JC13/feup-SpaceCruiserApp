package com.spacecruiser.game.view.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.spacecruiser.game.SpaceCruiser;

/**
 * A view representing a big asteroid
 */
public class BigAsteroidView extends EntityView{
    /**
     * Constructs a big asteroid view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public BigAsteroidView(SpaceCruiser game) {
        super(game);
    }

    /**
     * Creates a sprite representing this asteroid.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this bonus shield
     */
    public Sprite createSprite(SpaceCruiser game) {
        Texture texture = game.getAssetManager().get("images/asteroid-big.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
