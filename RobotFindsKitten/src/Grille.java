public class Grille {
    private Case[][] grille;

    public Grille(int nbrPiecesX, int nbrPiecesY, int largeurPiece, int hauteurPiece, int nbrNonKitten) {
        // Initialisation de la grille avec les dimensions spécifiées
        grille = new Case[nbrPiecesY][nbrPiecesX];

        // Boucle pour remplir la grille avec des cases vides ou des éléments non-chatons
        for (int y = 0; y < nbrPiecesY; y++) {
            for (int x = 0; x < nbrPiecesX; x++) {
                // Vous pouvez mettre en place la logique pour décider quel type de case placer à chaque position
                if (Math.random() < (double) nbrNonKitten / (nbrPiecesX * nbrPiecesY)) {
                    // Place un élément non-chaton à cette position
                    grille[y][x] = new NonKitten();
                } else {
                    // Place une case vide à cette position
                    grille[y][x] = new CaseVide();
                }
            }
        }

        // Placez d'autres éléments comme les murs, la porte, etc., si nécessaire
    }

    // Autres méthodes de la classe Grille...

    // Exemple de méthode pour afficher la grille
    public void afficher(Robot robot) {
        for (int y = 0; y < grille.length; y++) {
            for (int x = 0; x < grille[y].length; x++) {
                if (robot.getPosition().egal(x, y)) {
                    System.out.print("R "); // Affiche le robot
                } else {
                    System.out.print(grille[y][x].getRepresentation() + " "); // Affiche la représentation de la case
                }
            }
            System.out.println(); // Saut de ligne pour afficher la prochaine ligne de la grille
        }
    }
}
