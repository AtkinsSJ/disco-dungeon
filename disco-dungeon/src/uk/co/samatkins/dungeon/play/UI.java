package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.Entity;
import uk.co.samatkins.dungeon.data.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class UI extends Entity {
	
	private Player player;
	private float playerHealthPercent;
	
	private ShapeRenderer shape;
	
	private Texture uiTexture;

	public UI() {
		this.playerHealthPercent = 100;
		this.uiTexture = AssetManager.getInstance().getUITexture();
		this.shape = new ShapeRenderer();
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
		batch.end();
		
		this.renderShadows();
		
		batch.begin();
		// ==== HEALTH BAR ====
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
	
	private void renderShadows() {
		// Enable blending
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		this.shape.setProjectionMatrix(this.world.getCamera().combined);
		
		this.shape.begin(ShapeType.FilledRectangle);
		
		
		this.shape.setColor(0, 0, 0, 0.5f);
		this.shape.filledRect(this.getX(), this.getY(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT);
		this.shape.end();
		
		// Disable it again
		Gdx.gl.glDisable(GL10.GL_BLEND);
	}

}
