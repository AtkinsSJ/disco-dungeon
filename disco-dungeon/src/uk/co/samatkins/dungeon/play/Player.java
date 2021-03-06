package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;
import uk.co.samatkins.dungeon.play.effects.Poison;
import uk.co.samatkins.dungeon.play.effects.RecoverHealth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Player extends DungeonEntity {
	
	private int attack = 1;
	
	private int sightDistance = 10;
	
	public Player(final Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		
		this.hp = this.maxHp = 20;
		this.name = "Player";
		
		AnimatedSprite animation = new AnimatedSprite(AssetManager.getInstance().getEntitiesTexture(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT, 5);
		animation.addAnimation("idle", new int[] {0,1} );
		animation.play("idle");
		
		this.sprite = animation;
		
		this.refreshFogOfWar();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (this.dungeon.isPlayersTurn()) {
			DungeonEntity e;
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				e = this.dungeon.getEntityAt(this.tileX-1, this.tileY);
				if (e == null || !e.beUsedBy(this)) {
					this.moveLeft();
				}
				this.endTurn();
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				e = this.dungeon.getEntityAt(this.tileX+1, this.tileY);
				if (e == null || !e.beUsedBy(this)) {
					this.moveRight();
				}
				this.endTurn();
			} else if (Gdx.input.isKeyPressed(Keys.UP)) {
				e = this.dungeon.getEntityAt(this.tileX, this.tileY+1);
				if (e == null || !e.beUsedBy(this)) {
					this.moveUp();
				}
				this.endTurn();
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				e = this.dungeon.getEntityAt(this.tileX, this.tileY-1);
				if (e == null || !e.beUsedBy(this)) {
					this.moveDown();
				}
				this.endTurn();
			} else if (Gdx.input.isKeyPressed(Keys.H)) {
				this.addEffect(new RecoverHealth(5));
				this.endTurn();
			}
		}
	}
	
	public void attack(DungeonEntity e) {
		e.takeDamage(this.attack);
	}
	
	private void endTurn() {
		this.animating = true;
		this.addAction(
			Actions.delay(0.2f, new Action() {
				public boolean act(float delta) {
					((DungeonEntity)actor).animating = false;
					return true;
				}
			})
		);
		this.dungeon.endPlayerTurn();
		this.refreshFogOfWar();
		super.takeTurn();
	}
	
	private void refreshFogOfWar() {
		this.dungeon.clearVisibleTiles();
		
		int l = this.tileX - this.sightDistance;
		int r = this.tileX + this.sightDistance;
		int d = this.tileY - this.sightDistance;
		int u = this.tileY + this.sightDistance;
		for (int tx = l; tx < r; tx++) {
			for (int ty = d; ty < u; ty++) {
				if (this.canSee(tx, ty)) {
					this.dungeon.setTileAsSeen(tx, ty);
				}
			}
		}
	}

	@Override
	public boolean beUsedBy(DungeonEntity user) {
		return false;
	}
	
	@Override
	public void die() {
		this.addAction(
				Actions.parallel(
					Actions.forever(Actions.rotateBy(360, 0.5f)),
					Actions.sequence(
						Actions.scaleTo(0f, 0f, 1f),
						new Action() {
							@Override
							public boolean act(float delta) {
								((PlayWorld)(((Player)actor).world)).lose();
								return true;
							}
						}
					)
				)
			);
	}
}
