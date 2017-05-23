package com.spacecruiser.game.model.entities;

/**
 * A model representing a certain type of bonus.
 */

public class PointsModel extends EntityModel {

    private int value;

    /**
     * Constructs a bonus model belonging to a game model.
     *
     * @param x The x-coordinate of this bonus.
     * @param y The y-coordinate of this bonus.
     * @param rotation The rotation of this bonus.
     */
    public PointsModel(float x, float y, float rotation, int value) {
        super(x, y, rotation);
        this.value = value;
    }


    public int getValue(){
        return this.value;
    }

}
