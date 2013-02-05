package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import uk.co.samatkins.Entity;
import uk.co.samatkins.Tilemap;

public class Dungeon extends Entity {

	public Dungeon(int width, int height) {
		super("dungeon");
		
		sprite = new Tilemap(new Texture(Gdx.files.internal("cyberpunk/tiles.png")), 16, 16, width, height);
	}

}
