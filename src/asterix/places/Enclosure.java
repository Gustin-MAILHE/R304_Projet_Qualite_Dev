package asterix.places;

import asterix.characters.Character;
import asterix.characters.ClanChief;

public class Enclosure extends Place {

	public Enclosure(String name, double size, ClanChief clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		// TODO : werewolf once again
		return false;
	}

}
