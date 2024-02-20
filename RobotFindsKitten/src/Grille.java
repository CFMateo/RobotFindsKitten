import java.util.Random;


public class Grille {
 private char[][] grille; // Matrice représentant la grille de jeu
 private boolean kittenTrouve = false; // Indique si le robot a trouvé le "kitten"
 private char kittenSymbol = Case.getRandomSymbole(); // Symbole représentant le "kitten"
 private boolean boolAncienKitten = false; // Servira a indique si la case précédente contenait un "kitten"
 private String nomRobot = "nom du robot";
 private char ancienneValeur = ' '; // Initialisation de la valeur précédente de la case où se trouvait le robot
 private int nombreCles = 0; 
 private int nombreTeleporteur = 0;
 private boolean possedeTeleporteur = false;


 public Grille(int hauteur, int largeur) {
     grille = new char[hauteur][largeur]; 
     initialiserGrille(hauteur, largeur);
 }

 // On initialise la grille avec des murs et des espaces vides
 private void initialiserGrille(int hauteur, int largeur) {
     // Boucle pour parcourir chaque case de la grille
     for (int i = 0; i < hauteur; i++) {
         for (int j = 0; j < largeur; j++) {
             // Si la case est sur le bord de la grille ou à une certaine position, elle devient un mur
             if (i == 0 || j == 0 || i % 6 == 0 || j % (hauteur - 1) == 0) {
                 grille[i][j] = '%';
             } else {
                 grille[i][j] = ' '; // Sinon, la case est vide
             }
         }
     }

     // Création des portes '!'
     for (int i = 0; i < hauteur; i++) {
         for (int j = 0; j < largeur; j++) {
             if ((i == 3 || i == 9) && (j > 0 && j < 60) && j % (hauteur - 1) == 0) {
                 grille[i][j] = '!';
             } else if (i == 6 && (j - 6) % (hauteur - 1) == 0) {
                 grille[i][j] = '!';
             }
         }
     }
 }


 public void afficherGrille() {
     for (int i = 0; i < grille.length; i++) {
         for (int j = 0; j < grille[i].length; j++) {
             System.out.print(grille[i][j]);
         }
         System.out.println();
     }
 }


 /**
  * Sélectionne aléatoirement une cellule vide dans la grille.
  * 
  * @return Un objet Point représentant les coordonnées de la cellule vide sélectionnée.
  */
 public Point randomEmptyCell() {
     Random random = new Random();
     int hauteur = grille.length;
     int largeur = grille[0].length;
     int x, y;
     // Sélectionne aléatoirement des coordonnées jusqu'à ce qu'une cellule vide soit trouvée
     do {
         x = random.nextInt(hauteur);
         y = random.nextInt(largeur);
     } while (!deplacementPossible(x, y));

     return new Point(x, y);
 }

