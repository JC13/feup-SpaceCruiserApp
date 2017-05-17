package com.spacecruiser.game.view.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.spacecruiser.game.SpaceCruiser;

/**
 * A view representing a medium asteroid
 */

public class MediumAsteroidView extends EntityView{
    /**
     * Constructs a medium asteroid view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public MediumAsteroidView(SpaceCruiser game) {
        super(game);
    }

    /**
     * Creates a sprite representing this asteroid.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this asteroid
     */
    public Sprite createSprite(SpaceCruiser game) {
        Texture texture = game.getAssetManager().get("images/asteroid-medium.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
