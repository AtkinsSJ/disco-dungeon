package uk.co.samatkins.dungeon;

import uk.co.samatkins.dungeon.play.PlayWorld;

import com.badlogic.gdx.Gdx;

public class Game extends com.badlogic.gdx.Game {
	
	@Override
	public void create() {
		
		Gdx.graphics.setTitle("Disco Dungeon");
		
		this.setScreen(new PlayWorld(this));
		
	}

}
