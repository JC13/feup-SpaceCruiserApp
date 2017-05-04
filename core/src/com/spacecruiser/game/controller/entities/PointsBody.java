package com.spacecruiser.game.controller.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.spacecruiser.game.model.entities.BonusModel;

/**
 * A concrete representation of an EntityBody representing an extra points bonus.
 */

public class PointsBody extends EntityBody {

    /**
     * Constructs extra points bonus body.
     *
     * @param world the physical world this bonus belongs to.
     * @param model the model representing this bonus.
     */
    public PointsBody(World world, BonusModel model) {
        super(world, model);

        float density = 1f, friction = 0.4f, restitution = 0.5f;
        int width = 35, height = 35;

        createFixture(body, new float[]{
                0,23, 3,2, 27,2, 34,18, 17,33
        }, width, height, density, friction, restitution);
    }

}
