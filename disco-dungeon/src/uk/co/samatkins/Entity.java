package uk.co.samatkins;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Similar to Flashpunk's Entity.
 * 
 * Base class for all game objects. 
 * 
 * @author Sam
 *
 */
public class Entity extends Actor {
	
	protected World world;
	protected Sprite sprite;

	public Entity() {

	}
	
	public void setWorld(World world) {
		this.world = world;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.isVisible() && (sprite != null)) {
			sprite.setPosition(this.getX(), this.getY());
			sprite.setRotation(this.getRotation());
			sprite.setScale(this.getScaleX(), this.getScaleY());
			sprite.draw(batch, parentAlpha);
		}
	}

}
