package core;

import cells.*;
import distribution.*;

public class Simulation {
    private CityMap cityMap;
    private int maxTicks;
    private int currentPooledPopulation = 0;
    private int currentPooledGoods = 0;
    private int currentPooledLifestyle = 0;
    private int nextTickPopulation = 0;
    private int nextTickGoods = 0;
    private int nextTickLifestyle = 0;

    public Simulation(CityMap cityMap, int maxTicks) {
        this.cityMap = cityMap;
        this.maxTicks = maxTicks;
    }

    public void run() {
        for (int tick = 1; tick <= maxTicks; tick++) {
            System.out.println("Tick " + tick);
            currentPooledPopulation += nextTickPopulation;
            currentPooledGoods += nextTickGoods;
            currentPooledLifestyle += nextTickLifestyle;
            nextTickPopulation = 0;
            nextTickGoods = 0;
            nextTickLifestyle = 0;
            resetZones();
            distributeServices();
            distributeUtilities();
            updateZones();
            distributeResources();
            collectProduction();
        }
    }

    private void resetZones() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);
                if (cell instanceof Zone) {
                    ((Zone) cell).resetInputs();
                }
            }
        }
    }

    private void distributeServices() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);
                if (cell instanceof ServiceBuilding) {
                    ServiceDistribution.distribute(cityMap, (ServiceBuilding) cell);
                }
            }
        }
    }

    private void distributeUtilities() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);
                if (cell instanceof UtilityProvider) {
                    UtilityDistribution.distribute(cityMap, (UtilityProvider) cell);
                }
            }
        }
    }

    private void distributeResources() {
        ResourceDistribution.distribute(cityMap, this);
    }

    private void updateZones() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);
                if (cell instanceof Zone) {
                    ((Zone) cell).updateZone();
                }
            }
        }
    }

    private void collectProduction() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);
                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int prod = zone.calculateProduction();
                    char symbol = zone.getSymbol();
                    String typeName = zone.getClass().getSimpleName();
                    if (typeName.equals("Housing")) {
                        typeName = "House";
                    }

                    String resName = "population";
                    if (symbol == 'H') {
                        nextTickPopulation += prod;
                        resName = "population";
                    } else if (symbol == 'I') {
                        nextTickGoods += prod;
                        resName = "goods";
                    } else if (symbol == 'C') {
                        nextTickLifestyle += prod;
                        resName = "lifestyle";
                    }
                    zone.setLastProduction(prod);
                    System.out.println(typeName + " at (" + i + "," + j + ") generated " + prod + " " + resName);
                }
            }
        }
    }

    public int getCurrentPooledPopulation() {
        return currentPooledPopulation;
    }

    public void setCurrentPooledPopulation(int val) {
        this.currentPooledPopulation = val;
    }

    public int getCurrentPooledGoods() {
        return currentPooledGoods;
    }

    public void setCurrentPooledGoods(int val) {
        this.currentPooledGoods = val;
    }

    public int getCurrentPooledLifestyle() {
        return currentPooledLifestyle;
    }

    public void setCurrentPooledLifestyle(int val) {
        this.currentPooledLifestyle = val;
    }
}