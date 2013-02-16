package uk.co.samatkins;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class World extends Stage implements Screen {
	
	public World() {
		//float zoom = 0.5f;
		super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(this);
		//((OrthographicCamera)this.stage.getCamera()).zoom = zoom;
	}
	
	public void add(Entity entity) {
		this.addActor(entity);
		entity.setWorld(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		this.update(delta);
		this.draw();
	}

	abstract public void update(float delta);

	@Override
	public void resize(int width, int height) {
		this.setViewport(width, height, false);
	}

	@Override
	public void show() {
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
