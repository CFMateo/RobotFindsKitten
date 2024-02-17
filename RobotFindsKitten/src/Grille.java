import java.util.Random;

public class Grille {
	 private char[][] grille;
	 boolean boolAncienKitten = false;
	 // Stocker la valeur de la case précédente
	 char ancienneValeur = ' ';
	 
	 public Grille(int hauteur, int largeur) {
	        grille = new char[hauteur][largeur];
	      
	        // Structure basique de la grille:
	        for (int i = 0; i < hauteur; i++) {
	            for (int j = 0; j < largeur; j++) {
	            	 if (i == 0 || j ==0 || i%6 == 0 || j%(hauteur-1) == 0) {
		        		  grille[i][j] = '%';
		        		  }else {
		                        grille[i][j] = ' ';
		        		  }
	           
		        	  }			
	            }
	        // Créaton des murs
	        for (int i = 0; i < hauteur; i++) {
	            for (int j = 0; j < largeur; j++) {
	            	 // Condition pour les lignes 3 et 9
	                if ((i == 3 || i == 9) && (j > 0 && j < 60) && j % (hauteur - 1) == 0) {
	                    grille[i][j] = '!';
	                }
	                // Condition pour la ligne 6
	                else if (i == 6 && (j - 6) % (hauteur - 1) == 0) {
	                    grille[i][j] = '!';
	                }
	            }
	        }
	        
	    }
	 
	 public Point randomEmptyCell() {
		    Random random = new Random();
		    int hauteur = grille.length;
		    int largeur = grille[0].length;

		    int x, y;
		    do {
		        x = random.nextInt(hauteur);
		        y = random.nextInt(largeur);
		    } while (!deplacementPossible(x, y));;

		    return new Point(x, y);
		}
	 public boolean deplacementPossible(int x, int y) {
		    // Vérifier si les coordonnées sont valides
		    if (x < 0 || y < 0 || x >= grille.length || y >= grille[0].length) {
		        return false; // Les coordonnées sont en dehors des limites de la grille
		    }
		    
		    // Vérifier si la cellule est vide ou non
		    if (grille[x][y] == '%' || grille[x][y] == '!') {
		        return false ; // La cellule n'est pas vide, le déplacement n'est pas possible
		    }
		    
		    // Vérifier si le déplacement est possible par rapport aux règles du jeu (ex: murs, objets)
		    // Ajoutez vos conditions spécifiques ici
		    
		    // Le déplacement est possible si toutes les conditions sont satisfaites
		    return true;
		}

	 
	 public int[] placerRobot(int x, int y) {
		    if (x != -1 && y != -1) {
		        // Si les coordonnées x et y sont fournies, placer le robot à ces coordonnées
		        if (grille[x][y] == ' ') {
		            grille[x][y] = '#';
		            return new int[] {x, y};
		        } else {
		            System.out.println("Impossible de placer le robot à la position spécifiée.");
		            return new int[] {-1, -1}; // Retourner un tableau d'entiers invalides
		        }
		    } else {
		        // Si les coordonnées ne sont pas fournies, placer le robot aléatoirement
		    	 Point positionAleatoire = randomEmptyCell(); 
		         int xAleatoire = positionAleatoire.getX();
		         int yAleatoire = positionAleatoire.getY();
		         
		        // Place le robot à la position aléatoire trouvée
		        grille[xAleatoire][yAleatoire] = '#';

		        // Retourne les coordonnées finales
		        return new int[] {xAleatoire, yAleatoire};
		    }
	 }
	
	 
	// On affiche la grille 2D:
	 public void afficherGrille() {
		    for (int i = 0; i < grille.length; i++) {
		        for (int j = 0; j < grille[i].length; j++) {
		            System.out.print(grille[i][j]);
		        }
		        System.out.println();
		    }
		}
	 public void deplacerRobot(String commande, Robot robot) {
		    int ancienX = robot.getPosition().getX();
		    int ancienY = robot.getPosition().getY();
		    
		    int newX = ancienX;
		    int newY = ancienY;
		   

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
		        default:
		            System.out.println("Commande invalide.");
		            return; // Sortir de la méthode si la commande est invalide
		    }

