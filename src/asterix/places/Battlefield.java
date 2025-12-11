package asterix.places;

import asterix.characters.Character;

public class Battlefield extends Place {

	Battlefield(String name, double size, Character clanChief) {
		super(name, size, clanChief);
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		return true;
	}

}
