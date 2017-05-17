package com.spacecruiser.game.view.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.spacecruiser.game.SpaceCruiser;

/**
 * A view representing extra points bonus.
 */

public class PointsView extends EntityView {

    /**
     * Constructs a bonus points view.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public PointsView(SpaceCruiser game) {
        super(game);
    }

    /**
     * Creates a sprite representing this extra points.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this bonus
     */
    public Sprite createSprite(SpaceCruiser game) {
        Texture texture = game.getAssetManager().get("images/bonus-points.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

}
