package core;

import cells.*;

public abstract class Zone extends Cell {
    protected int level;
    protected int population;
    protected int goods;
    protected int lifestyle;
    protected int electricity;
    protected int water;
    protected int internet;
    protected boolean hasUtilities;
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;
    protected int lastProduction;

    public Zone(int x, int y, char symbol) {
        super(x, y, symbol);
        this.level = 0;
        this.lastProduction = 0;
    }

    public void resetInputs() {
        this.electricity = 0;
        this.water = 0;
        this.internet = 0;
        this.population = 0;
        this.goods = 0;
        this.lifestyle = 0;
        this.hasUtilities = false;
        this.hasSecurity = false;
        this.hasHealth = false;
        this.hasEducation = false;
    }

    public abstract void updateZone();
    public abstract int calculateProduction();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getElectricity() {
        return electricity;
    }

    public void receiveElectricity(int given) {
        this.electricity = given;
    }

    public int getWater() {
        return water;
    }

    public void receiveWater(int given) {
        this.water = given;
    }

    public int getInternet() {
        return internet;
    }

    public void receiveInternet(int given) {
        this.internet = given;
    }

    public boolean isHasUtilities() {
        return hasUtilities;
    }

    public void setHasUtilities(boolean hasUtilities) {
        this.hasUtilities = hasUtilities;
    }

    public boolean isHasSecurity() {
        return hasSecurity;
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public boolean isHasHealth() {
        return hasHealth;
    }

    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public boolean isHasEducation() {
        return hasEducation;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public int getLastProduction() {
        return lastProduction;
    }

    public void setLastProduction(int lastProduction) {
        this.lastProduction = lastProduction;
    }

    public int getDemand() {
        if (level == 0) {
            return 1;
        }
        return Math.max(1, lastProduction);
    }

    @Override
    public boolean isConnectable() {
        return true; }
}

class Housing extends Zone {
    public Housing(int x, int y) {
        super(x, y, 'H');
    }

    @Override
    public void updateZone() {
        int oldLevel = level;
        if (electricity == 0 || water == 0 || internet == 0) {
            level = 0;
        } else {
            int targetLvl = 1;
            if (hasSecurity && hasHealth && hasEducation) {
                targetLvl = 2;
            }
            if (targetLvl == 2 && lifestyle > 0) {
                targetLvl = 3;
            }
            level = targetLvl;
        }
        if (level > oldLevel) {
            System.out.println("House at (" + x + "," + y + ") levels up from " + oldLevel + " to " + level);
        } else if (level < oldLevel) {
            System.out.println("House at (" + x + "," + y + ") levels down from " + oldLevel + " to " + level);
        }
    }

    @Override
    public int calculateProduction() {
        if (level == 0) {
            return 0;
        }
        int m = Math.min(electricity, Math.min(water, internet));
        if (m == 0) {
            m = 1;
        }
        switch (level) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3:
                return 2 * m + lifestyle;
            default:
                return 0;
        }
    }
}

class Industrial extends Zone {
    public Industrial(int x, int y) {
        super(x, y, 'I');
    }

    @Override
    public void updateZone() {
        int oldLevel = level;
        if (electricity == 0 || water == 0) {
            level = 0;
        } else {
            int targetLvl = 1;
            if (hasSecurity) {
                targetLvl = 2;
            }
            if (targetLvl == 2 && population > 0) {
                targetLvl = 3;
            }
            level = targetLvl;
        }
        if (level > oldLevel) {
            System.out.println("Industrial at (" + x + "," + y + ") levels up from " + oldLevel + " to " + level);
        } else if (level < oldLevel) {
            System.out.println("Industrial at (" + x + "," + y + ") levels down from " + oldLevel + " to " + level);
        }
    }

    @Override
    public int calculateProduction() {
        if (level == 0) {
            return 0;
        }
        int m = Math.min(electricity, water);
        if (m == 0) {
            m = 1;
        }
        switch (level) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3:
                return 2 * m + population;
            default:
                return 0;
        }
    }
}

class Commercial extends Zone {
    public Commercial(int x, int y) {
        super(x, y, 'C');
    }

    @Override
    public void updateZone() {
        int oldLevel = level;
        if (electricity == 0 || water == 0 || internet == 0) {
            level = 0;
        } else {
            int targetLvl = 1;
            if (hasSecurity) {
                targetLvl = 2;
            }
            if (targetLvl == 2 && population > 0 && goods > 0) {
                targetLvl = 3;
            }
            level = targetLvl;
        }
        if (level > oldLevel) {
            System.out.println("Commercial at (" + x + "," + y + ") levels up from " + oldLevel + " to " + level);
        } else if (level < oldLevel) {
            System.out.println("Commercial at (" + x + "," + y + ") levels down from " + oldLevel + " to " + level);
        }
    }

    @Override
    public int calculateProduction() {
        if (level == 0) {
            return 0;
        }
        int m = Math.min(electricity, Math.min(water, internet));
        if (m == 0) {
            m = 1;
        }
        switch (level) {
            case 1:
                return m;
            case 2:
                return 2 * m;
            case 3:
                return 2 * m + Math.min(population, goods);
            default:
                return 0;
        }
    }
}