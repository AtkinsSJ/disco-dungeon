package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import uk.co.samatkins.Entity;

public class Player extends Entity {
	
	public Player() {
		super("player");
		
		this.sprite = new Sprite(new Texture(Gdx.files.internal("cyberpunk/tiles.png")), 0, 0, 16, 16);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		x = Gdx.input.getX();
		y = Gdx.graphics.getHeight() - Gdx.input.getY();
		
		this.visible = !Gdx.input.isButtonPressed(Buttons.LEFT);
	}

}
