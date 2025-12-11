package asterix.places;

import asterix.characters.Character;
import asterix.characters.ClanChief;
import asterix.characters.Gallic;

public class GallicTown extends Place {

	public GallicTown(String name, double size, ClanChief clanChief) {
		super(name, size, clanChief);
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		//TO-DO : add the verification with the werewolf too
		return (c instanceof Gallic);
	}
	
	

}
