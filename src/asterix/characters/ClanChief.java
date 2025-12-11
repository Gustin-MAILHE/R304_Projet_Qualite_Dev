package asterix.characters;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import asterix.food.MagicPotion;

public class ClanChief {
	private String name;
    private String sexe;
    private int age;
    private Place placeOrigin;
    
    public ClanChief(String name, String sexe, int age, Place placeOrigin) {
        this.name = name;
        this.sexe = sexe;
        this.age = age;
        this.placeOrigin = placeOrigin;
    }
    
    // Getters
    public String getname() { return name; }
    public String getSexe() { return sexe; }
    public int getAge() { return age; }
    public Place getplaceOrigin() { return placeOrigin; }
    
    // 1. Examiner son lieu
    public void checkPlace() {
        System.out.println("\n" + name + " examine son territoire...\n");
        placeOrigin.DisplayInfos();
    }
    
    // 2. Créer un nouveau personnage dans son lieu
    public void createCharacter(Character p) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(name + " accueille un nouveau membre !");
        System.out.println("══════════════════════════════════════════");
        
        if (placeOrigin.AddCharacter(p)) {
            System.out.println(p.getName() + " a rejoint " + placeOrigin.getName());
        } else {
            System.out.println("✗ Impossible d'ajouter " + p.getName());
        }
    }
    
    // 3. Soigner les personnages de son lieu
    public void healCharacters() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(" ordonne les soins !");
        System.out.println("══════════════════════════════════════════");
        placeOrigin.HealCharacters();
    }
    
    // 4. Nourrir les personnages de son lieu
    public void feedCharacters() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(name + " organise un festin !");
        System.out.println("══════════════════════════════════════════");
        placeOrigin.FeedCharacters();
    }
    
    // 5. Demander à un druide de faire de la potion magique
    public void requestMagicPotion(int type) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(name + " demande la préparation de potion magique...");
        System.out.println("══════════════════════════════════════════");
        
        // Recherche d'un druide dans le lieu
        Druid druid = null;
        for (Character p : placeOrigin.getCharacters()) {
            if (p instanceof Druid) {
            	druid = (Druid) p;
                break;
            }
        }
        
        //check si les ingrédients sont 
        
        if (druid != null) {
        	if (placeOrigin.canCookPotion()) {
        		druid.cookMagicPotion(type);
        		System.out.println("✓ " + druid.getName() + " a préparé une marmite de potion magique !");
        	} else {
        		System.out.println("✗ Des aliments nécéssaire ne sont pas disponible !");
        	}
        } else {
            System.out.println("✗ Aucun druide présent pour préparer la potion !");
        }
    }
    
    // 6. Faire boire de la potion magique à des personnages
    public void faireBoirePotion(Personnage p) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(name + " donne de la potion magique à " + p.getname());
        System.out.println("══════════════════════════════════════════");
        
        if (placeOrigin.getPersonnagesPresents().contains(p)) {
            p.boirePotionMagique();
            System.out.println("✓ " + p.getname() + " a bu la potion magique ! 🧪");
        } else {
            System.out.println("✗ " + p.getname() + " n'est pas dans " + placeOrigin.getname());
        }
    }
    
    // 7. Transférer un personnage vers un champ de bataille ou un enclos
    public void transfererPersonnage(Personnage p, Lieu destination) {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println(name + " transfère " + p.getname() + "...");
        System.out.println("══════════════════════════════════════════");
        
        // Vérification que la destination est un champ de bataille ou enclos
        if (!(destination instanceof ChampDeBataille) && !(destination instanceof Enclos)) {
            System.out.println("✗ Transfert impossible ! Seuls les champs de bataille et enclos sont autorisés.");
            return;
        }
        
        // Vérification que le personnage est dans le lieu du chef
        if (!placeOrigin.getPersonnagesPresents().contains(p)) {
            System.out.println("✗ " + p.getname() + " n'est pas dans votre lieu !");
            return;
        }
        
        // Retrait du personnage du lieu actuel
        if (placeOrigin.enleverPersonnage(p)) {
            // Ajout dans la destination
            if (destination.ajouterPersonnage(p)) {
                System.out.println("✓ " + p.getname() + " a été transféré vers " + destination.getname());
            } else {
                // Si l'ajout échoue, on remet le personnage dans le lieu d'origine
                placeOrigin.ajouterPersonnage(p);
                System.out.println("✗ Transfert échoué. " + p.getname() + " reste dans " + placeOrigin.getname());
            }
        }
    }
    
    // Menu interactif pour diriger le chef de clan
    public void afficherMenu(Scanner scanner) {
        boolean continuer = true;
        
        while (continuer) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║        CHEF DE CLAN : " + name + "                    ");
            System.out.println("║        Lieu : " + placeOrigin.getName() + "                    ");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Examiner mon lieu                                      ║");
            System.out.println("║  2. Créer un nouveau personnage                            ║");
            System.out.println("║  3. Soigner les personnages                                ║");
            System.out.println("║  4. Nourrir les personnages                                ║");
            System.out.println("║  5. Demander de la potion magique                          ║");
            System.out.println("║  6. Faire boire de la potion à un personnage               ║");
            System.out.println("║  7. Transférer un personnage (champ de bataille/enclos)    ║");
            System.out.println("║  0. Quitter                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════╝");
            System.out.print("Votre choix : ");
            
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne
            
            switch (choix) {
                case 1:
                    checkPlace();
                    break;
                    
                case 2:
                    // Menu pour créer un personnage
                    menuCreateCharacter(scanner);
                    break;
                    
                case 3:
                    healCharacters();
                    break;
                    
                case 4:
                    feedCharacters();
                    break;
                    
                case 5:
                    requestMagicPotion();
                    break;
                    
                case 6:
                    menuFaireBoirePotion(scanner);
                    break;
                    
                case 7:
                    menuTransfererPersonnage(scanner);
                    break;
                    
                case 0:
                    System.out.println("\n" + name + " se retire...");
                    continuer = false;
                    break;
                    
                default:
                    System.out.println("✗ Choix invalide !");
            }
        }
    }
    
    // Menu auxiliaire pour créer un personnage
    private void menuMagicPotion(Scanner scanner) {
        System.out.println("\n╔═══════════════════════════ CRÉATION D'UN NOUVEAU PERSONNAGE ════════════════════════════════");
        System.out.println("╚═══ la demande sera complétée si les aliments sont disponible et qu'un druide est présent ═══");
        
        System.out.println("\nType de potion (le type est un effet bonus à l'effet de base) :");
        System.out.println("1. Potion nutritive (fraise, homard ou jus de betterave)");
        System.out.println("2. Potion métamorphe (poils d'Idéfix)");
        System.out.println("3. Potion de duplication (lait de licorne à deux têtes");
        System.out.print("Choix : ");
        int type = scanner.nextInt();
        scanner.nextLine();
        
        switch (type) {
            case 1: requestMagicPotion(type); break;
            case 2: requestMagicPotion(type); break;
            case 3: requestMagicPotion(type); break;

            default:
                System.out.println("✗ Type invalide !");
                return;
        }
        
        return;
    }
    
 // Menu auxiliaire pour créer un personnage
    private void menuCreateCharacter(Scanner scanner) {
        System.out.println("\n═══ DEMANDE DE POTION MAGIQUE ═══");
        System.out.print("name : ");
        String name = scanner.nextLine();
        System.out.print("Sexe (M/F) : ");
        String sexe = scanner.nextLine();
        System.out.print("Taille : ");
        long taille = scanner.nextLong();
        System.out.print("Âge : ");
        long age = scanner.nextInt();
        System.out.print("Force : ");
        long strength = scanner.nextInt();
        scanner.nextLine();
        
        
        System.out.println("\nType de personnage :");
        System.out.println("1. Marchand");
        System.out.println("2. Aubergiste");
        System.out.println("3. Forgeron");
        System.out.println("4. Druide");
        System.out.println("5. Légionnaire");
        System.out.println("6. Préfet");
        System.out.println("7. Général");
        System.out.println("8. Lycanthrope");
        System.out.print("Choix : ");
        int type = scanner.nextInt();
        scanner.nextLine();
        
        Character nouveau = null;
        switch (type) {
            case 1: nouveau = new Merchant(name, sexe, taille, age, strength); break;
            case 2: nouveau = new Innkeeper(name, sexe, taille, age, strength); break;
            case 3: nouveau = new Smith(name, sexe, taille, age, strength); break;
            case 4: nouveau = new Druid(name, sexe, taille, age, strength); break;
            case 5: nouveau = new Legionnaire(name, sexe, taille, age, strength); break;
            case 6: nouveau = new Prefect(name, sexe, taille, age, strength); break;
            case 7: nouveau = new General(name, sexe, taille, age, strength); break;
            default:
                System.out.println("✗ Type invalide !");
                return;
        }
        
        createCharacter(nouveau);
    }
    
    // Menu auxiliaire pour faire boire la potion
    private void menuFaireBoirePotion(Scanner scanner) {
        List<Personnage> personnages = placeOrigin.getPersonnagesPresents();
        
        if (personnages.isEmpty()) {
            System.out.println("✗ Aucun personnage présent !");
            return;
        }
        
        System.out.println("\n═══ PERSONNAGES PRÉSENTS ═══");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println((i + 1) + ". " + personnages.get(i).getname() + 
                             " (" + personnages.get(i).getClass().getSimpleName() + ")");
        }
        
        System.out.print("Choisir un personnage : ");
        int choix = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (choix >= 0 && choix < personnages.size()) {
            faireBoirePotion(personnages.get(choix));
        } else {
            System.out.println("✗ Choix invalide !");
        }
    }
    
    // Menu auxiliaire pour transférer un personnage
    private void menuTransfererPersonnage(Scanner scanner) {
        List<Personnage> personnages = placeOrigin.getPersonnagesPresents();
        
        if (personnages.isEmpty()) {
            System.out.println("✗ Aucun personnage à transférer !");
            return;
        }
        
        System.out.println("\n═══ PERSONNAGES PRÉSENTS ═══");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println((i + 1) + ". " + personnages.get(i).getname() + 
                             " (" + personnages.get(i).getClass().getSimpleName() + ")");
        }
        
        System.out.print("Choisir un personnage : ");
        int choixPerso = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (choixPerso < 0 || choixPerso >= personnages.size()) {
            System.out.println("✗ Choix invalide !");
            return;
        }
        
        // Ici, vous devriez avoir une liste de champs de bataille et d'enclos disponibles
        // Pour l'exemple, on suppose qu'ils existent déjà
        System.out.println("\n═══ DESTINATIONS POSSIBLES ═══");
        System.out.println("1. Champ de bataille");
        System.out.println("2. Enclos");
        System.out.print("Choisir une destination : ");
        int choixDest = scanner.nextInt();
        scanner.nextLine();
        
        // Vous devriez avoir une référence aux lieux disponibles
        // Pour l'exemple simplifié :
        System.out.println("⚠ Fonctionnalité à compléter avec les lieux existants");
    }
}
