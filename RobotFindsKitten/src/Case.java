public abstract class Case {
    protected char representation;

    /**
     * Retourne la représentation de la case (un seul caractère)
     *
     * @return la représentation de la case
     */
    public char getRepresentation() {
        return representation;
    }

    public static char getRandomSymbole() {
        return (char) (Math.random() * (126 - 58) + 58);
    }
}
