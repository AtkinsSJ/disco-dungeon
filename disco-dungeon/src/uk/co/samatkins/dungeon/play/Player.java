package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Player extends DungeonEntity {
	
	private PlayWorld world;
	
	public Player(final PlayWorld world, final Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.world = world;
		
		this.sprite = new Sprite(new Texture(Gdx.files.internal("neon/entities.png")), 0, 0, Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT);
		
		this.addListener(new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				System.out.println("Player received input " + keycode);
				if (keycode == Keys.LEFT) {
					moveLeft();
					world.endPlayerTurn();
					return true;
				} else if (keycode == Keys.RIGHT) {
					moveRight();
					world.endPlayerTurn();
					return true;
				} else if (keycode == Keys.UP) {
					moveUp();
					world.endPlayerTurn();
					return true;
				} else if (keycode == Keys.DOWN) {
					moveDown();
					world.endPlayerTurn();
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
