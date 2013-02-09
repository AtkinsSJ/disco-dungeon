package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.co.samatkins.Entity;

public class DungeonEntity extends Entity {
	
	protected int tileX, tileY;

	public DungeonEntity(int x, int y) {
		super();
		this.init(x, y);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.isVisible() && (this.sprite != null)) {
			this.sprite.setPosition(this.tileX * Dungeon.TILE_WIDTH, this.tileY * Dungeon.TILE_HEIGHT);
			this.sprite.draw(batch, parentAlpha);
		}
	}
	
	private void init(int x, int y) {
		this.tileX = x;
		this.tileY = y;
	}
	
	public boolean moveLeft() {
		this.tileX--;
		return true;
	}
	
	public boolean moveRight() {
		this.tileX++;
		return true;
	}
	
	public boolean moveUp() {
		this.tileY++;
		return true;
	}
	
	public boolean moveDown() {
		this.tileY--;
		return true;
	}

}
