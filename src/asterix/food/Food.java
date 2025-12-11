package asterix.food;

/**
 * Représente un aliment dans l'application.
 * Correspond à la classe Food du diagramme UML.
 */
public class Food {
    private String name;
    private FoodType type;
    private int nutritionalValue;
    private FreshnessLevel freshness;

    /**
     * Constructeur pour la classe Food.
     * @param name Le nom de l'aliment.
     * @param type Le type de l'aliment (FoodType).
     * @param freshness Le niveau de fraîcheur de l'aliment (FreshnessLevel).
     */
    public Food(String name, FoodType type, FreshnessLevel freshness) {
        this.name = name;
        this.type = type;
        this.freshness = freshness;
        // Définir une valeur nutritionnelle de base, peut être ajustée selon le type/la fraîcheur
        this.nutritionalValue = calculateNutritionalValue(type, freshness);
    }

    /**
     * Fait "vieillir" l'aliment, diminuant potentiellement sa fraîcheur.
     */
    public void decay() {
        if (this.freshness == FreshnessLevel.FRESH) {
            this.freshness = FreshnessLevel.PASSABLY_FRESH;
        } else if (this.freshness == FreshnessLevel.PASSABLY_FRESH) {
            this.freshness = FreshnessLevel.NOT_FRESH;
        }
        // Recalculer la valeur nutritionnelle après changement de fraîcheur
        this.nutritionalValue = calculateNutritionalValue(this.type, this.freshness);
    }

    /**
     * Calcule la valeur nutritionnelle en fonction du type et de la fraîcheur.
     */
    private int calculateNutritionalValue(FoodType type, FreshnessLevel freshness) {
        int baseValue;
        switch (type) {
            case MEAT:
            case FISH:
                baseValue = 50;
                break;
            case VEGETABLE:
            case FRUIT:
                baseValue = 30;
                break;
            case DRINK:
                baseValue = 20;
                break;
            case CONDIMENT:
            case SPECIAL:
            default:
                baseValue = 10;
                break;
        }

        // Ajustement selon la fraîcheur
        if (freshness == FreshnessLevel.NOT_FRESH) {
            return baseValue / 5;
        } else if (freshness == FreshnessLevel.PASSABLY_FRESH) {
            return baseValue / 2;
        }
        return baseValue;
    }

    // Getters

    public String getName() {
        return name;
    }

    public FoodType getType() {
        return type;
    }

    public int getNutritionalValue() {
        return nutritionalValue;
    }

    public FreshnessLevel getFreshness() {
        return freshness;
    }
}