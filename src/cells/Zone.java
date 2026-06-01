package cells;

public abstract class Zone extends Cell {
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

    public void receiveElectricity(int amount) {
        this.electricity = amount;
    }

    public void receiveWater(int amount) {
        this.water = amount;
    }

    public void receiveInternet(int amount) {
        this.internet = amount;
    }

    public int getDemand() {
        return Math.max(1, lastProduction);
    }

    public void resetInputs() {
        this.electricity = 0;
        this.water = 0;
        this.internet = 0;
        this.population = 0;
        this.goods = 0;
        this.lifestyle = 0;
        this.security = false;
        this.health = false;
        this.education = false;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getElectricity() {
        return electricity;
    }

    public int getWater() {
        return water;
    }

    public int getInternet() {
        return internet;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(int lifestyle) {
        this.lifestyle = lifestyle;
    }

    public boolean isSecurity() {
        return security;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public boolean isHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public boolean isEducation() {
        return education;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }

    public int getLastProduction() {
        return lastProduction;
    }

    public void setLastProduction(int lastProduction) {
        this.lastProduction = lastProduction;
    }

    public abstract void updateZone();
    public abstract int calculateProduction();

    @Override
    public String toString() {
        return symbol + "(L" + level + ")[E:" + electricity + " W:" + water + " I:" + internet + " P:" + population + " G:" + goods + " LS:" + lifestyle + "]";
    }
}