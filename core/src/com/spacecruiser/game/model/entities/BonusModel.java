package com.spacecruiser.game.model.entities;

/**
 * A model representing a certain type of bonus.
 */

public class BonusModel extends EntityModel {

    /**
     * Possible bonus types.
     */
    public enum BonusType {SHIELD , POINTS}

    /**
     * This bonus type.
     */
    private BonusType type;

    /**
     * Constructs a bonus model belonging to a game model.
     *
     * @param x The x-coordinate of this bonus.
     * @param y The y-coordinate of this bonus.
     * @param rotation The rotation of this bonus.
     * @param type The type of this bonus.
     */
    public BonusModel(float x, float y, float rotation, BonusType type) {
        super(x, y, rotation);
        this.type = type;
    }

    /**
     * Return the bonus type
     *
     * @return the bonus type
     */
    public BonusType getType(){return this.type;}

}
