package asterix.characters;

public class RomanCamp extends Place {

	RomanCamp(String name, double size, Character clanChief) {
		super(name, size, clanChief);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean CanHaveCharacter(Character c) {
		//TO-DO: add werewolf
		return (c instanceof Legionnaire || c instanceof General);
	}
	
	
}
