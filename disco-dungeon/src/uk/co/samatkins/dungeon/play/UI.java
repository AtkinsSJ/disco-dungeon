package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.co.samatkins.Entity;

public class UI extends Entity {
	
	private Player player;
	private float playerHealthPercent;

	public UI() {
		this.playerHealthPercent = 100;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		this.playerHealthPercent = ((float)this.player.getHp() / (float)this.player.getMaxHp()) * 100.0f;
		if (this.playerHealthPercent < 0) { this.playerHealthPercent = 0; }
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
//		System.out.println(this.playerHealthPercent);
	}

}
