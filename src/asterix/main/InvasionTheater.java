package asterix.main;

import asterix.characters.*;
import asterix.characters.Character;
import asterix.food.*;
import asterix.places.*;
import asterix.werewolf.Pack;
import asterix.werewolf.Werewolf;
import asterix.places.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InvasionTheater {
    private String name;
    private int maxPlaces;
    private List<Place> places;
    private List<ClanChief> chiefs;
    private Scanner scanner;
    private Random random;

    public InvasionTheater(String name, int maxPlaces) {
        this.name = name;
        this.maxPlaces = maxPlaces; // [cite: 103]
        this.places = new ArrayList<>();
        this.chiefs = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // --- GESTION DES LIEUX ET CHEFS ---

    public void addPlace(Place place) {
        if (places.size() < maxPlaces) {
            this.places.add(place);
        } else {
            System.out.println("Le théâtre est complet !");
        }
    }

    public void addChief(ClanChief chief) {
        this.chiefs.add(chief);
    }

    // --- AFFICHAGE ---

    public void displayState() {
        System.out.println("=== État du Théâtre : " + this.name + " ===");
        System.out.println("Nombre de lieux : " + places.size());
        int totalChars = 0;
        for (Place p : places) {
            p.displayInfos();
            totalChars += p.getCharacters().size();
        }
        System.out.println("Total personnages : " + totalChars);
    }

    // --- BOUCLE PRINCIPALE DE SIMULATION ---

    public void runSimulation() {
        boolean running = true;
        int turn = 1;

        while (running) {
            System.out.println("\n--- TOUR " + turn + " ---");

            // 1. Faire combattre les belligérants (Champs de bataille)
            handleCombats();

            // 2. Modifier l'état des personnages (Faim, Potion, etc.)
            updateCharactersState();

            // 3. Faire apparaître des aliments (Hors champs de bataille)
            spawnFood();

            // 4. Pourrissement des aliments (1 tour sur 2)
            if (turn % 2 == 0) {decayFood();}

            // 5. Action des Chefs de Clan (Utilisateur)
            handleChiefsTurn();

            // Affichage de l'état global
            displayState();

            // Pause ou Arrêt
            System.out.println("Appuyez sur Entrée pour le tour suivant (ou tapez 'exit')...");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) running = false;

            turn++;
        }
    }

    // --- LOGIQUE INTERNE ---

    private void handleCombats() {
        // Les combats ont lieu principalement sur les champs de bataille
        for (Place place : places) {
            // Suppose que Place possède une méthode ou un type pour savoir si c'est un champ de bataille
            if (place instanceof Battlefield) {
                System.out.println("Combats en cours sur " + place.getName() + "...");
                // Logique de combat à implémenter dans Place ou ici :
                // place.startBattle();
            }
        }
    }

    private void updateCharactersState() {
        // Modifier l'état, baisser niveau potion
        for (Place place : places) {
            List<Character> chars = place.getCharacters();
            for (Character c : chars) {
                // Diminue la potion, augmente la faim
                c.timePasses();
            }
        }
    }

    private void spawnFood() {
        // Apparition aliments sauf champs de bataille
        for (Place place : places) {
            if (!(place instanceof Battlefield)) {
                for (int i = 0; i < random.nextInt(5); i++) {
                    place.addFood(FoodFactory.createRandomFood());
                }
            }
        }
        System.out.println("De la nouvelle nourriture est apparue !");
    }

    private void decayFood() {
        for (Place place : places) {
            for (Food food : place.getFoods()) {
                food.decay();
            }
        }
        System.out.println("La nourriture se dégrade...");
    }

    private void handleChiefsTurn() {
        //  Passer la main à un chef de clan
        for (ClanChief chief : chiefs) {
            // Appeler le menu du chef
            chief.displayMenu(scanner);
        }
    }

    // Dans InvasionTheater.java

    public void initializeSimulation() {
        System.out.println("--- Initialisation de l'Armorique (50 av. J.C.) ---");

        // ==========================================
        // 1. CRÉATION DES LIEUX [cite: 58-80]
        // ==========================================

        // Le Village Gaulois (Seulement Gaulois & Créatures)
        GallicTown village = new GallicTown("Village des Irréductibles", 100, null);
        // Note : Si tu as fait des sous-classes (GallicVillage, RomanCamp...), utilise-les ici :
        // Place village = new GallicVillage("Village des Irréductibles", 100);
        this.addPlace(village);

        // Le Camp Romain (Seulement Romains & Créatures) - Camps cités : Babaorum, Aquarium... [cite: 14]
        RomanCamp camp = new RomanCamp("Camp de Babaorum", 150, null);
        this.addPlace(camp);

        // L'Enclos (Seulement Créatures)
        Enclosure foret = new Enclosure("Forêt des Carnutes", 500, null);
        this.addPlace(foret);

        // Le Champ de Bataille (Tout le monde, PAS DE CHEF) [cite: 64, 79]
        Battlefield plaine = new Battlefield("La Grande Plaine", 1000, null);
        this.addPlace(plaine);

        // ==========================================
        // 2. CRÉATION DES CHEFS DE CLAN [cite: 81]
        // ==========================================
        // Les chefs doivent être rattachés à un lieu (sauf champ de bataille).
        // On les crée d'abord, on les assignera aux lieux juste après.

        ClanChief chefGaulois = new ClanChief("Abraracourcix", "M", 50, village);
        ClanChief chefRomain = new ClanChief("Caius Bonus", "M", 45, camp);
        ClanChief chefForet = new ClanChief("Le Gardien des Bêtes", "M", 99, foret);

        this.addChief(chefGaulois);
        village.setClanChief(chefGaulois);
        this.addChief(chefRomain);
        camp.setClanChief(chefRomain);
        this.addChief(chefForet);
        foret.setClanChief(chefForet);

        // ==========================================
        // 3. CRÉATION DES PERSONNAGES [cite: 15-41]
        // ==========================================
        // Rappel stats : Nom, Sexe, Taille, Age, Force, Endurance, Santé

        // --- Les Gaulois ---
        // Astérix : Petit mais malin (Force basse sans potion, haute endurance)
        Gallic asterix = new Gallic("Astérix", "M", 120, 35, 10);
        // Panoramix : Le Druide (Indispensable pour la potion)
        Druid panoramix = new Druid("Panoramix", "M", 175, 80, 5);
        // Un forgeron (Cétautomatix)
        Smith cetautomatix = new Smith("Cétautomatix", "M", 180, 40, 60);

        village.addCharacter(asterix);
        village.addCharacter(panoramix);
        village.addCharacter(cetautomatix);

        // --- Les Romains ---
        // Quelques légionnaires génériques
        for (int i = 1; i <= 5; i++) {
            // Force moyenne, endurance moyenne
            Legionnaire legio = new Legionnaire("Légionnaire " + i, "M", 170, 25, 30);
            camp.addCharacter(legio);
        }
        // Un Préfet
        Prefect prefet = new Prefect("Olibrius", "M", 165, 50, 20);
        camp.addCharacter(prefet);

        // --- Les Créatures Fantastiques ---
        // Un Loup-Garou dans la forêt
        Pack pack = new Pack();
        Werewolf loup = new Werewolf("Loup-Garou Alpha", "M", 200, 100, 80, pack);
        // Attributs spécifiques si tu as une sous-classe Lycanthrope
        foret.addCharacter(loup);

        // ==========================================
        // 4. DISTRIBUTION DES ALIMENTS [cite: 42-43]
        // ==========================================
        // Utilisation de la FoodFactory créée précédemment

        // --- Garde-manger du Village (Sangliers + Ingrédients potion) ---
        village.addFood(FoodFactory.createFood("Sanglier"));
        village.addFood(FoodFactory.createFood("Sanglier"));
        village.addFood(FoodFactory.createFood("Vin"));
        // Ingrédients pour Panoramix (Recette potion [cite: 48])
        village.addFood(FoodFactory.createFood("Gui"));
        village.addFood(FoodFactory.createFood("Carottes"));
        village.addFood(FoodFactory.createFood("Miel"));
        village.addFood(FoodFactory.createFood("Huile de roche"));
        // Un peu de poisson (frais ou pas...)
        village.addFood(FoodFactory.createFood("Poisson"));

        // --- Garde-manger du Camp Romain ---
        camp.addFood(FoodFactory.createFood("Vin"));
        camp.addFood(FoodFactory.createFood("Vin"));
        camp.addFood(FoodFactory.createFood("Sanglier")); // Les romains en mangent aussi [cite: 45]
        camp.addFood(FoodFactory.createFood("Hydromel"));

        // --- Aliments sauvages dans la forêt ---
        foret.addFood(FoodFactory.createFood("Fraises"));
        foret.addFood(FoodFactory.createFood("Lait de licorne à deux têtes")); // Rare !

        System.out.println("Initialisation terminée ! La Gaule est prête à s'animer.");
    }

    // Point d'entrée
    public static void main(String[] args) {
        InvasionTheater theater = new InvasionTheater("Armorique", 10);
        theater.initializeSimulation();
        theater.runSimulation();
    }
}