 /**
  * Déplace le robot en fonction de la commande spécifiée par l'utilisateur.
  * 
  * @param commande La commande de déplacement spécifiée par l'utilisateur.
  * @param robot    L'objet Robot à déplacer.
  */
 public void deplacerRobot(String commande, Robot robot) {
     int ancienX = robot.getPosition().getX();
     int ancienY = robot.getPosition().getY();
     int newX = ancienX;
     int newY = ancienY;

     // Déplacement du robot en fonction de la commande spécifiée
     switch (commande) {
         case "w": // Haut
             newX -= 1;
             break;
         case "a": // Gauche
             newY -= 1;
             break;
         case "s": // Bas
             newX += 1;
             break;
         case "d": // Droite
             newY += 1;
             break;
         case "t": // Téléportation
             if (possedeTeleporteur) {
                 teleporter(ancienX, ancienY, robot); // Téléporte le robot
                 return; // Sort de la méthode après la téléportation
             }
             break;
         default:
             System.out.println("Commande invalide.");
             return; // Sort de la méthode si la commande est invalide
     }

     // Vérifie si le déplacement vers le nouveau point est possible
     if (deplacementPossible(newX, newY)) {
         Point newPosition = new Point(newX, newY);
         grille[ancienX][ancienY] = ' '; // Efface l'ancienne position du robot dans la grille

         // Restaure la valeur précédente si c'était un ancien symbole Kitten
         if (boolAncienKitten) {
             grille[ancienX][ancienY] = ancienneValeur;
             boolAncienKitten = false;
         }

         // Effectue différentes actions selon le contenu de la nouvelle position
         if (grille[newX][newY] == kittenSymbol) { // Si le robot trouve le Kitten
             System.out.println("You found kitten! Way to go, robot.\nKitten <3 " + nomRobot);
             kittenTrouve = true;
             
         // Test si l'element est un non kitten + autre qu'un T, un ' et qu'un !
         } else if (grille[newX][newY] != ' ' && grille[newX][newY] != '\'' && grille[newX][newY] != 'T' && grille[newX][newY] != '!') { // Si la nouvelle position contient un objet
             String description = NonKitten.getDescriptionFromSymbol(grille[newX][newY]); // Obtient la description de l'objet
             System.out.println(description); // Affiche la description
             ancienneValeur = grille[newX][newY]; // Stocke la valeur précédente
             boolAncienKitten = true; // Indique qu'il s'agit d'un ancien symbole Kitten
             
         } else if (grille[newX][newY] == '\'') { // Si la nouvelle position contient une clé
             nombreCles += 1; // Incrémente le nombre de clés
         } else if (grille[newX][newY] == '!') { // Si la nouvelle position contient une porte
             nombreCles -= 1;
         } else if (grille[newX][newY] == 'T' && nombreTeleporteur == 1) {
             possedeTeleporteur = true; // Indique mainetnant que le joueur possède un téléporteur
         } else {
             boolAncienKitten = false; // Réinitialise la valeur de boolAncienKitten
         }

         robot.setPosition(newPosition); // Met à jour la position du robot
         grille[newX][newY] = '#'; // Place le robot dans sa nouvelle position sur la grille
     }
 }

 /**
  * Vérifie si un déplacement vers la cellule spécifiée est possible.
  * 
  * @param x La coordonnée x de la cellule.
  * @param y La coordonnée y de la cellule.
  * @return true si le déplacement est possible, sinon false.
  */
 public boolean deplacementPossible(int x, int y) {
     // Vérifie si les coordonnées sont valides
     if (x < 0 || y < 0 || x >= grille.length || y >= grille[0].length) {
         return false; // Les coordonnées sont en dehors des limites de la grille
     }
     
     // Vérifie si la cellule est une porte ou un mur
     if (grille[x][y] == '%') {
         return false;
     } else if (grille[x][y] == '!') {
         return nombreCles > 0; //  Déplacement possible seulement si le joueur a au moins 1 clé
     }
     
     return true; // Le déplacement est possible
 }

 /**
  * Place le robot aux coordonnées spécifiées ou à une position aléatoire si aucune coordonnée n'est spécifiée.
  * 
  * @param x La coordonnée x pour placer le robot.
  * @param y La coordonnée y pour placer le robot.
  * @return Un tableau d'entiers contenant les coordonnées finales du robot.
  */
 public int[] placerRobot(int x, int y) {
     if (x != -1 && y != -1) { // Si les coordonnées sont fournies
         if (grille[x][y] == ' ') { 
             grille[x][y] = '#'; 
             return new int[]{x, y}; // Retourne les coordonnées finales du robot
         } else {
             System.out.println("Impossible de placer le robot à la position spécifiée.");
             return new int[]{-1, -1}; // Retourne un tableau d'entiers invalides si la cellule n'est pas vide
         }
     } else { // Si aucune coordonnée n'est spécifiée (on souhaite le placer aléatoirement)
         Point positionAleatoire = randomEmptyCell();
         int xAleatoire = positionAleatoire.getX();
         int yAleatoire = positionAleatoire.getY();
         grille[xAleatoire][yAleatoire] = '#';
         return new int[]{xAleatoire, yAleatoire}; // Retourne les coordonnées finales du robot
     }
 }

 /**
  * Ajoute un téléporteur à une position aléatoire vide.
  */
 public void ajouterTeleporteurs() {
     Point position = randomEmptyCell(); // Sélectionne une position aléatoire vide
     int x = position.getX();
     int y = position.getY();
     grille[x][y] = 'T'; // Place le téléporteur dans la cellule vide aléatoire
     nombreTeleporteur++;
 }

