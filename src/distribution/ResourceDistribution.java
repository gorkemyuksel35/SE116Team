package distribution;

import cells.Cell;
import cells.Zone;
import core.CityMap;
import zones.Housing;
import zones.Industrial;
import zones.Commercial;

import java.util.ArrayList;
import java.util.List;

public class ResourceDistribution {
    public static void distribute(CityMap cityMap) {
        List<Housing>    houses      = new ArrayList<>();
        List<Industrial> industrials = new ArrayList<>();
        List<Commercial> commercials = new ArrayList<>();
        // adding the zones
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);
                if (cell instanceof Housing)    houses.add((Housing) cell);
                else if (cell instanceof Industrial) industrials.add((Industrial) cell);
                else if (cell instanceof Commercial) commercials.add((Commercial) cell);
            }
        }
        // the summation of the lastProduction for all of the housings
        int totalPopulation = 0;
        for (Housing h : houses) {
            totalPopulation += h.lastProduction;
        }

        // dividing for each of the industrial and commercials
        int indComCount = industrials.size() + commercials.size();
        if (indComCount > 0 && totalPopulation > 0) {
            int popPerZone = totalPopulation / indComCount; // tamsayi bolmesi
            for (Industrial ind : industrials) {
                ind.population += popPerZone;
            }
            for (Commercial com : commercials) {
                com.population += popPerZone;
            }
        }

        //summation of the last Productions for all Industrials
        int totalGoods = 0;
        for (Industrial ind : industrials) {
            totalGoods += ind.lastProduction;
        }

        // dividing for each commercial
        if (!commercials.isEmpty() && totalGoods > 0) {
            int goodsPerCom = totalGoods / commercials.size(); // tamsayi bolmesi
            for (Commercial com : commercials) {
                com.goods += goodsPerCom;
            }
        }

        //summation of the last Productions for all Commercials
        int totalLifestyle = 0;
        for (Commercial com : commercials) {
            totalLifestyle += com.lastProduction;
        }

        // dividing equally for each housing
        if (!houses.isEmpty() && totalLifestyle > 0) {
            int lifestylePerHouse = totalLifestyle / houses.size(); // tamsayi bolmesi
            for (Housing h : houses) {
                h.lifestyle += lifestylePerHouse;
            }
        }
    }
}



