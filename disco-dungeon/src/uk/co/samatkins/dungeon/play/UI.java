package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.Entity;
import uk.co.samatkins.dungeon.data.AssetManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class UI extends Entity {
	
	private Player player;
	private float playerHealthPercent;
	
	private Texture uiTexture;

	public UI() {
		this.playerHealthPercent = 100;
		this.uiTexture = AssetManager.getInstance().getUITexture();
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		this.playerHealthPercent = ((float)this.player.getHp() / (float)this.player.getMaxHp());
		if (this.playerHealthPercent < 0) { this.playerHealthPercent = 0; }

	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Camera cam = this.world.getCamera();
		this.setPosition(cam.position.x - (cam.viewportWidth/2),
				cam.position.y - (cam.viewportHeight/2));
		
		// Draw red background
		batch.draw(this.uiTexture,
				this.getX(), this.getY() + cam.viewportHeight - 8,
				0, 0,
				64, 8);
		// Draw green bar
		batch.draw(this.uiTexture,
				this.getX(), this.getY() + cam.viewportHeight - 8,
				0, 8,
				(int)Math.floor(64 * this.playerHealthPercent), 8);
	}

}
