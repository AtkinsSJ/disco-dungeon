package uk.co.samatkins.dungeon.play;

import java.util.ArrayList;

import uk.co.samatkins.Entity;
import uk.co.samatkins.FileRNG;
import uk.co.samatkins.Tilemap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dungeon extends Entity {
	
	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;
	
	private Tilemap tilemap;

	public Dungeon(int width, int height) {
		super();
		
		sprite = tilemap = new Tilemap(new Texture(Gdx.files.internal("cyberpunk/tiles.png")), TILE_WIDTH, TILE_HEIGHT, width, height);
		tilemap.setTileRect(0, width-1, 0, height-1, -1); // Set all to void
		
		this.buildDungeon("cyberpunk/Zabutom_-_Zeta_force_level_2.mp3");
	}
	
	public void buildDungeon(String filename) {
		tilemap.clear();
		
		FileRNG random = new FileRNG(Gdx.files.internal(filename).read());
		
		// Create starting lists
		int gridSize = 4,
			gridCount = gridSize * gridSize;
		int[] graph = new int[gridCount];
			// graph[node] will contain its connections OR'd together 1=up, 2=right, 4=down, 8=left
		ArrayList<Integer> connected = new ArrayList<Integer>(gridCount);
		ArrayList<Integer> unconnected = new ArrayList<Integer>(gridCount);
		for (int i=0; i<gridCount; i++) {
			unconnected.add(i);
		}
		
		int start = random.getIntBetween(0, unconnected.size()-1);
		unconnected.remove((Object)start);
		connected.add(start);
		System.out.println("First node: " + start);
		
		// Repeatedly pick a node that's connected, and connect it to one of its neighbours
		// until all are connected.
		int a,b=0,direction;
		while (unconnected.size() > 0) {
			a = connected.get(random.getIntBetween(0, connected.size()-1));
			
			// If a fully connected, skip it
			if (graph[a] == 15) {
				System.out.println("Skipping " + a);
				continue;
			}
			
			// b must neighbour a
			direction = random.getIntBetween(0, 3);
			switch (direction) {
			case 0: // Up
				if (a < gridSize) {
					continue;
				} else {
					b = a - gridSize;
					graph[a] = graph[a] | 1;
					graph[b] = graph[b] | 4;
				}
				break;
			case 1: // Right
				if (a % gridSize == (gridSize-1)) {
					continue;
				} else {
					b = a + 1;
					graph[a] = graph[a] | 2;
					graph[b] = graph[b] | 8;
				}
				break;
			case 2: // Down
				if (a >= (gridCount - gridSize)) {
					continue;
				} else {
					b = a + gridSize;
					graph[a] = graph[a] | 4;
					graph[b] = graph[b] | 1;
				}
				break;
			case 3: // Left
				if (a % gridSize == 0) {
					continue;
				} else {
					b = a - 1;
					graph[a] = graph[a] | 8;
					graph[b] = graph[b] | 2;
				}
				break;
			}
			
			connected.add(b);
			unconnected.remove((Object)b);
			
			System.out.println("Connecting " + a + " to " + b);
		}
		
		// Calculate size of 'chunks' that nodes fit into
		int chunkWidth = (int)Math.floor(tilemap.getTilesAcross() / gridSize),
			chunkHeight = (int)Math.floor(tilemap.getTilesDown() / gridSize);
		
		int l,t,w,h;
		for (int x=0; x<gridSize; x++) {
			for (int y=0; y<gridSize; y++) {
				l = (x * chunkWidth) + random.getIntBetween(0, chunkWidth/2);
				t = (y * chunkHeight) + random.getIntBetween(0, chunkHeight/2);
				w = random.getIntBetween(3, chunkWidth-l);
				h = random.getIntBetween(3, chunkHeight-t);
				
				this.buildRoom(l, t, w, h);
			}
		}
		
//		int x1, y1;
//		
//		for (int i=0; i<100; i++) {
//			x1 = random.getIntBetween(0, this.tilemap.getTilesAcross() - 20);
//			y1 = random.getIntBetween(0, this.tilemap.getTilesDown() - 20);
//			this.buildRoom(x1, x1 + random.getIntBetween(3, 10), y1, y1 + random.getIntBetween(3, 10));
//		}
//		
//		// Get wall tiles to figure-out how they should look
//		for (int x=0; x<this.tilemap.getTilesAcross(); x++) {
//			for (int y=0; y<this.tilemap.getTilesDown(); y++) {
//				if (this.tilemap.getSolid(x, y)) {
//					int wallTile = 0
//							| (this.tilemap.getSolid(x, y+1) ? 1 : 0)
//							| (this.tilemap.getSolid(x+1, y) ? 2 : 0)
//							| (this.tilemap.getSolid(x, y-1) ? 4 : 0)
//							| (this.tilemap.getSolid(x-1, y) ? 8 : 0);
//					this.tilemap.setTile(x, y, wallTile);
//				}
//			}
//		}
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
	
	private void buildRoom(int left, int top, int width, int height) {
		
		int right = left + width,
			bottom = top + height;
		
		// Top and bottom
		for (int x=left; x <= right; x++) {
			this.placeWall(x, top);
			this.placeWall(x, bottom);
		}
		
		// Left and right
		for (int y=top; y <= bottom; y++) {
			this.placeWall(left, y);
			this.placeWall(right, y);
		}
		
		// Floor!
		for (int x=left + 1; x < right; x++) {
			for (int y=top+1; y<bottom; y++) {
				this.placeFloor(x, y);
			}
		}
	}

}
