package asterix.characters;

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
