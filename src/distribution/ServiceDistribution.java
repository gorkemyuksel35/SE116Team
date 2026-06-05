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

                if (cell instanceof Zone) {
                    double distance = Math.sqrt(Math.pow(i - sx, 2) + Math.pow(j - sy, 2));

                    if (distance <= radius) {
                        applyService(cell, serviceSymbol, i, j);
                    }
                }
            }
        }
    }

    private static void applyService(Object zoneObj, char serviceSymbol, int x, int y) {
        if (zoneObj instanceof Zone) {
            Zone zone = (Zone) zoneObj;
            String typeName = zone.getClass().getSimpleName();
            if (typeName.equals("Housing")) {
                typeName = "House";
            }

            switch (serviceSymbol) {
                case 'F':
                    if (!zone.isHasSecurity()) {
                        zone.setHasSecurity(true);
                        System.out.println(typeName + " at (" + x + "," + y + ") received security service");
                    }
                    break;
                case 'D':
                    if (!zone.isHasHealth()) {
                        zone.setHasHealth(true);
                        System.out.println(typeName + " at (" + x + "," + y + ") received health service");
                    }
                    break;
                case 'S':
                    if (!zone.isHasEducation()) {
                        zone.setHasEducation(true);
                        System.out.println(typeName + " at (" + x + "," + y + ") received education service");
                    }
                    break;
            }
        }
    }
}
