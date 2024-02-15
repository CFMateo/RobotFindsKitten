public class Grille {
	 private char[][] grille;
	  
	 public Grille(int hauteur, int largeur, int repetitions) {
	        grille = new char[13][65];
	        
	        for (int i = 0; i < 13; i++) {
	            for (int j = 0; j < 65; j++) {
	            	 if (i == 0 || j ==0 || i%6 == 0 || (j+1)%13 == 0) {
		        		  grille[i][j] = '%';
		        		  }else {
		                        grille[i][j] = ' ';
		        		  }
	           
		        	  }			
	            }
	        // Remplacement des caractères spéciaux
	        for (int i = 0; i < 13; i++) {
	            for (int j = 0; j < 65; j++) {
	                if (((i==3 || i==9)&& j>0  && j % 13 == 0)|| i==6 && (j-6)%13==0) {
	                    grille[i][j-1] = '!';
	                }
	            }
	        }
	    }
	
	
	    

	    // Méthode pour afficher la grille
	    public void afficherGrille() {
	        for (int i = 0; i < 13; i++) {
	            for (int j = 0; j < 65; j++) {
	                System.out.print(grille[i][j]);
	            }
	            System.out.println(); // Pour passer à la ligne suivante après avoir affiché chaque ligne
	        }
	    }


    public boolean deplacementPossible(Robot robot, int x, int y) {
        // Implémentez la méthode pour vérifier si le déplacement du robot à la position (x, y) est possible
        return false;
    }

    public void interagir(Robot robot) {
        // Implémentez la méthode pour interagir entre le robot et la case sur laquelle il se trouve
    }

    public Point randomEmptyCell() {
        // Implémentez la méthode pour renvoyer une cellule vide aléatoire sur la grille
        return null;
    }
}
