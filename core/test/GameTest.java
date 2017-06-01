import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.controller.entities.BigAsteroidBody;
import com.spacecruiser.game.controller.entities.MediumAsteroidBody;
import com.spacecruiser.game.controller.entities.PointsBody;
import com.spacecruiser.game.controller.entities.ShieldBody;
import com.spacecruiser.game.controller.entities.ShipBody;
import com.spacecruiser.game.controller.entities.EntityBody;
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

import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    private GameController controller;
    private GameModel model;
    private World world;
    private Body ship, asteroid, shield, point;

    public GameTest(){
        model = new GameModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2,
                1,1, 1);
        controller = new GameController(model);

        world = controller.getWorld();

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        int auxA = 0, auxShip = 0, auxShield = 0, auxP = 0;
        for(Body body: bodies){
            if (body.getUserData() instanceof AsteroidModel)
                asteroid = body;
            else if(body.getUserData() instanceof PointsModel)
                point = body;
            else if(body.getUserData() instanceof ShipModel)
                ship = body;
            else if(body.getUserData() instanceof ShieldModel)
                shield = body;
        }

        ((EntityModel)asteroid.getUserData()).setPosition(2,2);
        ((EntityModel)ship.getUserData()).setPosition(2,2);
        ((EntityModel)shield.getUserData()).setPosition(2,2);
        ((EntityModel)point.getUserData()).setPosition(2,2);



    }

    @Test
    public void testCollisions(){
        controller.shieldShipCollision(shield, ship);
        assertTrue(model.getShip().isShielded());
        assertEquals(1,model.getShieldsPicked());

        controller.asteroidsShipCollision(asteroid,ship);
        assertEquals(1,model.getAsteroidsDestroyed(),0);
        assertTrue(model.getAsteroids().get(0).isFlaggedToBeRemoved());
        assertFalse(model.getShip().isShielded());
        assertTrue(model.getBonusShields().get(0).isFlaggedToBeRemoved());


        controller.ptsShipCollision(point, ship);
        assertEquals(1,model.getPtsPicked(),0);

        controller.removeFlagged();
        assertEquals(0,model.getAsteroids().size(),0);
        assertEquals(0,model.getBonusShields().size(),0);
        assertEquals(0,model.getBonusPoints().size(),0);
        assertEquals(1, world.getBodyCount(),0);



    }




}
