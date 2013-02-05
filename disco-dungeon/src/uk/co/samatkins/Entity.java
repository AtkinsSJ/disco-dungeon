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
		init();
	}

	public Entity(String name) {
		super(name);
		init();
	}
	
	private void init() {
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.visible && (sprite != null)) {
			sprite.setPosition(x, y);
			sprite.draw(batch, parentAlpha);
		}
	}

	@Override
	public Actor hit(float x, float y) {
		if (this.touchable && sprite.getBoundingRectangle().contains(x, y)) {
			return this;
		}
		return null;
	}

}
