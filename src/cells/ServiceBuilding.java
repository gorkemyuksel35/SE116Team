package cells;

public abstract class ServiceBuilding extends Cell {
    protected int radius;

    public ServiceBuilding(int x, int y, char symbol, int radius) {
        super(x, y, symbol);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
