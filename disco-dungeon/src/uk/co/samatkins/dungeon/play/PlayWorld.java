package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.World;
import uk.co.samatkins.dungeon.data.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector3;

public class PlayWorld extends World {
	private Dungeon dungeon;
	private Player player;
	private DiscoLights disco;
	private UI ui;
	
	private Music music;

	public PlayWorld() {
		super();
		
		AssetManager.getInstance().loadTheme("neon");
		
		this.dungeon = new Dungeon(100, 100);
		this.add(this.dungeon);
		
		this.dungeon.buildDungeon("neon/Zabutom_-_Zeta_force_level_2.mp3");
		
		this.player = this.dungeon.player;
		this.add(player);
		this.setKeyboardFocus(player);
		
		this.disco = new DiscoLights();
		this.add(this.disco);
		
		this.ui = new UI();
		this.ui.setPlayer(this.player);
		this.add(this.ui);
		
		this.music = Gdx.audio.newMusic(Gdx.files.internal("neon/Zabutom_-_Zeta_force_level_2.mp3"));
		this.music.play();
	}
	
	public void update(float delta) {
		super.act(delta);
	}
	
	public void draw() {
		Vector3 position = this.getCamera().position;
		position.set(player.getX(), player.getY(), position.z);
		super.draw();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		this.music.dispose();
	}
}
