package com.spacecruiser.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.spacecruiser.game.model.entities.BonusModel;

/**
 * A concrete representation of an EntityBody representing a bonus shield.
 */

public class ShieldBody extends EntityBody {

    /**
     * Constructs a bonus shield body according to a bonus shield.
     *
     * @param world the physical world this shield belongs to.
     * @param model the model representing this shield.
     */
    public ShieldBody(World world, BonusModel model) {
        super(world, model);

        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 35, height = 35;

        createFixture(body, new float[]{
                0,23, 3,2, 27,2, 34,18, 17,33
        }, width, height, density, friction, restitution);
    }

}
