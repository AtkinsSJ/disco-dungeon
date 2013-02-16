package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends DungeonEntity {

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		// TODO Auto-generated constructor stub
		this.sprite = new Sprite(new Texture(Gdx.files.internal("neon/entities.png")), 0, 0, Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT);
	}
	
	@Override
	public void takeTurn() {
		System.out.println("Enemy took a turn!");
		this.moveLeft();
	}

}
