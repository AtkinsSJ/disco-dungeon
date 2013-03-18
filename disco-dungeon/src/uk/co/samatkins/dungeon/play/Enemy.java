package uk.co.samatkins.dungeon.play;

import uk.co.samatkins.AnimatedSprite;
import uk.co.samatkins.dungeon.data.AssetManager;
import uk.co.samatkins.dungeon.data.EnemyData;

import com.badlogic.gdx.Gdx;

public class Enemy extends DungeonEntity {
	
//	public static enum Movement {
//		RANDOM {
//			void move(Enemy e) {
//				int direction = (int) Math.floor(Math.random() * 4);
//				switch (direction) {
//				case 0:
//					e.moveUp();
//					break;
//				case 1:
//					e.moveRight();
//					break;
//				case 2:
//					e.moveDown();
//					break;
//				case 3:
//					e.moveLeft();
//					break;
//				}
//			}
//		},
//		BEELINE {
//			void move(Enemy e) {
//				e.moveTowards(e.dungeon.player.tileX, e.dungeon.player.tileY);
//			} 
//		};
//		
//		abstract void move(Enemy e);
//	};
//	private Movement movement;
	
	/**
	 * State enum, controls enemy behaviour
	 * @author Sam
	 *
	 */
	public static enum State {
		IDLE {
			State run(Enemy e) {
				// Move randomly (50% chance of standing still)
				int direction = (int) Math.floor(Math.random() * 8);
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
				default:
					// Do nothing!
					break;
				}
				
				// Can we see the player?
				if (e.canSee(e.dungeon.player)) {
					// Run at them foolishly!
					Gdx.app.log("AI", e.getName() + " is now approaching player.");
					return APPROACH;
				}
				
				return this;
			}
		},
		APPROACH {
			State run(Enemy e) {
				e.moveTowards(e.dungeon.player);
				return this;
			}
		},
		RETREAT {
			State run(Enemy e) {
				e.moveAwayFrom(e.dungeon.player);
				return this;
			}
		}
		;
		
		/**
		 * Run the AI code for this state
		 * @return the new state
		 */
		abstract State run(Enemy e);
	};
	private State state;
	
	private int attack;

	public Enemy(Dungeon dungeon, int x, int y) {
		super(dungeon, x, y);
		this.state = State.IDLE;
	}
	
	public static Enemy create(EnemyData data, Dungeon dungeon, int x, int y) {
		Enemy e = new Enemy(dungeon, x, y);
		
		e.hp = e.maxHp = data.getHp();
		e.attack = data.getAttack();
		e.name = data.getName();
		
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
			this.state = this.state.run(this);
		}
	}
	
	private void attack(DungeonEntity e) {
		e.takeDamage(this.attack);
	}

	@Override
	public boolean interactWith(DungeonEntity user) {
		if (user instanceof Player) {
			((Player)user).attack(this);
			return true;
		}
		
		return false;
	}

}
