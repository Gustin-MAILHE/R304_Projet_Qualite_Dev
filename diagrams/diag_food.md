```mermaid
classDiagram
    %% --- ENUMS ---
    class FoodType {
    <<enumeration>>
    MEAT
    FISH
    VEGETABLE
    FRUIT
    CONDIMENT
    DRINK
    SPECIAL
    }

    class FreshnessLevel {
        <<enumeration>>
        FRESH
        PASSABLY_FRESH
        NOT_FRESH
    }

    class PotionEffect {
        <<enumeration>>
        SUPER_STRENGTH
        INVINCIBILITY
        PERMANENT_EFFECT
        GRANITE_STATUE
        DUPLICATION
        METAMORPHOSIS_WEREWOLF
    }

    %% --- CLASSES PRINCIPALES ---

    class Food {
        -name: String
        -type: FoodType
        -nutritionalValue: int
        -freshness: FreshnessLevel
        +Food(name, type, freshness)
        +decay() void
        +isEdible() boolean
        +getFreshness() FreshnessLevel
        +getType() FoodType
    }

    class MagicPotion {
        -dosesRemaining: int
        -effects: List~PotionEffect~
        +MagicPotion(ingredients: List~Food~)
        +drinkDose() List~PotionEffect~
        +isEmpty() boolean
        -determineEffects(ingredients: List~Food~) void
    }

    %% --- RELATIONS ---

    MagicPotion o-- "0..*" Food : consists of
    Food ..> FoodType : has
    Food ..> FreshnessLevel : has
    MagicPotion ..> PotionEffect : causes
```