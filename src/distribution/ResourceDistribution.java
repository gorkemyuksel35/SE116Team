package distribution;

import cells.*;
import core.*;
import java.util.*;

public class ResourceDistribution {
    public static void distribute(CityMap cityMap, Simulation simulation) {
        List<Zone> housingZones    = new ArrayList<>();
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

        int totalPopulation = simulation.getCurrentPooledPopulation();
        int indComCount = industrialZones.size() + commercialZones.size();

        if (indComCount > 0 && totalPopulation > 0) {
            int baseShare = totalPopulation / indComCount;
            int remainder = totalPopulation % indComCount;

            for (Zone ind : industrialZones) {
                ind.setPopulation(baseShare);
            }
            for (Zone com : commercialZones) {
                com.setPopulation(baseShare);
            }

            for (Zone ind : industrialZones) {
                if (remainder > 0) {
                    ind.setPopulation(ind.getPopulation() + 1); remainder--;
                }
            }
            for (Zone com : commercialZones) {
                if (remainder > 0) {
                    com.setPopulation(com.getPopulation() + 1); remainder--;
                }
            }
            simulation.setCurrentPooledPopulation(remainder);
        }

        int totalGoods = simulation.getCurrentPooledGoods();
        if (!commercialZones.isEmpty() && totalGoods > 0) {
            int baseShare = totalGoods / commercialZones.size();
            int remainder = totalGoods % commercialZones.size();

            for (Zone com : commercialZones) {
                com.setGoods(baseShare);
            }
            for (Zone com : commercialZones) {
                if (remainder > 0) {
                    com.setGoods(com.getGoods() + 1); remainder--;
                }
            }
            simulation.setCurrentPooledGoods(remainder);
        }

        int totalLifestyle = simulation.getCurrentPooledLifestyle();
        if (!housingZones.isEmpty() && totalLifestyle > 0) {
            int baseShare = totalLifestyle / housingZones.size();
            int remainder = totalLifestyle % housingZones.size();

            for (Zone h : housingZones) {
                h.setLifestyle(baseShare);
            }
            for (Zone h : housingZones) {
                if (remainder > 0) {
                    h.setLifestyle(h.getLifestyle() + 1); remainder--;
                }
            }
            simulation.setCurrentPooledLifestyle(remainder);
        }

        for (Zone ind : industrialZones) {
            if (ind.getPopulation() > 0) {
                System.out.println("Industrial at (" + ind.getX() + "," + ind.getY() + ") received " + ind.getPopulation() + " population");
            }
        }
        for (Zone com : commercialZones) {
            if (com.getPopulation() > 0) {
                System.out.println("Commercial at (" + com.getX() + "," + com.getY() + ") received " + com.getPopulation() + " population");
            }
            if (com.getGoods() > 0) {
                System.out.println("Commercial at (" + com.getX() + "," + com.getY() + ") received " + com.getGoods() + " goods");
            }
        }
        for (Zone h : housingZones) {
            if (h.getLifestyle() > 0) {
                System.out.println("House at (" + h.getX() + "," + h.getY() + ") received " + h.getLifestyle() + " lifestyle");
            }
        }
    }
}