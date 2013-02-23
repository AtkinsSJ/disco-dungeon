package uk.co.samatkins.dungeon.play;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import uk.co.samatkins.Entity;

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
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (this.isVisible() && (this.sprite != null)) {
			this.sprite.setPosition(this.getX(), this.getY());
			this.sprite.draw(batch, parentAlpha);
		}
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
	
	private boolean moveBy(final int across, final int up) {
		
		if (dungeon.isTileSolid(this.tileX + across, this.tileY + up)
				|| (dungeon.getEntityAt(this.tileX + across, this.tileY + up) != null) ) {
			return false;
		}
		
		this.animating = true;
		
		this.addAction(
			Actions.sequence(
				Actions.moveBy(across * Dungeon.TILE_WIDTH,
						up * Dungeon.TILE_HEIGHT, 0.2f),
						//this.visibleToPlayer() ? 0.2f : 0), // If invisible, take 0 seconds
				new Action() { public boolean act(float delta) {
					((DungeonEntity)actor).tileX += across;
					((DungeonEntity)actor).tileY += up;
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

}
