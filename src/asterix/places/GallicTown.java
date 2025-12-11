package asterix.places;

import asterix.characters.Character;
import asterix.characters.Gallic;

public class GallicTown extends Place {

	GallicTown(String name, double size, Character clanChief) {
		super(name, size, clanChief);
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		//TO-DO : add the verification with the werewolf too
		return (c instanceof Gallic);
	}
	
	

}
