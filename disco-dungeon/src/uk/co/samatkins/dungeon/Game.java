package uk.co.samatkins.dungeon;

import uk.co.samatkins.dungeon.play.PlayWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class Game extends com.badlogic.gdx.Game {
	
	@Override
	public void create() {
		
//		Gson gson = new Gson();
//		try {
//			EnemyData[] enemyData = gson.fromJson(Gdx.files.internal("cyberpunk/enemies.json").reader(), EnemyData[].class);
//			for (EnemyData enemy : enemyData) {
//				System.out.println(enemy);
//			}
//		} catch (JsonSyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonIOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		this.setScreen(new PlayWorld());
		
	}

}
