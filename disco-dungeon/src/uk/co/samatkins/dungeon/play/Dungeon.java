package uk.co.samatkins.dungeon.play;

import java.io.IOException;
import java.io.InputStream;

import uk.co.samatkins.Entity;
import uk.co.samatkins.Tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dungeon extends Entity {
	
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;
	
	private Tilemap tilemap;
	
	private InputStream is;

	public Dungeon(int width, int height) {
		super();
		
		sprite = tilemap = new Tilemap(new Texture(Gdx.files.internal("cyberpunk/tiles.png")), TILE_WIDTH, TILE_HEIGHT, width, height);
		tilemap.setTileRect(0, width-1, 0, height-1, 16); // Set all to void
		
		this.buildDungeon("cyberpunk/Zabutom_-_Zeta_force_level_2.mp3");
	}
	
	private void buildDungeon(String filename) {
		is = Gdx.files.internal(filename).read();
		int x1 = getShortFromFile() % 20,
			y1 = getShortFromFile() % 20;
		this.buildRoom(x1, x1 + 5 + getShortFromFile() % 10, y1, y1 + 5 + getShortFromFile() % 10);
	}
	
	private int getShortFromFile() {
		int n;
		try {
			n = (is.read() << 8) + is.read();
			return n;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	private void placeWall(int x, int y) {
		tilemap.setTile(x, y, 0);
		tilemap.setSolid(x, y, true);
	}
	
	private void buildRoom(int x1, int x2, int y1, int y2) {
		// Top and bottom
		for (int x=x1; x<=x2; x++) {
			this.placeWall(x, y1);
			this.placeWall(x, y2);
		}
		
		// Left and right
		for (int y=y1; y<=y2; y++) {
			this.placeWall(x1, y);
			this.placeWall(x2, y);
		}
	}

}
