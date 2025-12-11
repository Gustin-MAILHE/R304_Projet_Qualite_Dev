package asterix.places;

import asterix.characters.Character;
import asterix.characters.ClanChief;

public class Battlefield extends Place {

	public Battlefield(String name, double size, ClanChief clanChief) {
		super(name, size, clanChief);
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		return true;
	}

}
