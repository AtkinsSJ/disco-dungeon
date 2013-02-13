package uk.co.samatkins.dungeon.play.dungeon;

public class Room {
	
	public int x, y, width, height;

	public Room(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		System.out.println("Creating room: " + x + "," + y + "," + width + "," + height);
	}
	
	public int getCentreX() {
		return this.x + (int)Math.floor(this.width/2);
	}
	
	public int getCentreY() {
		return this.y + (int)Math.floor(this.height/2);
	}
	
	public int getLeft() {
		return this.x;
	}
	
	public int getRight() {
		return this.x + this.width;
	}
	
	public int getBottom() {
		return this.y;
	}
	
	public int getTop() {
		return this.y + this.height;
	}

}
