package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends DungeonEntity {
	
	private int attack = 1;
	
	public Player(final Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		
		this.hp = this.maxHp = 20;
		this.name = "Player";
		
		AnimatedSprite animation = new AnimatedSprite(AssetManager.getInstance().getEntitiesTexture(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT, 5);
		animation.addAnimation("idle", new int[] {0,1} );
		animation.play("idle");
		
		this.sprite = animation;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (this.dungeon.isPlayersTurn()) {
			DungeonEntity e;
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				e = this.dungeon.getEntityAt(this.tileX-1, this.tileY);
				if (e != null) {
					this.attack(e);
				} else {
					this.moveLeft();
				}
				this.dungeon.endPlayerTurn();
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				e = this.dungeon.getEntityAt(this.tileX+1, this.tileY);
				if (e != null) {
					this.attack(e);
				} else {
					this.moveRight();
				}
				this.dungeon.endPlayerTurn();
			} else if (Gdx.input.isKeyPressed(Keys.UP)) {
				e = this.dungeon.getEntityAt(this.tileX, this.tileY+1);
				if (e != null) {
					this.attack(e);
				} else {
					this.moveUp();
				}
				this.dungeon.endPlayerTurn();
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				e = this.dungeon.getEntityAt(this.tileX, this.tileY-1);
				if (e != null) {
					this.attack(e);
				} else {
					this.moveDown();
				}
				this.dungeon.endPlayerTurn();
			}
		}
	}
	
	private void attack(DungeonEntity e) {
		System.out.println("ATTACKING!");
		e.takeDamage(this.attack);
		
		// TODO: Figure-out why this is necessary to stop double-movement bugs.
		this.animating = true;
		this.addAction(
			Actions.delay(0.2f, new Action() {
				public boolean act(float delta) {
					((DungeonEntity)actor).animating = false;
					System.out.println("ATTACK over");
					return true;
				}
			})
		);
	}
}
