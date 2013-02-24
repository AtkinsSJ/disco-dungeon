package uk.co.samatkins.dungeon.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.google.gson.*;

public class AssetManager {
	
	private static AssetManager instance;
	
	private Gson gson;
	
	private String theme;
	
	private Texture tiles;
	private Texture entities;
	
	private Texture uiTexture;
	
	private EnemyData[] enemyData;
	
	private ParticleEffect particleAttacked;

	private AssetManager() {
		this.gson = new Gson();
		this.loadParticleEffects();
		
		this.uiTexture = new Texture(Gdx.files.internal("ui.png"));
	}
	
	public static AssetManager getInstance() {
		if (AssetManager.instance == null) {
			AssetManager.instance = new AssetManager();
		}
		
		return AssetManager.instance;
	}
	
	private void loadParticleEffects() {
		this.particleAttacked = new ParticleEffect();
		this.particleAttacked.load(Gdx.files.internal("particles/attacked.p"), Gdx.files.internal("particles"));
	}
	
	/**
	 * Load the given theme's assets
	 * @param theme
	 * @return True if successful, false if failed
	 */
	public boolean loadTheme(String theme) {
		
		// Same theme already loaded, so skip
		if ( theme.equals(this.theme) ) {
			return true;
		}
		
		this.tiles = new Texture(Gdx.files.internal(theme + "/tiles.png"));
		this.entities = new Texture(Gdx.files.internal(theme + "/entities.png"));
		
		try {
			enemyData = gson.fromJson(Gdx.files.internal(theme + "/enemies.json").reader(), EnemyData[].class);
			for (EnemyData enemy : enemyData) {
				System.out.println(enemy);
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.theme = null;
			return false;
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.theme = null;
			return false;
		}
		
		this.theme = theme;
		return true;
	}
	
	public Texture getTilesTexture() {
		return this.tiles;
	}
	
	public Texture getEntitiesTexture() {
		return this.entities;
	}
	
	public Texture getUITexture() {
		return this.uiTexture;
	}
	
	public int getEnemyTypeCount() {
		return this.enemyData.length;
	}
	
	public EnemyData getEnemyData(int index) {
		if (index < this.enemyData.length) {
			return this.enemyData[index];
		}
		return null;
	}
	
	public ParticleEffect getParticleAttacked() {
		return new ParticleEffect(this.particleAttacked);
	}
}
