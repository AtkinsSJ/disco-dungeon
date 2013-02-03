package uk.co.samatkins.dungeon.data;

public class EnemyData {
	private String name;
	private int[] tileIDs;
	private int hp;
	
	public String getName() { return name; }
	public int[] getTileIDs() { return tileIDs; }
	public int getHp() { return hp; }
	
	@Override
	public String toString() {
		return name + ", tiles: " + tileIDs + ", " + hp + " hp."; 
	}
}
