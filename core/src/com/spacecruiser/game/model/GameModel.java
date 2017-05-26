package com.spacecruiser.game.model;


import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.entities.AsteroidModel;
import com.spacecruiser.game.model.entities.EntityModel;
import com.spacecruiser.game.model.entities.PointsModel;
import com.spacecruiser.game.model.entities.ShieldModel;
import com.spacecruiser.game.model.entities.ShipModel;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.random;


/**
 * A model representing a game.
 */

public class GameModel {

    private final static int MIN_PTS_BONUS = 500;
    private final static int MAX_PTS_BONUS = 1000;


    /**
     * The space ship controlled by the user in this game.
     */
    private ShipModel ship;

    /**
     *  Points earned
     */
    private float score = 0;

    private int shieldsPicked = 0;

    private int ptsPicked = 0;

    private int asteroidsDestroyed = 0;

    private String playerNickname;

    /**
     * The asteroids to avoid in this game.
     */
    private List<AsteroidModel> asteroids;

    /**
     * The bonus to be collect in this game.
     */
    private List<PointsModel> points;

    /**
     * The bonus to be collect in this game.
     */
    private List<ShieldModel> shields;



    /**
     * Constructs a game with a.space ship in a certain position and
     * a certain number of asteroids in different sizes.
     *
     * @param x the x-coordinate of the space ship in meters
     * @param y the y-coordinate of the space ship in meters
     * @param asteroidCount The number of asteroids to create
     */
    public GameModel(float x, float y, int asteroidCount, int ptsCount, int shieldsCount) {
        asteroids = new ArrayList<AsteroidModel>();
        points = new ArrayList<PointsModel>();
        shields = new ArrayList<ShieldModel>();
        ship = new ShipModel(x, y, 0);


        for (int i = 0; i < asteroidCount; i++)
            asteroids.add(new AsteroidModel(
                    random.nextFloat() * GameController.ARENA_WIDTH,
                    random.nextFloat() * GameController.ARENA_HEIGHT,
                    (float) Math.toRadians(random.nextFloat() * 360),
                    random.nextBoolean() ? AsteroidModel.AsteroidSize.BIG : AsteroidModel.AsteroidSize.MEDIUM));


        for(int j = 0; j < ptsCount; j++)
            points.add(new PointsModel(
                    random.nextFloat() * GameController.ARENA_WIDTH,
                    random.nextFloat() * GameController.ARENA_HEIGHT,
                    (float) Math.toRadians(random.nextFloat() * 360),
                    random.nextInt(MAX_PTS_BONUS) + MIN_PTS_BONUS));

        for(int j = 0; j < shieldsCount; j++)
            shields.add(new ShieldModel(
                    random.nextFloat() * GameController.ARENA_WIDTH,
                    random.nextFloat() * GameController.ARENA_HEIGHT,
                    (float) Math.toRadians(random.nextFloat() * 360)));

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
     *  Returns the score.
     *
     *  @return the score
     */
    public float getScore(){return score;}

    /**
     * Set the score.
     *
     */
    public void setScore(float points){ this.score = points;}

    public void increaseAsteroidsDestroyed(){
        this.asteroidsDestroyed++;
    }

    public void increasePtsPicked(){
        this.ptsPicked++;
    }

    public void increaseShieldsPicked(){
        this.shieldsPicked++;
    }

    public void remove(EntityModel model) {

        if (model instanceof AsteroidModel) {
            asteroids.remove(model);
        }

        if (model instanceof PointsModel) {
            points.remove(model);
        }

        if (model instanceof ShieldModel){
            shields.remove(model);
        }
    }

    /**
     * Returns the asteroids.
     *
     * @return the asteroid list
     */
    public List<AsteroidModel> getAsteroids() {
        return asteroids;
    }

    /**
     * Returns the bonus
     *
     * @return the bonus list
     */
    public List<PointsModel> getBonusPoints() { return points; }

    public List<ShieldModel> getBonusShields() { return shields; }

    public int getShieldsPicked() {
        return shieldsPicked;
    }

    public int getPtsPicked() {
        return ptsPicked;
    }

    public int getAsteroidsDestroyed() {
        return asteroidsDestroyed;
    }

    public String getPlayerNickname(){
        return this.playerNickname;
    }
}
