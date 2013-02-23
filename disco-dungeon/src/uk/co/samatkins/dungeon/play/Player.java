package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player extends DungeonEntity {
	
	private int attack = 1;
	
	public Player(final Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		
		AnimatedSprite animation = new AnimatedSprite(AssetManager.getInstance().getEntitiesTexture(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT, 5);
		animation.addAnimation("idle", new int[] {0,1} );
		animation.play("idle");
		
		this.sprite = animation;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (this.dungeon.isPlayersTurn()) {
			if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				this.moveLeft();
				this.dungeon.endPlayerTurn();
			} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				this.moveRight();
				this.dungeon.endPlayerTurn();
			} else if (Gdx.input.isKeyPressed(Keys.UP)) {
				this.moveUp();
				this.dungeon.endPlayerTurn();
			} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				this.moveDown();
				this.dungeon.endPlayerTurn();
			}
		}
	}
	
	@Override
	protected boolean moveBy(final int across, final int up) {
		boolean result = super.moveBy(across, up);
		if (!result) {
			DungeonEntity e = this.dungeon.getEntityAt(this.tileX + across, this.tileY + up);
			if (e != null) {
				this.attack(e);
				return true;
			}
		}
		return result;
	}
	
	private void attack(DungeonEntity e) {
		e.takeDamage(this.attack);
	}
}
