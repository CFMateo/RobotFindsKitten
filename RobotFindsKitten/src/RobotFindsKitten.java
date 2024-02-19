import java.util.Scanner;

public class RobotFindsKitten {
    public static void main(String[] args) {
    	
    	// Création d'une instance de Grille avec une certaine hauteur et largeur
        Grille grille = new Grille(13, 61); // Création de la grille avec une hauteur de 7 et une largeur de 14 caractères
         
        int[] coordonneesRobot = grille.placerRobot(-1,-1);
        
        // Récupération des coordonnées i et j retournées par la méthode placerRobotAleatoirement()
        int x = coordonneesRobot[0];
        int y = coordonneesRobot[1];
        
        // Création d'un objet Point pour définir la position initiale du robot
        Point positionInitiale = new Point(x, y);

        // Instanciation d'un objet Robot en utilisant le constructeur avec la position initiale
        Robot robot = new Robot("Le bg", positionInitiale);

               
        grille.genererNonKittens();
        System.out.println("           Bienvenue dans RobotFindsKitten\nSuper Dungeon Master 3000 Ultra Turbo Edition !");
        // Affichage de la grille après avoir placé le robot
        grille.afficherGrille();

        
    
        // Création d'un scanner pour lire l'entrée de l'utilisateur depuis la console
        Scanner scanner = new Scanner(System.in);
        
      
        
        // Boucle principale
        while (true) {
        	Point positionRobot = robot.getPosition();
        	
            grille.afficherPrompt(robot);
            
            // Lire la commande de déplacement de l'utilisateur
            String commande = scanner.nextLine();

            // Vérifier si l'utilisateur souhaite quitter le jeu
            if (commande.equals("HAHA")) {
                System.out.println("Jeu terminé. Au revoir !");
                break; // Sortir de la boucle et terminer le programme
            }

            // Déplacer le robot en fonction de la commande entrée par l'utilisateur
            grille.deplacerRobot(commande, robot);

            // Afficher la grille mise à jour après le déplacement du robot
            grille.afficherGrille();
            
        }

        // Fermer le scanner une fois que l'utilisateur a terminé
        scanner.close();

    }   
}
    



