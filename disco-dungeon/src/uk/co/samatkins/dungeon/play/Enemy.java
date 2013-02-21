package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;
import uk.co.samatkins.dungeon.data.EnemyData;

public class Enemy extends DungeonEntity {

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		// TODO Auto-generated constructor stub
		//this.sprite = new Sprite(new Texture(Gdx.files.internal("neon/entities.png")), 0, 0, Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT);
	}
	
	public static Enemy create(EnemyData data, Dungeon dungeon, int x, int y) {
		Enemy e = new Enemy(dungeon, x, y);
		
		e.hp = e.maxHp = data.getHp();
		e.name = data.getName();
		AnimatedSprite ani = new AnimatedSprite(AssetManager.getInstance().getEntitiesTexture(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT, 5);
		ani.addAnimation("idle", data.getTileIDs());
		ani.play("idle");
		e.sprite = ani;
		
		return e;
	}
	
	@Override
	public void takeTurn() {
		System.out.println("Enemy took a turn!");
		int direction = (int) Math.floor(Math.random() * 4);
		switch (direction) {
		case 0:
			this.moveUp();
			break;
		case 1:
			this.moveRight();
			break;
		case 2:
			this.moveDown();
			break;
		case 3:
			this.moveLeft();
			break;
		}
	}

}
