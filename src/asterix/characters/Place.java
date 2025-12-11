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
