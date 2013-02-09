package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.World;

public class PlayWorld extends World {
	
	private boolean playersTurn;

	public PlayWorld() {
		super();
		//this.add(new Dungeon(15, 10));
		Player p = new Player(3, 3);
		this.add(p);
		this.setKeyboardFocus(p);
		this.playersTurn = true;
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
//		System.out.println(delta);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		System.out.println("Test " + keycode);
		if (this.playersTurn) {
			return super.keyDown(keycode);
		}
		System.out.println("Not the player's turn!");
		return false;
	}
}
