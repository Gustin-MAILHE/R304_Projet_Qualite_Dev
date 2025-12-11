package asterix.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import asterix.food.Food;


public abstract class Place {
	private String name;
	private double size;
	private Character clanChief;
	private ArrayList<Character> characters;
	private ArrayList<Food> foods = new ArrayList<Food>();
	
	Place(String name, double size, Character clanChief) {
		this.name = name;	
		this.size = size;
		this.clanChief = clanChief;
	}
	
	
	public String getName() {
		return name;
	}

	public double getSize() {
		return size;
	}

	public Character getClanChief() {
		return clanChief;
	}

	public ArrayList<Character> getCharacters() {
		return characters;
	}

	public ArrayList<Food> getFoods() {
		return foods;
	}
	
	
	public String displayInfos() {
		System.out.println("\n╔════════════════════════════════════════════════════════════╗");
	    System.out.println("║          " + this.getClass().getSimpleName().toUpperCase() + "          ");
	    System.out.println("╠════════════════════════════════════════════════════════════╣");
	    System.out.println("║ Nom: " + name);
	    System.out.println("║ Superficie: " + size + " m²");
	    
	    if (clanChief != null) {
	        System.out.println("║ Chef de clan: " + clanChief.getName());
	    } else {
	        System.out.println("║ Chef de clan: Aucun");
	    }
	    
	    System.out.println("║ Nombre de personnages: " + characters.size());
	    System.out.println("╠════════════════════════════════════════════════════════════╣");
	    
	    // Affichage des personnages présents
	    if (characters.isEmpty()) {
	        System.out.println("║ Aucun personnage présent");
	    } else {
	        System.out.println("║ PERSONNAGES PRÉSENTS:");
	        System.out.println("╠════════════════════════════════════════════════════════════╣");
	        for (Character p : characters) {
	            System.out.println("║ ► " + p.getName() + " (" + p.getClass().getSimpleName() + ")");
	            System.out.println("║   ├─ Santé: " + p.getHealth() + "/100");
	            System.out.println("║   ├─ Faim: " + p.getHunger() + "/100");
	            System.out.println("║   └─ Force: " + p.getStrength());
	        }
	    }
	    
	    System.out.println("╠════════════════════════════════════════════════════════════╣");
	    
	    // Affichage des aliments présents
	    if (foods.isEmpty()) {
	        System.out.println("║ Aucun aliment disponible");
	    } else {
	        System.out.println("║ ALIMENTS DISPONIBLES:");
	        System.out.println("╠════════════════════════════════════════════════════════════╣");
	        //for (Food a : foods) {
	        //    System.out.println("║ ► " + a.getName() + " (Valeur nutritive: " + a.getValeurNutritive() + ")");
	        //}
	    }
	    
	    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

		return "feur";
	}
	
	public boolean addCharacter(Character c) {
		if (CanHaveCharacter(c)) {
			characters.add(c);
			return true;
		}
		return false;
	}
	
	public boolean removeCharacter(Character c) {
		if (characters.contains(c)) {
			characters.remove(c);
			return true;
		}
		return false;
	}
	
	public void healCharacters() {
		for (Character c : characters) {
			c.setHealth(100);
		}
	}
	
	public void feedCharacters() {
		for (Character c : characters) {
			c.setHunger(100);
		}
	}
	
	public boolean canCookPotion() {
	    Set<String> foodNames = foods.stream()
	            .map(Food::getName)
	            .collect(Collectors.toSet());

	    List<String> required = List.of("egg", "apple", "carrot", "steak");

	    return foodNames.containsAll(required);
	}

	
	public abstract boolean CanHaveCharacter(Character c);
}
