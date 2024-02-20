
import java.util.Scanner;


public class RobotFindsKitten {
    
    // Méthode principale
    public static void main(String[] args) {
        // Définition des constantes pour la taille de la grille
        final int HAUTEUR_GRILLE = 13;
        final int LARGEUR_GRILLE = 61;

        // Création d'une instance de Grille avec la taille spécifiée
        Grille grille = new Grille(HAUTEUR_GRILLE, LARGEUR_GRILLE);

        // Placement du robot à une position aléatoire sur la grille
        int[] coordonneesRobot = grille.placerRobot(-1, -1);
        int positionX = coordonneesRobot[0];
        int positionY = coordonneesRobot[1];
        Point positionInitiale = new Point(positionX, positionY);
        Robot robot = new Robot("R.O.B.", positionInitiale);

        // Génération des objets (clés, téléporteurs, et le kitten)
        grille.genererObjets();

 
        System.out.println("           Bienvenue dans RobotFindsKitten\nSuper Dungeon Master 3000 Ultra Turbo Edition !");

        // Création d'un scanner pour lire l'entrée de l'utilisateur depuis la console
        Scanner scanner = new Scanner(System.in);

        // Boucle principale du jeu
        while (!grille.isKittenTrouve()) {
        	grille.afficherGrille();
      
            grille.afficherPrompt(robot);
            
            // Lecture de la commande de déplacement entrée par l'utilisateur
            String commande = scanner.nextLine();

            // Vérification si l'utilisateur souhaite quitter le jeu
            if (commande.equals("q")) {
                System.out.println(" Au revoir !");
                break; // Sortie de la boucle principale si l'utilisateur quitte le jeu
            }

            // Déplacement du robot selon la commande entrée par l'utilisateur
            grille.deplacerRobot(commande, robot);

            
        }
        scanner.close();
    }
}
