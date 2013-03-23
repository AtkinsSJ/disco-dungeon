package uk.co.samatkins.dungeon.play.effects;

import uk.co.samatkins.dungeon.play.DungeonEntity;

public abstract class StatusEffect {

	protected int turnsLeft;
	
	public StatusEffect(int turns) {
		this.turnsLeft = turns;
	}
	
	public int getTurnsRemaining() {
		return this.turnsLeft;
	}
	
	public void setTurnsRemaining(int turnsRemaining) {
		this.turnsLeft = turnsRemaining;
	}
	
	public void atEndOfTurn(DungeonEntity e) {
		this.turnsLeft--;
	}

}
