package com.spacecruiser.game.model;

import org.junit.Test;
import com.spacecruiser.game.controller.GameController;
import com.spacecruiser.game.model.entities.AsteroidModel;
import com.spacecruiser.game.model.entities.EntityModel;
import com.spacecruiser.game.model.entities.PointsModel;
import com.spacecruiser.game.model.entities.ShieldModel;
import com.spacecruiser.game.model.entities.ShipModel;
import com.spacecruiser.game.SpaceCruiser;

import static org.junit.Assert.*;




public class GameModelTest {
    @Test
    public void testOne(){
        GameModel model = new GameModel(GameController.ARENA_WIDTH / 2, GameController.ARENA_HEIGHT / 2,
                SpaceCruiser.ASTEROIDS_COUNT, SpaceCruiser.POINTS_COUNT, SpaceCruiser.SHIELDS_COUNT);

        assertEquals(SpaceCruiser.POINTS_COUNT, model.getPtsPicked());
    }

}