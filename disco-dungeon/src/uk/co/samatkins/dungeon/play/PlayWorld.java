package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.math.Vector3;

import uk.co.samatkins.World;
import uk.co.samatkins.dungeon.play.dungeon.Room;

public class PlayWorld extends World {
	
	private Dungeon dungeon;
	private Player player;

	public PlayWorld() {
		super();
		this.dungeon = new Dungeon(100, 100);
		this.add(this.dungeon);
		
		this.player = this.dungeon.player;
		this.add(player);
		this.setKeyboardFocus(player);
	}
	
	public void update(float delta) {
		super.act(delta);
	}
	
	public void draw() {
		Vector3 position = this.getCamera().position;
		position.set(player.getX(), player.getY(), position.z);
		super.draw();
	}
}
