package senin.dto;

public class Route {
    private final int idA;
    private final int idB;

    public Route(int idA, int idB) {
        this.idA = idA;
        this.idB = idB;
    }

    public int getIdA() {
        return idA;
    }

    public int getIdB() {
        return idB;
    }
}
