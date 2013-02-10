package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import uk.co.samatkins.Entity;
import uk.co.samatkins.Tilemap;

public class Dungeon extends Entity {
	
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;
	
	private Tilemap tilemap;

	public Dungeon(int width, int height) {
		super();
		
		sprite = tilemap = new Tilemap(new Texture(Gdx.files.internal("cyberpunk/tiles.png")), TILE_WIDTH, TILE_HEIGHT, width, height);
		tilemap.setTileRect(0, width-1, 0, height-1, 16); // Set all to floor
	}
	
	public void placeWall(int x, int y) {
		tilemap.setTile(x, y, 0);
		tilemap.setSolid(x, y, true);
	}

}
