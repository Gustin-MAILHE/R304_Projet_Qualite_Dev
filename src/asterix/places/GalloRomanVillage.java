package asterix.places;

import asterix.characters.Character;
import asterix.characters.Gallic;
import asterix.characters.Roman;

public class GalloRomanVillage extends Place {

	GalloRomanVillage(String name, double size, Character clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		return (c instanceof Gallic || c instanceof Roman);
	}

}
