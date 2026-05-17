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
        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (!(cell instanceof Zone)) {
                    continue;
                }
                double distance = Math.sqrt(Math.pow(i - sx, 2) + Math.pow(j - sy, 2));

                if (distance <= radius) {
                    Zone zone = (Zone) cell;
                    applyService(zone, serviceSymbol);
                }
            }
        }
    }
    private static void applyService(Zone zone, char serviceSymbol) {
        switch (serviceSymbol) {
            case 'F': // police station -> security
                zone.security = true;
                break;
            case 'D': // hospital -> health
                zone.health = true;
                break;
            case 'S': // school -> education
                zone.education = true;
                break;
        }
    }
}


