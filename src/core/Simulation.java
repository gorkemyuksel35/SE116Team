package core;

import cells.*;
import distribution.ServiceDistribution;
import distribution.UtilityDistribution;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private CityMap cityMap;
    private int maxTicks;

    private int currentPooledPopulation = 0;
    private int currentPooledGoods = 0;
    private int currentPooledLifestyle = 0;

    public Simulation(CityMap cityMap, int maxTicks) {
        this.cityMap = cityMap;
        this.maxTicks = maxTicks;
    }

    public void run() {
        for (int tick = 1; tick <= maxTicks; tick++) {
            System.out.println();
            System.out.println("=================================");
            System.out.println("TICK " + tick);
            System.out.println("=================================");

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
        if (currentPooledPopulation == 0 && currentPooledGoods == 0 && currentPooledLifestyle == 0) {
            return;
        }

        List<Zone> housingZones = new ArrayList<>();
        List<Zone> industrialZones = new ArrayList<>();
        List<Zone> commercialZones = new ArrayList<>();

        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    char symbol = cell.getSymbol();

                    if (symbol == 'H') {
                        housingZones.add((Zone) cell);
                    } else if (symbol == 'I') {
                        industrialZones.add((Zone) cell);
                    } else if (symbol == 'C') {
                        commercialZones.add((Zone) cell);
                    }
                }
            }
        }

        int totalJobZones = industrialZones.size() + commercialZones.size();

        if (totalJobZones > 0 && currentPooledPopulation > 0) {
            int share = currentPooledPopulation / totalJobZones;

            for (Zone z : industrialZones) {
                z.setPopulation(share);
            }
            for (Zone z : commercialZones) {
                z.setPopulation(share);
            }
        }

        if (!commercialZones.isEmpty() && currentPooledGoods > 0) {
            int share = currentPooledGoods / commercialZones.size();

            for (Zone z : commercialZones) {
                z.setGoods(share);
            }
        }

        if (!housingZones.isEmpty() && currentPooledLifestyle > 0) {
            int share = currentPooledLifestyle / housingZones.size();

            for (Zone z : housingZones) {
                z.setLifestyle(share);
            }
        }
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
        int nextPopulation = 0;
        int nextGoods = 0;
        int nextLifestyle = 0;

        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int prod = zone.calculateProduction();
                    zone.setLastProduction(prod);

                    char symbol = zone.getSymbol();
                    if (symbol == 'H') {
                        nextPopulation += prod;
                    } else if (symbol == 'I') {
                        nextGoods += prod;
                    } else if (symbol == 'C') {
                        nextLifestyle += prod;
                    }
                }
            }
        }

        currentPooledPopulation = nextPopulation;
        currentPooledGoods = nextGoods;
        currentPooledLifestyle = nextLifestyle;

        System.out.println("\n=== PRODUCTION REPORT ===");
        System.out.println("Pooled Population: " + currentPooledPopulation);
        System.out.println("Pooled Goods: " + currentPooledGoods);
        System.out.println("Pooled Lifestyle: " + currentPooledLifestyle);
    }

    private void printZones() {
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    System.out.print(cell + " ");
                } else {
                    System.out.print(cell.getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }
}