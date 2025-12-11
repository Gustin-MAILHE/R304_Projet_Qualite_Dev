package asterix.food;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une marmite de Potion Magique.
 * Correspond à la classe MagicPotion du diagramme UML.
 */
public class MagicPotion {
    private static final int INITIAL_DOSES_IN_CAULDRON = 10;
    private int dosesRemaining;
    private List<PotionEffect> effects;
    private List<Food> ingredients;

    /**
     * Constructeur pour la classe MagicPotion.
     * @param ingredients La liste des aliments utilisés pour concocter la potion.
     */
    public MagicPotion(List<Food> ingredients) {
        this.ingredients = ingredients;
        this.dosesRemaining = INITIAL_DOSES_IN_CAULDRON;
        this.effects = new ArrayList<>();
        determineEffects(ingredients);
    }

    /**
     * Détermine les effets de la potion en fonction des ingrédients.
     * La recette de base donne force surhumaine et invincibilité.
     * @param ingredients La liste des ingrédients.
     */
    private void determineEffects(List<Food> ingredients) {
        // Ingrédients de la recette de base (géré dans la méthode de confection du Druid)
        this.effects.add(PotionEffect.SUPER_STRENGTH);
        this.effects.add(PotionEffect.INVINCIBILITY);

        // Vérification des ajouts spéciaux
        for (Food food : ingredients) {
            if (food.getName().equalsIgnoreCase("lait de licorne à deux têtes")) {
                this.effects.add(PotionEffect.DUPLICATION);
            } else if (food.getName().equalsIgnoreCase("poils d'Idéfix")) {
                this.effects.add(PotionEffect.METAMORPHOSIS_WEREWOLF);
            } else if (food.getName().equalsIgnoreCase("fraises") || food.getName().equalsIgnoreCase("homard") || food.getName().equalsIgnoreCase("jus de betterave")) {
                this.effects.add(PotionEffect.NUTRITIOUS);
            }
        }
    }

    /**
     * Fait boire une dose de potion magique.
     * @return La liste des effets de la dose consommée.
     */
    public List<PotionEffect> drinkDose() {
        if (dosesRemaining >= doseAmount) {
            dosesRemaining = dosesRemaining - doseAmount;
            // On retourne les effets de base + spéciaux, en laissant la gestion du temps au personnage.
            return new ArrayList<>(effects);
        }
        return new ArrayList<>(); // Retourne une liste vide si la marmite est vide
    }

    /**
     * Vérifie si la marmite est vide.
     * @return true si dosesRemaining est 0.
     */
    public boolean isEmpty() {
        return dosesRemaining == 0;
    }

    // Getters

    public int getDosesRemaining() {
        return dosesRemaining;
    }

    public List<PotionEffect> getEffects() {
        return effects;
    }
}