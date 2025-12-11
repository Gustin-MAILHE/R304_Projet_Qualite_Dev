package asterix.places;

import asterix.characters.Character;
import asterix.characters.ClanChief;
import asterix.characters.General;
import asterix.characters.Legionnaire;

public class RomanCamp extends Place {

	public RomanCamp(String name, double size, ClanChief clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		//TO-DO: add werewolf
		return (c instanceof Legionnaire || c instanceof General);
	}
	
	
}
