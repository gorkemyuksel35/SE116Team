package core;

import cells.Cell;
import cells.Zone;

public class Simulation {
    private CityMap cityMap;
    private int maxTicks;
    private int pooledPopulation;
    private int pooledGoods;
    private int pooledLifestyle;

    public Simulation(CityMap cityMap, int maxTicks) {
        this.cityMap = cityMap;
        this.maxTicks = maxTicks;
    }

    public void run() {
        for (int tick = 1; tick <= maxTicks; tick++) {
            System.out.println();
            System.out.println("Tick " + tick);
            System.out.println();

            resetZones();
            distributeServices();
            distributeUtilities();
            distributeResources();
            updateZones();
            collectProduction();
            printZones();
        }
    }

    private void resetZones() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    zone.resetInputs();
                }
            }
        }
    }

    private void distributeServices() {

    }

    private void distributeUtilities() {

    }

    private void distributeResources() {

    }

    private void updateZones() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    zone.updateZone();
                }
            }
        }
    }

    private void collectProduction() {
        pooledPopulation = 0;
        pooledGoods = 0;
        pooledLifestyle = 0;

        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof zones.Housing) {
                    pooledPopulation += ((Zone) cell).calculateProduction();
                } else if (cell instanceof zones.Industrial) {
                    pooledGoods += ((Zone) cell).calculateProduction();
                } else if (cell instanceof zones.Commercial) {
                    pooledLifestyle += ((Zone) cell).calculateProduction();
                }
            }
        }
        System.out.println();
        System.out.println("Population: " + pooledPopulation);
        System.out.println("Goods: " + pooledGoods);
        System.out.println("Lifestyle: " + pooledLifestyle);
    }

    private void printZones() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    System.out.print(zone.getSymbol() + "(" + zone.getLevel() + ")");
                } else {
                    System.out.print(cell.getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }


}
