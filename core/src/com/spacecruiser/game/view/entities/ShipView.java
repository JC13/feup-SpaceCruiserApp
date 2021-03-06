package com.spacecruiser.game.view.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.spacecruiser.game.SpaceCruiser;
import com.spacecruiser.game.model.entities.EntityModel;
import com.spacecruiser.game.model.entities.ShipModel;

/**
 * A view representing a space ship
 */
public class ShipView extends EntityView {
    /**
     * The time between the animation frames
     */
    private static final float FRAME_TIME = 0.05f;

    /**
     * The animation used when the ship is accelerating
     */
    private Animation<TextureRegion> acceleratingAnimation;

    private Animation<TextureRegion> shieldedAnimation;

    /**
     * The texture used when the ship is not accelerating
     */
    private TextureRegion notAcceleratingRegion;

    /**
     * Time since the space ship started the game. Used
     * to calculate the frame to show in animations.
     */
    private float stateTime = 0;

    /**
     * Is the space ship accelerating.
     */
    private boolean accelerating;

    private boolean shielded;

    /**
     * Constructs a space ship model.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public ShipView(SpaceCruiser game) {
        super(game);
    }

    /**
     * Creates a sprite representing this space ship.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this space ship
     */
    @Override
    public Sprite createSprite(SpaceCruiser game) {
        acceleratingAnimation = createAcceleratingAnimation(game);
        notAcceleratingRegion = createNotAcceleratingRegion(game);
        shieldedAnimation = createShieldedAnimation(game);

        return new Sprite(notAcceleratingRegion);
    }

    /**
     * Creates the texture used when the ship is not accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the texture used when the ship is not accelerating
     */
    private TextureRegion createNotAcceleratingRegion(SpaceCruiser game) {
        Texture noThrustTexture = game.getAssetManager().get("images/spaceship-no-thrust.png");
        return new TextureRegion(noThrustTexture, noThrustTexture.getWidth(), noThrustTexture.getHeight());
    }

    /**
     * Creates the animation used when the ship is accelerating
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the animation used when the ship is accelerating
     */
    private Animation<TextureRegion> createAcceleratingAnimation(SpaceCruiser game) {
        Texture thrustTexture = game.getAssetManager().get("images/spaceship-thrust.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 4, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[4];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 4);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }


    private Animation<TextureRegion> createShieldedAnimation(SpaceCruiser game){
        Texture thrustTexture = game.getAssetManager().get("images/spaceship-thrust-shielded.png");
        TextureRegion[][] thrustRegion = TextureRegion.split(thrustTexture, thrustTexture.getWidth() / 4, thrustTexture.getHeight());

        TextureRegion[] frames = new TextureRegion[4];
        System.arraycopy(thrustRegion[0], 0, frames, 0, 4);

        return new Animation<TextureRegion>(FRAME_TIME, frames);
    }

    /**
     * Updates this ship model. Also save and resets
     * the accelerating flag from the model.
     *
     * @param model the model used to update this view
     */
    @Override
    public void update(EntityModel model) {
        super.update(model);

        accelerating = ((ShipModel)model).isAccelerating();
        shielded = ((ShipModel)model).isShielded();
        ((ShipModel)model).setAccelerating(false);
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     * Chooses the correct texture or animation to be used
     * depending on the accelerating flag.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (accelerating)
            sprite.setRegion(acceleratingAnimation.getKeyFrame(stateTime, true));

        if(shielded)
            sprite.setRegion(shieldedAnimation.getKeyFrame(stateTime,true));

        sprite.draw(batch);
    }
}
