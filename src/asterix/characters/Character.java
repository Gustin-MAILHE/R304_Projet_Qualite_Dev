package asterix.characters;

import java.util.ArrayList;

public abstract class Character {
	private String name;
	private String sexe;
	private long height;
	private long age;
	private long strength;
	private long stamina;
	private long health;
	private long hunger;
	private long fight_will;
	private long potion_level;
	private Food last_food;
	private ArrayList<Food> tried_food;
	private ArrayList<Food> liked_food;
	
	public Character(String name, String sexe, long height, long age, long strength) {
		this.name = name;
		this.sexe = sexe;
		this.height = height;
		this.age = age;
		this.strength = strength;
	}
	
	public void fight_against(Character enemy) {
		long damaged_dealed = Math.max(0, (this.strength * this.stamina/100) - (enemy.stamina/10));
		enemy.setHealth(damaged_dealed);
	}
	
	public void heal(long improve) {
		long new_health = Math.max(100, this.getHealth() + improve);
		this.setHealth(new_health);
	}
	
	public void eat(Food food) {
		long new_hunger = Math.max(100, this.getHunger() + 10);
		this.setHunger(new_hunger);
	}
	
	public void drink_magic_potion(Magic_potion_pot potion) {
		this.setStrength(1000);
		this.setHealth(1000);
	}
	
	public void dead() {
		this.setHealth(0);
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

	public long getFight_will() {
		return fight_will;
	}

	public void setFight_will(long fight_will) {
		this.fight_will = fight_will;
	}

	public long getPotion_level() {
		return potion_level;
	}

	public void setPotion_level(long potion_level) {
		this.potion_level = potion_level;
	}
	
	public Food getLast_food() {
		return last_food;
	}
	
	public void setLast_food(Food food) {
		this.last_food = food;
	}

	public ArrayList<Food> getTried_food() {
		return tried_food;
	}

	public void setTried_food(ArrayList<Food> tried_food) {
		this.tried_food = tried_food;
	}

	public ArrayList<Food> getLiked_food() {
		return liked_food;
	}

	public void setLiked_food(ArrayList<Food> liked_food) {
		this.liked_food = liked_food;
	}
	
}
