package asterix.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodFactory {

    // Liste constante des noms pour pouvoir piocher dedans aléatoirement
    public static final String[] FOOD_NAMES = {
            "Sanglier", "Poisson", "Gui",
            "Homard", "Fraises", "Carottes", "Sel", "Trèfle à quatre feuilles",
            "Jus de betterave", "Huile de roche",
            "Miel", "Vin", "Hydromel", "Lait de licorne à deux têtes",
            "Poils d'Idéfix", "Ingrédient secret"
    };

    /**
     * Crée une NOUVELLE instance d'un aliment basé sur son nom.
     * @param name Le nom de l'aliment souhaité.
     * @return Un nouvel objet Food ou null si le nom n'est pas reconnu.
     */
    public static Food createFood(String name) {
        switch (name) {
            // --- VIANDES & POISSONS ---
            case "Sanglier":
                return new Food("Sanglier", FoodType.MEAT, FreshnessLevel.FRESH);
            case "Poisson":
                return new Food("Poisson", FoodType.FISH, FreshnessLevel.FRESH);
            case "Homard":
                return new Food("Homard", FoodType.FISH, FreshnessLevel.FRESH);

            // --- LÉGUMES & FRUITS ---
            case "Carottes":
                return new Food("Carottes", FoodType.VEGETABLE, FreshnessLevel.FRESH);
            case "Gui":
                return new Food("Gui", FoodType.VEGETABLE, FreshnessLevel.FRESH);
            case "Trèfle à quatre feuilles":
                return new Food("Trèfle à quatre feuilles frais", FoodType.VEGETABLE, FreshnessLevel.FRESH);
            case "Jus de betterave":
                return new Food("Jus de betterave", FoodType.VEGETABLE, FreshnessLevel.FRESH);
            case "Fraises":
                return new Food("Fraises", FoodType.FRUIT, FreshnessLevel.FRESH);

            // --- BOISSONS ---
            case "Vin":
                return new Food("Vin", FoodType.DRINK, FreshnessLevel.FRESH);
            case "Hydromel":
                return new Food("Hydromel", FoodType.DRINK, FreshnessLevel.FRESH);
            case "Lait de licorne à deux têtes":
                return new Food("Lait de licorne à deux têtes", FoodType.DRINK, FreshnessLevel.FRESH); // Ou SPECIAL

            // --- CONDIMENTS & SPÉCIAUX ---
            case "Sel":
                return new Food("Sel", FoodType.CONDIMENT, FreshnessLevel.FRESH);
            case "Miel":
                return new Food("Miel", FoodType.CONDIMENT, FreshnessLevel.FRESH);
            case "Huile de roche":
                return new Food("Huile de roche", FoodType.CONDIMENT, FreshnessLevel.FRESH);
            case "Poils d'Idéfix":
                return new Food("Poils d'Idéfix", FoodType.SPECIAL, FreshnessLevel.FRESH);
            case "Ingrédient secret":
                return new Food("Ingrédient secret", FoodType.SPECIAL, FreshnessLevel.FRESH);

            default:
                System.out.println("Aliment inconnu : " + name);
                return null;
        }
    }

    /**
     * Méthode utilitaire pour obtenir un aliment aléatoire (pour le spawn dans le Théâtre).
     */
    public static Food createRandomFood() {
        Random rand = new Random();
        String randomName = FOOD_NAMES[rand.nextInt(FOOD_NAMES.length)];
        return createFood(randomName);
    }
}
