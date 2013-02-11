package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Player extends DungeonEntity {
	
	public Player(final Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		
		this.sprite = new Sprite(new Texture(Gdx.files.internal("cyberpunk/entities.png")), 0, 0, Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT);
		
		this.addListener(new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				System.out.println("Player received input " + keycode);
				if (keycode == Keys.LEFT) {
					moveLeft();
					return true;
				} else if (keycode == Keys.RIGHT) {
					moveRight();
					return true;
				} else if (keycode == Keys.UP) {
					moveUp();
					return true;
				} else if (keycode == Keys.DOWN) {
					moveDown();
					return true;
				} else if (keycode == Keys.SPACE) {
					dungeon.buildDungeon("cyberpunk/Zabutom_-_Zeta_force_level_2.mp3");
				}
				
				return false;
			}
			
			public boolean keyUp(InputEvent event, int keycode) {
				System.out.println("Player received input " + keycode);
				if (keycode == Keys.LEFT) {
					return true;
				} else if (keycode == Keys.RIGHT) {
					return true;
				} else if (keycode == Keys.UP) {
					return true;
				} else if (keycode == Keys.DOWN) {
					return true;
				}
				
				return false;
			}
		});
	}
}
