package asterix.places;

import asterix.characters.Character;

public class Enclosure extends Place {

	Enclosure(String name, double size, Character clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		// TODO : werewolf once again
		return false;
	}

}
