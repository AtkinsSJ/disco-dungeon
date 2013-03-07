package uk.co.samatkins.dungeon.data;

public class EnemyData {
	private String name;
	private int[] tileIDs;
	private int hp;
	private int attack;
	private String[] traits;
	
	public String getName() { return name; }
	public int[] getTileIDs() { return tileIDs; }
	public int getHp() { return hp; }
	public int getAttack() { return attack; }
	public String[] getTraits() { return traits; }
	
	@Override
	public String toString() {
		return name + ", tiles: " + tileIDs + ", " + hp + " hp, " + attack + " attack, "; 
	}
}
