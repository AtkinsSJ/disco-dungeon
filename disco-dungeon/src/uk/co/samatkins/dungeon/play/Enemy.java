package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;
import uk.co.samatkins.dungeon.data.EnemyData;

public class Enemy extends DungeonEntity {
	
	public static enum Movement {
		RANDOM {
			void move(Enemy e) {
				int direction = (int) Math.floor(Math.random() * 4);
				switch (direction) {
				case 0:
					e.moveUp();
					break;
				case 1:
					e.moveRight();
					break;
				case 2:
					e.moveDown();
					break;
				case 3:
					e.moveLeft();
					break;
				}
			}
		},
		BEELINE {
			void move(Enemy e) {
				e.moveTowards(e.dungeon.player.tileX, e.dungeon.player.tileY);
			} 
		};
		
		abstract void move(Enemy e);
	};
	
	private int attack;
	
	private Movement movement;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
	}
	
	public static Enemy create(EnemyData data, Dungeon dungeon, int x, int y) {
		Enemy e = new Enemy(dungeon, x, y);
		
		e.hp = e.maxHp = data.getHp();
		e.attack = data.getAttack();
		e.name = data.getName();
		e.movement = data.getMovement();
		
		AnimatedSprite ani = new AnimatedSprite(AssetManager.getInstance().getEntitiesTexture(), Dungeon.TILE_WIDTH, Dungeon.TILE_HEIGHT, 5);
		ani.addAnimation("idle", data.getTileIDs());
		ani.play("idle");
		e.sprite = ani;
		
		return e;
	}
	
	@Override
	public void takeTurn() {
		// Is the player next to us?
		if (this.distanceToEntity(this.dungeon.player) == 1) {
			this.attack(this.dungeon.player);
		} else {
			this.movement.move(this);
		}
	}
	
	private void attack(DungeonEntity e) {
		e.takeDamage(this.attack);
	}

}
