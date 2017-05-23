package com.spacecruiser.game.controller;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spacecruiser.game.controller.entities.BigAsteroidBody;
import com.spacecruiser.game.controller.entities.MediumAsteroidBody;
import com.spacecruiser.game.controller.entities.PointsBody;
import com.spacecruiser.game.controller.entities.ShieldBody;
import com.spacecruiser.game.controller.entities.ShipBody;
import com.spacecruiser.game.model.GameModel;
import com.spacecruiser.game.model.entities.AsteroidModel;
import com.spacecruiser.game.model.entities.EntityModel;
import com.spacecruiser.game.model.entities.PointsModel;
import com.spacecruiser.game.model.entities.ShieldModel;
import com.spacecruiser.game.model.entities.ShipModel;
import com.spacecruiser.game.view.entities.PointsView;

import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Controls the physics aspect of the game.
 */

public class GameController implements ContactListener{
    /**
     * The arena width in meters.
     */
    public static final int ARENA_WIDTH = 100;

    /**
     * The arena height in meters.
     */
    public static final int ARENA_HEIGHT = 50;

    /**
     * The rotation speed in radians per second.
     */
    private static final float ROTATION_SPEED = 10f;

    /**
     * The acceleration impulse in newtons.
     */
    private static final float ACCELERATION_FORCE = 30f;

    private GameModel model;
    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The spaceship body.
     */
    private final ShipBody shipBody;

    /**
     * Accumulator used to calculate the simulation step.
     */
    private float accumulator;


    private int accumulatedPts = 0;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     *
     * @param model The model controlled by this controller.
     */
    public GameController(GameModel model) {
        this.model = model;
        world = new World(new Vector2(0, 0), true);
        shipBody = new ShipBody(world, model.getShip());

        List<AsteroidModel> asteroids = model.getAsteroids();
        for (AsteroidModel asteroid : asteroids)
        if (asteroid.getSize() == AsteroidModel.AsteroidSize.BIG)
            new BigAsteroidBody(world, asteroid);
        else if (asteroid.getSize() == AsteroidModel.AsteroidSize.MEDIUM)
            new MediumAsteroidBody(world, asteroid);


        List<PointsModel> points = model.getBonusPoints();
        for (PointsModel b : points) {
            new PointsBody(world, b);
        }

        List<ShieldModel> shields = model.getBonusShields();
        for (ShieldModel b : shields) {
            new ShieldBody(world, b);
        }

        world.setContactListener(this);

    }

    /**
     * Calculates the next physics step of duration delta (in seconds).
     *
     * @param delta The size of this physics step in seconds.
     */
    public void update(float delta) {
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            verifyBounds(body);
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
            ((EntityModel) body.getUserData()).setRotation(body.getAngle());
        }
        this.accelerate(delta);

    }

    /**
     * Verifies if the body is inside the arena bounds and if not
     * wraps it around to the other side.
     *
     * @param body The body to be verified.
     */
    private void verifyBounds(Body body) {
        if (body.getPosition().x < 0)
            body.setTransform(ARENA_WIDTH, body.getPosition().y, body.getAngle());

        if (body.getPosition().y < 0)
            body.setTransform(body.getPosition().x, ARENA_HEIGHT, body.getAngle());

        if (body.getPosition().x > ARENA_WIDTH)
            body.setTransform(0, body.getPosition().y, body.getAngle());

        if (body.getPosition().y > ARENA_HEIGHT)
            body.setTransform(body.getPosition().x, 0, body.getAngle());
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Rotates the spaceship left. The rotation takes into consideration the
     * constant rotation speed and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void rotateLeft(float delta) {
        shipBody.setTransform(shipBody.getX(), shipBody.getY(), shipBody.getAngle() + ROTATION_SPEED * delta);
        shipBody.setAngularVelocity(0);
    }

    /**
     * Rotates the spaceship right. The rotation takes into consideration the
     * constant rotation speed and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */
    public void rotateRight(float delta) {
        shipBody.getX();

        shipBody.setTransform(shipBody.getX(), shipBody.getY(), shipBody.getAngle() - ROTATION_SPEED * delta);
        shipBody.setAngularVelocity(0);
    }

    /**
     * Accelerates the spaceship. The acceleration takes into consideration the
     * constant acceleration force and the delta for this simulation step.
     *
     * @param delta Duration of the rotation in seconds.
     */

    public void accelerate(float delta) {
        shipBody.applyForceToCenter(-(float) sin(shipBody.getAngle()) * ACCELERATION_FORCE * delta, (float) cos(shipBody.getAngle()) * ACCELERATION_FORCE * delta, true);
        ((ShipModel)shipBody.getUserData()).setAccelerating(true);
    }


    /**
     * Increase current score based on acceleration
     */
    public void increaseScore(float delta){
        model.setScore(model.getScore() + (delta * ACCELERATION_FORCE)/10 + accumulatedPts);
        accumulatedPts = 0;
    }

    public void ptsShipCollision(Body pts, Body ship){
        PointsModel ptsModel = ((PointsModel) pts.getUserData());
        accumulatedPts += ptsModel.getValue();
        model.increasePtsPicked();
        ptsModel.setToBeRemoved();
    }

    public void shieldShipCollision(Body shield, Body ship){
        ((ShipModel) ship.getUserData()).setShielded(true);
        ((ShieldModel) shield.getUserData()).setToBeRemoved();
        model.increaseShieldsPicked();
    }

    public void asteroidsShipCollision(Body asteroid, Body ship){
        if(((ShipModel)ship.getUserData()).isShielded()){
            ((AsteroidModel)asteroid.getUserData()).setToBeRemoved();
            ((ShipModel)ship.getUserData()).setShielded(false);
            model.increaseAsteroidsDestroyed();
        }
        else{
            System.out.println("GAME OVER");
        }
    }

    public void removeFlagged() {
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {

            if (((EntityModel)body.getUserData()).isFlaggedToBeRemoved()) {
                model.remove((EntityModel)body.getUserData());
                world.destroyBody(body);
            }
        }
    }

    @Override
    public void beginContact(Contact contact) {
        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();

        //collision between ship & points
        if (bodyA.getUserData() instanceof PointsModel && bodyB.getUserData() instanceof ShipModel){
            ptsShipCollision(bodyA,bodyB);
        }

        if (bodyA.getUserData() instanceof ShipModel && bodyB.getUserData() instanceof PointsModel) {
            ptsShipCollision(bodyB,bodyA);
        }


        //collision between ship & shields
        if (bodyA.getUserData() instanceof ShieldModel && bodyB.getUserData() instanceof ShipModel){
            shieldShipCollision(bodyA,bodyB);
        }

        if (bodyA.getUserData() instanceof ShipModel && bodyB.getUserData() instanceof ShieldModel){
            shieldShipCollision(bodyB,bodyA);
        }


        //collision between ship & asteroids
        if (bodyA.getUserData() instanceof AsteroidModel && bodyB.getUserData() instanceof ShipModel){
            asteroidsShipCollision(bodyA,bodyB);
        }

        if (bodyA.getUserData() instanceof ShipModel && bodyB.getUserData() instanceof AsteroidModel){
            asteroidsShipCollision(bodyB,bodyA);
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
