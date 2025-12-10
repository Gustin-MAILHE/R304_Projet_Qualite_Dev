package asterix.characters;

import java.util.ArrayList;

public abstract class Place {
	private String name;
	private double size;
	private Character clanChief;
	private ArrayList<Character> characters;
	private ArrayList<Food> foods;
	
	Place(String name, double size, Character clanChief) {
		this.name = name;	
		this.size = size;
		this.clanChief = clanChief;
	}
	
	public String DisplayInfos() {
		//TO-DO
		return "feur";
	}
	
	public void AddCharacter(Character c) {
		if (CanHaveCharacter(c)) {
			characters.add(c);
		}
	}
	
	public void RemoveCharacter(Character c) {
		if (characters.contains(c)) {
			characters.remove(c);
		}
	}
	
	public abstract boolean CanHaveCharacter(Character c);
}
