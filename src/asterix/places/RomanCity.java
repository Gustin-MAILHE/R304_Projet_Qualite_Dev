package asterix.places;

import asterix.characters.Character;
import asterix.characters.Roman;

public class RomanCity extends Place {

	RomanCity(String name, double size, Character clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		// TODO: add werewolf ahaha
		return c instanceof Roman;
	}

}
