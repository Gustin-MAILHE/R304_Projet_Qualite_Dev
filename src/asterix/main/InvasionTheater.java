package asterix.main;

import asterix.characters.*;
import asterix.characters.Character;
import asterix.food.*;
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

    // --- AFFICHAGE [cite: 106, 107, 108] ---

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

    // --- BOUCLE PRINCIPALE DE SIMULATION [cite: 109, 110] ---

    public void runSimulation() {
        boolean running = true;
        int turn = 1;

        while (running) {
            System.out.println("\n--- TOUR " + turn + " ---");

            // 1. Faire combattre les belligérants (Champs de bataille) [cite: 111]
            handleCombats();

            // 2. Modifier l'état des personnages (Faim, Potion, etc.) [cite: 112]
            updateCharactersState();

            // 3. Faire apparaître des aliments (Hors champs de bataille) [cite: 113]
            spawnFood();

            // 4. Pourrissement des aliments (Frais -> Pas frais) [cite: 114]
            decayFood();

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
        // [cite: 111] Les combats ont lieu principalement sur les champs de bataille
        for (Place place : places) {
            // Suppose que Place possède une méthode ou un type pour savoir si c'est un champ de bataille
            if (place.getName().toLowerCase().contains("bataille") || place instanceof Battlefield) {
                System.out.println("Combats en cours sur " + place.getName() + "...");
                // Logique de combat à implémenter dans Place ou ici :
                // place.startBattle();
            }
        }
    }

    private void updateCharactersState() {
        // [cite: 112] Modifier aléatoirement l'état, baisser niveau potion
        for (Place place : places) {
            List<Character> chars = place.getCharacters();
            for (Character c : chars) {
                // Diminue la potion, augmente la faim
                c.timePasses();
            }
        }
    }

    private void spawnFood() {
        // [cite: 113] Apparition aliments sauf champs de bataille
        for (Place place : places) {
            if (!(place instanceof Battlefield)) { // Nécessite la classe Battlefield ou un check
                if (random.nextInt(10) > 7) { // 30% de chance d'apparition
                    place.addFood(new Food("Sanglier", FoodType.MEAT, FreshnessLevel.FRESH));
                    System.out.println("Un sanglier apparait à " + place.getName());
                }
            }
        }
    }

    private void decayFood() {
        for (Place place : places) {
            for (Food food : place.getFoods()) {
                food.decay();
            }
        }
    }

    private void handleChiefsTurn() {
        //  Passer la main à un chef de clan
        for (ClanChief chief : chiefs) {
            System.out.println("C'est au tour du chef " + chief.getName() + " (" + chief.getPlaceOrigin().getName() + ")");
            // Appeler le menu du chef
            // chief.takeTurn();
        }
    }

    // Point d'entrée
    public static void main(String[] args) {
        InvasionTheater theater = new InvasionTheater("Armorique", 10);
        // Initialisation des lieux et persos ici...
        theater.runSimulation();
    }
}
