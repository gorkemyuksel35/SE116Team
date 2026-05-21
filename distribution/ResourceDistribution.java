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

    }
