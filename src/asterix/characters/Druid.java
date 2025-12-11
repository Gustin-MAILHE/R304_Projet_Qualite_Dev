package asterix.characters;

import java.util.ArrayList;
import java.util.List;

import asterix.food.Food;
import asterix.food.FoodType;
import asterix.food.FreshnessLevel;
import asterix.food.MagicPotion;

public class Druid extends Gallic implements IWorker, ILeader, IFighter {

	public Druid(String name, String sexe, long height, long age, long strength) {
		super(name, sexe, height, age, strength);
		// TODO Auto-generated constructor stub
	}
	
	public MagicPotion cookMagicPotion(int type) {
	    List<Food> ingredients = new ArrayList<>();

	    // Ingrédients de base
	    ingredients.add(new Food("gui", FoodType.CONDIMENT, FreshnessLevel.FRESH));
	    ingredients.add(new Food("carottes", FoodType.VEGETABLE, FreshnessLevel.FRESH));
	    ingredients.add(new Food("sel", FoodType.CONDIMENT, FreshnessLevel.FRESH));
	    ingredients.add(new Food("trèfle à quatre feuilles frais", FoodType.VEGETABLE, FreshnessLevel.FRESH));
	    ingredients.add(new Food("poisson passablement frais", FoodType.FISH, FreshnessLevel.FRESH));
	    ingredients.add(new Food("miel", FoodType.CONDIMENT, FreshnessLevel.FRESH));
	    ingredients.add(new Food("hydromel", FoodType.CONDIMENT, FreshnessLevel.FRESH));
	    ingredients.add(new Food("ingrédient secret", FoodType.SPECIAL, FreshnessLevel.FRESH));

	    // Huile de roche OU jus de betterave selon type
	    if (type == 3) {
	        ingredients.add(new Food("lait de licorne à deux têtes", FoodType.DRINK, FreshnessLevel.FRESH));
	    }

	    // Ajout d’un ingrédient nourrissant
	    if (type == 2) {
	        ingredients.add(new Food("poils d’Idéfix", FoodType.SPECIAL, FreshnessLevel.FRESH));           // OU fraises
	    }

	    if (type == 1) {
	    	ingredients.add(new Food("fraises", FoodType.FRUIT, FreshnessLevel.FRESH));
	    }
	    return new MagicPotion(ingredients);
	}



	@Override
	public void work() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fight() {
		// TODO Auto-generated method stub
		
	}

}
