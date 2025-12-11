package asterix.characters;

import java.util.ArrayList;
import java.util.List;

import asterix.food.*;


public abstract class Character {
	private String name;
	private String sexe;
	private long height;
	private long age;
	private long strength;
	private long baseStrength;
	private long stamina;
	private long health;
	private long hunger;
	private long fightWill;
	private long potionLevel = 0;
	private boolean isInvincible = false;
	private boolean isStatue = false;
	private boolean permanentMagicEffects = false;
	private Food lastFood;
	private ArrayList<Food> triedFood;
	private ArrayList<Food> likedFood;
	
	public Character(String name, String sexe, long height, long age, long strength) {
		this.name = name;
		this.sexe = sexe;
		this.height = height;
		this.age = age;
		this.strength = strength;
		this.baseStrength = strength;
	}
	
	public void fightAgainst(Character enemy) {
		if (!enemy.isInvincible) {
			long damagedDealed = Math.max(0, (this.strength * this.stamina/100) - (enemy.stamina/10));
			enemy.setHealth(enemy.getHealth() -  damagedDealed);
			if (enemy.getHealth() <= 0) {
				enemy.dead();
			}
		}
	}
	
	public void heal(long improve) {
		long newHealth = Math.max(100, this.getHealth() + improve);
		this.setHealth(newHealth);
	}

	public void eat(Food food) {
		long newHunger = Math.max(100, this.getHunger() + food.getNutritionalValue());
		this.setHunger(newHunger);
		if (food.getFreshness() == FreshnessLevel.NOT_FRESH || this.getLastFood().getType().equals(FoodType.VEGETABLE) && food.getType().equals(FoodType.VEGETABLE)) {
			this.setHealth(this.getHealth() - food.getNutritionalValue());
			if  (this.getHealth() <= 0) {
				this.dead();
			}
		}
		this.setLastFood(food);
	}

	/**
	 * Fait boire une quantité spécifique de doses de potion au personnage.
	 * @param potion La marmite de potion.
	 * @param dosesToDrink Le nombre de doses que le personnage VEUT boire (entre 1 et 10).
	 */
	public void drinkPotion(MagicPotion potion, int dosesToDrink) {
		// 1. Vérifications d'usage (Statue, Mort, Chaudron vide ou aucune dose demandée)
		if (this.isStatue || this.health <= 0 || potion.isEmpty() || dosesToDrink < 1) {
			return;
		}

		// On limite au nombre de doses restantes dans le chaudron
		if (dosesToDrink > potion.getDosesRemaining()) {
			dosesToDrink = potion.getDosesRemaining();
		}

		// 3. Consommation effective des doses
		int dosesConsumed = 0;
		List<PotionEffect> lastEffects = null;

		for (int i = 0; i < dosesToDrink; i++) {
			// On récupère les effets (qui sont identiques pour toutes les doses de la même marmite)
			lastEffects = potion.drinkDose();
			dosesConsumed++;
		}

		// 4. Mise à jour de l'état
		if (dosesConsumed > 0 && lastEffects != null) {
			this.potionLevel += dosesConsumed;

			// Appel de la méthode de gestion des effets avec les effets de la potion bue
			applyPotionEffects(lastEffects);
		}
	}

	/**
	 * Applique les effets et gère les seuils (Permanence / Statue) en fonction du potionLevel.
	 * Cette méthode est appelée après avoir bu.
	 * * @param effects La liste des effets contenus dans la potion.
	 */
	private void applyPotionEffects(List<PotionEffect> effects) {
		// --- CAS CRITIQUE : Seuil de 20 (2 marmites) -> Statue de Granit ---
		if (this.potionLevel >= 20) {
			if (!this.isStatue) {
				this.isStatue = true;
				this.setStrength(0);
				this.isInvincible = true;
			}
		}

		// --- CAS PERMANENT : Seuil de 10 (1 marmite) -> Effets permanents ---
		if (this.potionLevel >= 10) {
			if (!this.permanentMagicEffects) {
				this.permanentMagicEffects = true;
			}
		}

		// --- APPLICATION DES EFFETS (Force, Invincibilité, Spéciaux) ---
		// Ces effets s'appliquent que ce soit temporaire ou permanent.

		for (PotionEffect effect : effects) {
			switch (effect) {
				case SUPER_STRENGTH:
					// On applique le boost seulement si on n'est pas déjà boosté
					if (this.strength <= this.baseStrength) {
						this.strength += 100; // Valeur arbitraire de boost
					}
					break;

				case INVINCIBILITY:
					this.isInvincible = true;
					break;

				case DUPLICATION:
					System.out.println("Effet spécial : Dédoublement !");
					// Logique de duplication à implémenter
					break;

				case METAMORPHOSIS_WEREWOLF:
					System.out.println("Effet spécial : Ahouuuu (Loup-garou) !");
					// Logique de métamorphose à implémenter
					break;

				default:
					break;
			}
		}
	}
	
	public void dead() {
		this.setHealth(0);
	}

	/**
	 * Appelé par le Théâtre à chaque tour.
	 * Gère la digestion (faim) et la dissipation de la potion magique.
	 * [cite: 112]
	 */
	public void timePasses() {
		// 1. Gestion de la Potion
		if (this.potionLevel > 0) {
			// Si les effets ne sont PAS permanents et qu'on n'est PAS une statue
			if (!this.permanentMagicEffects && !this.isStatue) {
				this.potionLevel--;

				// Si le niveau retombe à 0, on perd les bonus
				if (this.potionLevel == 0) {
					System.out.println(this.name + " ne ressent plus les effets de la potion.");
					this.strength = this.baseStrength; // Rétablissement force normale
					this.isInvincible = false;       // Fin invincibilité
				}
			}
			// Si effectsPermanent est true, on ne décrémente pas
		}

		// 2. Gestion de la Faim (Exemple simple)
		this.setHunger(this.getHunger() - 10);
		if (this.getHunger() <= 0){
			this.setHunger(0);
			this.setHealth(this.getHealth() - 10);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public long getStrength() {
		return strength;
	}

	public void setStrength(long strength) {
		this.strength = strength;
	}

	public long getStamina() {
		return stamina;
	}

	public void setStamina(long stamina) {
		this.stamina = stamina;
	}

	public long getHealth() {
		return health;
	}

	public void setHealth(long health) {
		this.health = health;
	}

	public long getHunger() {
		return hunger;
	}

	public void setHunger(long hunger) {
		this.hunger = hunger;
	}

	public long getFightWill() {
		return fightWill;
	}

	public void setFightWill(long fightWill) {
		this.fightWill = fightWill;
	}

	public long getPotionLevel() {
		return potionLevel;
	}

	public void setPotionLevel(long potionLevel) {
		this.potionLevel = potionLevel;
	}
	
	public Food getLastFood() {
		return lastFood;
	}
	
	public void setLastFood(Food food) {
		this.lastFood = food;
	}

	public ArrayList<Food> getTriedFood() {
		return triedFood;
	}

	public void setTriedFood(ArrayList<Food> triedFood) {
		this.triedFood = triedFood;
	}

	public ArrayList<Food> getLikedFood() {
		return likedFood;
	}

	public void setLikedFood(ArrayList<Food> likedFood) {
		this.likedFood = likedFood;
	}
	
}
