public class Robot {
    String nom;
    private Point position;

    public Robot(String nom, Point position) {
        this.nom = nom;
        this.position = position;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }


}
