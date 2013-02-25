package uk.co.samatkins.dungeon.play;



import uk.co.samatkins.Entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class DungeonEntity extends Entity {
	
	protected Dungeon dungeon;
	protected int tileX, tileY;

	protected int hp;
	protected int maxHp;
	protected String name;
	
	protected boolean animating;

	public DungeonEntity(Dungeon dungeon, int x, int y) {
		super();
		
		this.dungeon = dungeon;
		this.tileX = x;
		this.tileY = y;
		
		this.setX(this.tileX * Dungeon.TILE_WIDTH);
		this.setY(this.tileY * Dungeon.TILE_HEIGHT);
		
		this.animating = false;
	}
	
	public String getName() {
		return this.name;
	}
	
	/**
	 * Is this entity currently visible to the player?
	 * @return
	 */
	public boolean visibleToPlayer() {
		Camera cam = this.world.getCamera();
		Rectangle r = new Rectangle(cam.position.x - (cam.viewportWidth/2), cam.position.y - (cam.viewportHeight/2), cam.viewportWidth, cam.viewportHeight);
		boolean v = r.contains(this.getX(), this.getY());
		//System.out.println(this.getName() + " is " + (v ? "visible" : "INVISIBLE"));
		return v;
	}
	
	protected boolean moveBy(int across, int up) {
		
		if (dungeon.isTileSolid(this.tileX + across, this.tileY + up)
			|| (dungeon.getEntityAt(this.tileX + across, this.tileY + up) != null)) {
			return false;
		}
		
		this.animating = true;
		
		this.tileX += across;
		this.tileY += up;
		
		this.addAction(
			Actions.sequence(
				Actions.moveBy(across * Dungeon.TILE_WIDTH,
						up * Dungeon.TILE_HEIGHT,// 0.2f),
						this.visibleToPlayer() ? 0.2f : 0), // If invisible, take 0 seconds
				new Action() { public boolean act(float delta) {
//					((DungeonEntity)actor).tileX += across;
//					((DungeonEntity)actor).tileY += up;
					((DungeonEntity)actor).animating = false;
					return true;
				}}
			)
		);
		
		return true;
	}
	
	public boolean moveLeft() {
		this.sprite.flip(false, false);
		return moveBy(-1, 0);
	}
	
	public boolean moveRight() {
		this.sprite.flip(true, false);
		return moveBy(1, 0);
	}
	
	public boolean moveUp() {
		return moveBy(0, 1);
	}
	
	public boolean moveDown() {
		return moveBy(0, -1);
	}
	
	public boolean isAnimating() {
		return this.animating;
	}
	
	public void takeTurn() { }

	/**
	 * Try to move one tile closer to the given destination.
	 * Tries to move along the furthest axis, then the other axis if that fails.
	 * 
	 * @param targetX
	 * @param targetY
	 * @return
	 */
	public boolean moveTowards(int targetX, int targetY) {
		int xDiff = targetX - this.tileX;
		int yDiff = targetY - this.tileY;
		
		// Which direction is largest?
		if (Math.abs(xDiff) > Math.abs(yDiff)) {
			// Try x first
			if ( !( (xDiff > 0) ? this.moveRight() : this.moveLeft() ) ) {
				return (yDiff == 0)
						? false 
						: ( (yDiff > 0) ? this.moveUp() : this.moveDown() );
			}
			
		} else {
			// Try y first
			if ( !( (yDiff > 0) ? this.moveUp() : this.moveDown() ) ) {
				return (xDiff == 0)
						? false
						: ( (xDiff > 0) ? this.moveRight() : this.moveLeft() );
			}
		}
		
		return false;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public void takeDamage(int attack) {
		System.out.println("OUCH! " + this.getName() + " took " + attack + " damage!");
		this.dungeon.playParticleEffect("attacked", this.tileX, this.tileY);
		this.hp -= attack;
		if (this.hp <= 0) {
			this.dungeon.removeEntity(this);
			this.die();
		}
	}
	
	public void die() {
		this.addAction(
			Actions.parallel(
				Actions.forever(Actions.rotateBy(360, 0.5f)),
				Actions.sequence(
					Actions.scaleTo(0f, 0f, 1f),
					Actions.removeActor()
				)
			)
		);
		System.out.println("OOF! " + this.getName() + " has died!");
	}
	
	protected int distanceToEntity(DungeonEntity e) {
		return (Math.abs(this.tileX - e.tileX)+ Math.abs(this.tileY - e.tileY));
	}
}
