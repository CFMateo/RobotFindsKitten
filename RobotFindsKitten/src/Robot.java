public class Robot {
    private String nom;
    private Point position;

    public Robot(String nom, Point position) {
        this.nom = nom;
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void deplacer(String direction) {
        // Implémentez la logique pour déplacer le robot dans une direction donnée
    }
}
