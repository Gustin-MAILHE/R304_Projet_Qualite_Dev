package asterix.places;

import asterix.characters.Character;
import asterix.characters.ClanChief;
import asterix.characters.Gallic;
import asterix.characters.Roman;

public class GalloRomanVillage extends Place {

	public GalloRomanVillage(String name, double size, ClanChief clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		return (c instanceof Gallic || c instanceof Roman);
	}

}
