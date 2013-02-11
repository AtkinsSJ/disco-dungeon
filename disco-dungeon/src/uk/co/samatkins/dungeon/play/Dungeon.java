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
		tilemap.setTileRect(0, width-1, 0, height-1, -1); // Set all to void
		
		this.buildDungeon("cyberpunk/Zabutom_-_Zeta_force_level_2.mp3");
	}
	
	private void buildDungeon(String filename) {
		is = Gdx.files.internal(filename).read();
		int x1, y1;
		
		for (int i=0; i<100; i++) {
			x1 = this.getShortFromFile() % (this.tilemap.getTilesAcross() - 20);
			y1 = this.getShortFromFile() % (this.tilemap.getTilesDown() - 20);
			this.buildRoom(x1, x1 + 3 + this.getShortFromFile() % 10, y1, y1 + 3 + this.getShortFromFile() % 10);
		}
		
		// Get wall tiles to figure-out how they should look
		for (int x=0; x<this.tilemap.getTilesAcross(); x++) {
			for (int y=0; y<this.tilemap.getTilesDown(); y++) {
				if (this.tilemap.getSolid(x, y)) {
					int wallTile = 0
							| (this.tilemap.getSolid(x, y+1) ? 1 : 0)
							| (this.tilemap.getSolid(x+1, y) ? 2 : 0)
							| (this.tilemap.getSolid(x, y-1) ? 4 : 0)
							| (this.tilemap.getSolid(x-1, y) ? 8 : 0);
					this.tilemap.setTile(x, y, wallTile);
				}
			}
		}
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
		if (tilemap.getTile(x, y) < 0) { // Only place if there's no existing tile
			tilemap.setTile(x, y, 0);
			tilemap.setSolid(x, y, true);
		}
	}
	
	private void placeFloor(int x, int y) {
		tilemap.setTile(x, y, 16);
		tilemap.setSolid(x, y, false);
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
		
		// Floor!
		for (int x=x1+1; x < x2; x++) {
			for (int y=y1+1; y<y2; y++) {
				this.placeFloor(x, y);
			}
		}
	}

}
