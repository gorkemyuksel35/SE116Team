package cells;

public abstract class UtilityProvider extends Cell {
    protected int cap;
    protected String utilType;

    public UtilityProvider(int x, int y, char symbol, String utilType) {
        super(x, y, symbol);
        this.cap = 100;
        this.utilType = utilType;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public int getCap() {
        return cap;
    }

    public String getUtilType() {
        return utilType;
    }
}

