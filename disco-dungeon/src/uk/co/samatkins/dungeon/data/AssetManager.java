package uk.co.samatkins.dungeon.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.google.gson.*;

public class AssetManager {
	
	private static AssetManager instance;
	
	private Gson gson;
	
	private String theme;
	
	private Texture tiles;
	private Texture entities;
	
	private EnemyData[] enemyData;

	private AssetManager() {
		this.gson = new Gson();
	}
	
	public static AssetManager getInstance() {
		if (AssetManager.instance == null) {
			AssetManager.instance = new AssetManager();
		}
		
		return AssetManager.instance;
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
	
	public int getEnemyTypeCount() {
		return this.enemyData.length;
	}
	
	public EnemyData getEnemyData(int index) {
		if (index < this.enemyData.length) {
			return this.enemyData[index];
		}
		return null;
	}
}
