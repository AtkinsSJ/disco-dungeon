package uk.co.samatkins.dungeon.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.co.samatkins.Entity;
import uk.co.samatkins.HSVColor;
import uk.co.samatkins.dungeon.data.AssetManager;

public class DiscoLights extends Entity {
	
	private HSVColor color;

	public DiscoLights() {
		this.sprite = new Sprite(AssetManager.getInstance().getDiscoLightsTexture());
		
		this.sprite.scale(
				Math.max(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
				/ this.sprite.getHeight() );
		
		this.color = new HSVColor(60, 1, 1);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		this.sprite.rotate(delta * 30.0f);
		this.color.rotate(delta * 20.0f);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		Camera cam = this.world.getCamera();
		this.setPosition(cam.position.x - this.sprite.getWidth()/2,
				cam.position.y - this.sprite.getHeight()/2);
		
		this.sprite.setColor(this.color.toRGB(0.5f));
		
		super.draw(batch, parentAlpha);
	}

}
