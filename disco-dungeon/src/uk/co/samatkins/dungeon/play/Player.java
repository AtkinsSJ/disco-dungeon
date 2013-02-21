package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Player extends DungeonEntity {
	
	public Player(final Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		
		AnimatedSprite animation = new AnimatedSprite(AssetManager.getInstance().getEntitiesTexture(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT, 10);
		animation.addAnimation("idle", new int[] {0,1} );
		animation.play("idle");
		
		
		this.sprite = animation;
		
		this.addListener(new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				
				if (animating) { return false; }
				
				if (!dungeon.isPlayersTurn()) {
					System.out.println("Not the player's turn!");
					return false;
				}
				
				System.out.println("Player received input " + keycode);
				if (keycode == Keys.LEFT) {
					moveLeft();
					dungeon.endPlayerTurn();
					return true;
				} else if (keycode == Keys.RIGHT) {
					moveRight();
					dungeon.endPlayerTurn();
					return true;
				} else if (keycode == Keys.UP) {
					moveUp();
					dungeon.endPlayerTurn();
					return true;
				} else if (keycode == Keys.DOWN) {
					moveDown();
					dungeon.endPlayerTurn();
					return true;
				} else if (keycode == Keys.SPACE) { // TODO: Debug code, to be removed
					dungeon.buildDungeon("neon/Zabutom_-_Zeta_force_level_2.mp3");
				}
				
				return false;
			}
			
			public boolean keyUp(InputEvent event, int keycode) {
//				System.out.println("Player key up " + keycode);
//				if (keycode == Keys.LEFT) {
//					return true;
//				} else if (keycode == Keys.RIGHT) {
//					return true;
//				} else if (keycode == Keys.UP) {
//					return true;
//				} else if (keycode == Keys.DOWN) {
//					return true;
//				}
				
				return false;
			}
		});
	}
}
