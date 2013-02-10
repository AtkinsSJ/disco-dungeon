package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import uk.co.samatkins.Entity;

public class DungeonEntity extends Entity {
	
	protected PlayWorld world;
	protected int tileX, tileY;

	public DungeonEntity(PlayWorld world, int x, int y) {
		super();
		
		this.world = world;
		this.tileX = x;
		this.tileY = y;
		
		this.setX(this.tileX * Dungeon.TILE_WIDTH);
		this.setY(this.tileY * Dungeon.TILE_HEIGHT);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.isVisible() && (this.sprite != null)) {
			this.sprite.setPosition(this.getX(), this.getY());
			this.sprite.draw(batch, parentAlpha);
		}
	}
	
	private boolean moveBy(final int across, final int up) {
		this.addAction(
			Actions.sequence(
				Actions.moveBy(across * Dungeon.TILE_WIDTH, up * Dungeon.TILE_HEIGHT, 0.2f),
				new Action() { public boolean act(float delta) {
					((DungeonEntity)actor).tileX += across;
					((DungeonEntity)actor).tileY += up;
					return true;
				}}
			)
		);
		
		return true;
	}
	
	public boolean moveLeft() {
		return moveBy(-1, 0);
	}
	
	public boolean moveRight() {
		return moveBy(1, 0);
	}
	
	public boolean moveUp() {
		return moveBy(0, 1);
	}
	
	public boolean moveDown() {
		return moveBy(0, -1);
	}

}