 /**
  * Téléporte le robot à une position aléatoire.
  * 
  * @param ancienX La coordonnée x de l'ancienne position du robot.
  * @param ancienY La coordonnée y de l'ancienne position du robot.
  * @param robot   L'objet Robot à téléporter.
  */
 public void teleporter(int ancienX, int ancienY, Robot robot) {
     int[] positionAleatoire = placerRobot(-1, -1); // Sélectionne une position aléatoire pour téléporter le robot
     int newX = positionAleatoire[0];
     int newY = positionAleatoire[1];

     // Restaure la valeur précédente si c'était un ancien symbole Kitten
     if (boolAncienKitten) {
         grille[ancienX][ancienY] = ancienneValeur;
         boolAncienKitten = false;
     } else {
         grille[ancienX][ancienY] = ' '; // Efface l'ancienne position du robot dans la grille
     }
     robot.setPosition(new Point(newX, newY)); // Met à jour la position du robot
 }
 /**
  * Insère des clés dans des positions aléatoires vides de la grille.
  * En s'assurant qu'il y ai bien une clé par salle
  */
 public void insererCles() {
     Random random = new Random();
     // Séquences prédéfinies de cases vides pour l'insertion des clés
     int[][] sequences = {
             {1, 1}, {1, 13}, {1, 25}, {1, 37}, {1, 49},
             {7, 1}, {7, 13}, {7, 25}, {7, 37}, {7, 49}
     };

     // Parcours de chaque séquence pour insérer une clé dans une case vide aléatoire
     for (int[] sequence : sequences) {
         int startX = sequence[0];
         int startY = sequence[1];

         int randomX, randomY;
         do {
             // Sélection aléatoire d'une case vide dans la séquence
             randomX = startX + random.nextInt(5);
             randomY = startY + random.nextInt(11);
         } while (grille[randomX][randomY] != ' '); // Vérifie si la case sélectionnée est vide

         grille[randomX][randomY] = '\''; // Insère une clé dans la case vide sélectionnée aléatoirement
     }
 }

 /**
  * Génère tous les objets de la grille, y compris les clés, les téléporteurs et Kitten.
  */
 public void genererObjets() {
     insererCles(); // Insère les clés
     ajouterTeleporteurs(); // Ajoute les téléporteurs
     genererKitten(); // Génère Kitten

     int nombreTotalCases = grille.length * grille[0].length;
     int nombreNonKittens = (int) (nombreTotalCases * 0.05); // Détermine le nombre de NonKittens

     for (int i = 0; i < nombreNonKittens; i++) {
         Point position = randomEmptyCell();
         int x = position.getX();
         int y = position.getY();
         if (grille[x][y] == ' ') {
             NonKitten nonKitten = new NonKitten(); // Crée un nouvel objet NonKitten
             grille[x][y] = nonKitten.getRepresentation(); // Place le symbole du NonKitten sur la grille
         }
     }
 }

 /**
  * Affiche le prompt de commande du robot pur l'utilisateur.
  * 
  * @param robot L'objet Robot.
  */
 public void afficherPrompt(Robot robot) {
     if (possedeTeleporteur) {
         System.out.println(robot.nom + " [" + nombreCles + "]T>"); // Affiche l'invite avec indication de possession de téléporteur
     } else {
         System.out.println(robot.nom + " [" + nombreCles + "]>"); // Affiche l'invite standard
     }
 }

 /**
  * Génère Kitten à une position aléatoire de la grille.
  */
 public void genererKitten() {
     Point positionKitten = randomEmptyCell(); // Sélectionne une position aléatoire vide
     int xKitten = positionKitten.getX();
     int yKitten = positionKitten.getY();
     grille[xKitten][yKitten] = kittenSymbol; // Place le symbole de Kitten sur la grille
 }

 /**
  * Vérifie si le Kitten a été trouvé.
  * 
  * @return true si Kitten a été trouvé, sinon false.
  */
 public boolean isKittenTrouve() {
     return kittenTrouve;
 }
}
