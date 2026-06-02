package distribution;

import cells.*;
import core.*;

public class ServiceDistribution {
    public static void distribute(CityMap cityMap, ServiceBuilding serviceBuilding) {
        int sx = serviceBuilding.getX();
        int sy = serviceBuilding.getY();
        int radius = serviceBuilding.getRadius();
        char serviceSymbol = serviceBuilding.getSymbol();

        for (int i = 0; i < cityMap.getRows(); i++) {
            for (int j = 0; j < cityMap.getCols(); j++) {
                Cell cell = cityMap.getCell(i, j);

                if (cell != null && cell.getClass().getSuperclass() != null && cell.getClass().getSuperclass().getSimpleName().equals("Zone")) {
                    double distance = Math.sqrt(Math.pow(i - sx, 2) + Math.pow(j - sy, 2));

                    if (distance <= radius) {
                        applyService(cell, serviceSymbol);
                    }
                }
            }
        }
    }

    private static void applyService(Object zoneObj, char serviceSymbol) {
        if (zoneObj instanceof Zone) {
            Zone zone = (Zone) zoneObj;
            switch (serviceSymbol) {
                case 'F': // Police Station
                    zone.setHasSecurity(true);
                    break;
                case 'D': // Hospital
                    zone.setHasHealth(true);
                    break;
                case 'S': // School
                    zone.setHasEducation(true);
                    break;
            }
        }
    }
}