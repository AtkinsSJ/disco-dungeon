package uk.co.samatkins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World extends Stage implements Screen {
	
	public World() {
		//float zoom = 0.5f;
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		//((OrthographicCamera)this.stage.getCamera()).zoom = zoom;
	}
	
	public void add(Entity entity) {
		this.addActor(entity);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		this.act(delta);
		this.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.setViewport(width, height, false);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
