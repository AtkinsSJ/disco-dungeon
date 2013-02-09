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
	
	protected Sprite sprite;

	public Entity() {

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.isVisible() && (sprite != null)) {
			sprite.setPosition(this.getX(), this.getY());
			sprite.draw(batch, parentAlpha);
		}
	}

}
