package uk.co.samatkins.dungeon.data;

import uk.co.samatkins.dungeon.play.Enemy.Movement;

public class EnemyData {
	private String name;
	private int[] tileIDs;
	private int hp;
	private int attack;
	private Movement movement;
	
	public String getName() { return name; }
	public int[] getTileIDs() { return tileIDs; }
	public int getHp() { return hp; }
	public int getAttack() { return attack; }
	public Movement getMovement() { return movement; }
	
	@Override
	public String toString() {
		return name + ", tiles: " + tileIDs + ", " + hp + " hp, " + attack + " attack, " + movement + " movement."; 
	}
}
