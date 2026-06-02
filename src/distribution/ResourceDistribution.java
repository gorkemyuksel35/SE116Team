package distribution;

import cells.*;
import core.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDistribution {
    public static void distribute(CityMap cityMap, Simulation simulation) {
        List<Zone> housingZones    = new ArrayList<>();
        List<Zone> industrialZones = new ArrayList<>();
        List<Zone> commercialZones = new ArrayList<>();

        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    char symbol = zone.getSymbol();

                    if (symbol == 'H') {
                        housingZones.add(zone);
                    } else if (symbol == 'I') {
                        industrialZones.add(zone);
                    } else if (symbol == 'C') {
                        commercialZones.add(zone);
                    }
                }
            }
        }

        int totalPopulation = simulation.getCurrentPooledPopulation();
        int indComCount = industrialZones.size() + commercialZones.size();

        if (indComCount > 0 && totalPopulation > 0) {
            int popPerZone = totalPopulation / indComCount;
            int remainder = totalPopulation % indComCount;

            for (Zone ind : industrialZones) ind.setPopulation(ind.getPopulation() + popPerZone);
            for (Zone com : commercialZones) com.setPopulation(com.getPopulation() + popPerZone);
            simulation.setCurrentPooledPopulation(remainder);
        } else {
            simulation.setCurrentPooledPopulation(indComCount == 0 ? totalPopulation : 0);
        }

        int totalGoods = simulation.getCurrentPooledGoods();
        if (!commercialZones.isEmpty() && totalGoods > 0) {
            int goodsPerCom = totalGoods / commercialZones.size();
            int remainder = totalGoods % commercialZones.size();

            for (Zone com : commercialZones) com.setGoods(com.getGoods() + goodsPerCom);
            simulation.setCurrentPooledGoods(remainder);
        } else {
            simulation.setCurrentPooledGoods(commercialZones.isEmpty() ? totalGoods : 0);
        }

        int totalLifestyle = simulation.getCurrentPooledLifestyle();
        if (!housingZones.isEmpty() && totalLifestyle > 0) {
            int lifestylePerHouse = totalLifestyle / housingZones.size();
            int remainder = totalLifestyle % housingZones.size();

            for (Zone h : housingZones) h.setLifestyle(h.getLifestyle() + lifestylePerHouse);
            simulation.setCurrentPooledLifestyle(remainder);
        } else {
            simulation.setCurrentPooledLifestyle(housingZones.isEmpty() ? totalLifestyle : 0);
        }
    }
}