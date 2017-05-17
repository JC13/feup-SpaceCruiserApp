package com.spacecruiser.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.spacecruiser.game.SpaceCruiser;

/**
 * A view representing a shield bonus.
 */

public class ShieldView extends EntityView {

    /**
     * Constructs a shield bonus view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public ShieldView(SpaceCruiser game) {
        super(game);
    }

    /**
     * Creates a sprite representing this shield.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this shield
     */
    public Sprite createSprite(SpaceCruiser game) {
        Texture texture = game.getAssetManager().get("images/bonus-shield.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

}