		    // Vérifier si le déplacement est possible
		    if (deplacementPossible(newX, newY)) {
		    	
		    	// Créer un nouveau point avec les nouvelles coordonnées
		        Point newPosition = new Point(newX, newY);
		        
		     // Effacer l'ancienne position du robot dans la grille
			    grille[ancienX][ancienY] = ' ';

		    	if (boolAncienKitten){
		    		grille[ancienX][ancienY] = ancienneValeur;
			        System.out.println(ancienneValeur+"hahahahahha");
		    	}
		        
		    	if (grille[newX][newY] != ' ' && grille[newX][newY] != '\''  && grille[newX][newY] != 'T'){
		    		System.out.println(grille[newX][newY]+"hahishishihsa");
	        		ancienneValeur =  grille[newX][newY]; 
	        		boolAncienKitten = true;		
		    	}else {
				    boolAncienKitten = false;	
				    
		    	}
		    	
			    
			    // Mettre à jour la position du robot avec le nouveau point
		        robot.setPosition(newPosition);
		        
		        // Placer le robot dans sa nouvelle position sur la grille
		        grille[newX][newY] = '#';
		    	
		    }
		    }
	 
		
	 public void ajouterTeleporteurs() {
		    Random random = new Random();
		    int hauteur = grille.length;
		    int largeur = grille[0].length;
		    int nombreTeleporteurs = 0;

		    // Tant que le nombre de téléporteurs ajoutés est inférieur à 2
		    while (nombreTeleporteurs < 2) {
		        // Trouver une cellule vide aléatoire
		        Point position = randomEmptyCell();
		        int x = position.getX();
		        int y = position.getY();

		        // Vérifier si la case est vide
		        if (grille[x][y] == ' ') {
		            // Placer un téléporteur à cet emplacement
		            grille[x][y] = 'T';
		            nombreTeleporteurs++;
		        }
		    }
		}
	 
	 public void insererCles() {
		 Random random = new Random();

		    // Coordonnées des coins supérieurs gauche des séquences de cases vides
		    int[][] sequences = {
		        {1, 1}, {1, 13}, {1, 25}, {1, 37}, {1, 49},
		        {7, 1}, {7, 13}, {7, 25}, {7, 37}, {7, 49}
		    };

		    // Parcourir chaque séquence de cases vides
		    for (int[] sequence : sequences) {
		        int startX = sequence[0];
		        int startY = sequence[1];

		        // Trouver une case vide aléatoire dans la séquence
		        int randomX, randomY;
		        do {
		            randomX = startX + random.nextInt(5);
		            randomY = startY + random.nextInt(11);
		        } while (grille[randomX][randomY] != ' ');

		        // Insérer une clé dans la case vide sélectionnée aléatoirement
		        grille[randomX][randomY] = '\'';
		    }
		}
	 
	 public void genererNonKittens() {
		    Random random = new Random();
		    int hauteur = grille.length;
		    int largeur = grille[0].length;
		    
		    insererCles();
		    ajouterTeleporteurs();
		  
		    

		    // Nombre total de cases dans la grille
		    int nombreTotalCases = hauteur * largeur;

		    // Calculer le nombre de NonKittens à générer (par exemple, 10% du nombre total de cases)
		    int nombreNonKittens = (int) (nombreTotalCases * 0.06	);

		    for (int i = 0; i < nombreNonKittens; i++) {
		        // Trouver une cellule vide aléatoire pour placer un NonKitten
		        Point position = randomEmptyCell();

		        // Récupérer les coordonnées de la cellule vide
		        int x = position.getX();
		        int y = position.getY();
		        if (grille[x][y] == ' ') {
		            // Générer un symbole aléatoire pour le NonKitten
		            char symbole = Case.getRandomSymbole();

		            // Placer le NonKitten sur la grille
		            grille[x][y] = symbole;
		    }
		  }
		}
	 

	 
	 

	 
}
     
