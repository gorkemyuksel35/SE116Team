package cells;

public abstract class Zone extends Cell{                        // This class helps us for storing all ingredients about economy
    protected int level;
    protected int electricity;
    protected int water;
    protected int internet;
    protected int population;
    protected int goods;
    protected int lifestyle;
    protected boolean security;
    protected boolean health;
    protected boolean education;
    protected int lastProduction;

    public Zone(int x, int y, char symbol) {
        super(x, y, symbol);
        this.level = 0;
        this.lastProduction = 0;
    }

    @Override
    public boolean isConnectable() {            // This must be return true because roads must be connectable
        return true;
    }

    public void resetInputs() {
        electricity = 0;
        water = 0;
        internet = 0;
        population = 0;
        goods = 0;
        lifestyle = 0;
        security = false;
        health = false;
        education = false;
    }

    public void receiveElectricity(int amount) {
        electricity += amount;
    }

    public void receiveWater(int amount) {
        water += amount;
    }

    public void receiveInternet(int amount) {
        internet += amount;
    }

    public int getDemand() {
        return Math.max(1, lastProduction);
    }

    public int getLevel() {
        return level;
    }

    public abstract void updateZone();
    public abstract int calculateProduction();
}
