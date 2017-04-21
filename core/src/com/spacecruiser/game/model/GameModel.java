package com.spacecruiser.game.model;




import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.entities.AsteroidModel;
import com.spacecruiser.game.model.entities.ShipModel;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;


/**
 * A model representing a game.
 */

public class GameModel {
    /**
     * The space ship controlled by the user in this game.
     */
    private ShipModel ship;

    /**
     * The asteroids roaming around in this game.
     */
    private List<AsteroidModel> asteroids;

    /**
     * Constructs a game with a.space ship in a certain position and
     * a certain number of asteroids in different sizes.
     *
     * @param x the x-coordinate of the space ship in meters
     * @param y the y-coordinate of the space ship in meters
     * @param asteroidCount The number of asteroids to create
     */
    public GameModel(float x, float y, int asteroidCount) {
        asteroids = new ArrayList<AsteroidModel>();
        ship = new ShipModel(x, y, 0);


        for (int i = 0; i < asteroidCount; i++)
            asteroids.add(new AsteroidModel(
                    random.nextFloat() * GameController.ARENA_WIDTH,
                    random.nextFloat() * GameController.ARENA_HEIGHT,
                    (float) Math.toRadians(random.nextFloat() * 360),
                    random.nextBoolean()?AsteroidModel.AsteroidSize.BIG:AsteroidModel.AsteroidSize.MEDIUM));

    }

    /**
     * Returns the player space ship.
     *
     * @return the space ship.
     */
    public ShipModel getShip() {
        return ship;
    }

    /**
     * Returns the asteroids.
     *
     * @return the asteroid list
     */
    public List<AsteroidModel> getAsteroids() {
        return asteroids;
    }
}
