package asterix.places;

import asterix.characters.Character;
import asterix.characters.ClanChief;
import asterix.characters.Roman;

public class RomanCity extends Place {

	public RomanCity(String name, double size, ClanChief clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		// TODO: add werewolf ahaha
		return c instanceof Roman;
	}

}
