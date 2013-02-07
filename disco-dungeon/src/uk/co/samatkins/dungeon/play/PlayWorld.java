package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.World;

public class PlayWorld extends World {

	public PlayWorld() {
		super();
		this.add(new Player(3, 3));
		this.add(new Dungeon(15, 10));
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
//		System.out.println(delta);
	}
}
