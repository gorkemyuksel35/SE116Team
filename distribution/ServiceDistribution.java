package distribution;

import cells.ServiceBuilding;
import cells.Cell;
import cells.Zone;
import core.CityMap;
import zones.Housing;
import zones.Industrial;
import zones.Commercial;

public class ServiceDistribution {
    public static void distribute(CityMap cityMap, ServiceBuilding serviceBuilding) {
        int sx = serviceBuilding.getX();
        int sy = serviceBuilding.getY();
        int radius = serviceBuilding.getRadius();
        char serviceSymbol = serviceBuilding.getSymbol();
    }
    }