package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.math.Vector3;

import uk.co.samatkins.World;
import uk.co.samatkins.dungeon.play.dungeon.Room;

public class PlayWorld extends World {
	
	private boolean playersTurn;
	private Dungeon dungeon;
	private Player player;

	public PlayWorld() {
		super();
		this.dungeon = new Dungeon(100, 100);
		this.add(this.dungeon);
		
		Room startRoom = this.dungeon.getStartRoom();
		
		this.player = new Player(this, this.dungeon, startRoom.getCentreX(), startRoom.getCentreY());
		this.add(player);
		this.setKeyboardFocus(player);
		this.playersTurn = true;
	}
	
	public void update(float delta) {
		super.act(delta);
	}
	
	public void draw() {
		Vector3 position = this.getCamera().position;
		position.set(player.getX(), player.getY(), position.z);
		super.draw();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (this.playersTurn) {
			return super.keyDown(keycode);
		}
		System.out.println("Not the player's turn!");
		return false;
	}
	
	/**
	 * Called after the player acts. Have any non-player entities move.
	 */
	public void endPlayerTurn() {
		this.playersTurn = false;
	}
}
