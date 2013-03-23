package uk.co.samatkins.dungeon.play.effects;

import uk.co.samatkins.dungeon.play.DungeonEntity;

public class RecoverHealth extends StatusEffect {

	public RecoverHealth(int turns) {
		super(turns);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void atEndOfTurn(DungeonEntity e) {
		e.heal(1);
		super.atEndOfTurn(e);
	}

}
