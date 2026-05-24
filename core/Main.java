//
//
//
//
// For now, this class is test class.
// I tested my codes and everything is working properly. Now, we have something that actually works.


package core;

import cells.InternetHub;
import cells.PowerPlant;
import cells.Road;
import cells.WaterStation;
import cells.PoliceStation;
import cells.Hospital;
import cells.School;
import zones.Housing;
import zones.Industrial;
import zones.Commercial;

public class Main {
    public static void main(String[] args) {
        CityMap testCM = new CityMap(5,5);
        testCM.setCell(1, 1, new Road(1, 1));
        testCM.setCell(1, 2, new PowerPlant(1, 2));
        testCM.setCell(1, 3, new WaterStation(1, 3));
        testCM.setCell(2, 2, new InternetHub(2, 2));
        testCM.setCell(3, 1, new PoliceStation(3, 1, 3));
        testCM.setCell(3, 2, new Hospital(3, 2, 2));
        testCM.setCell(3, 3, new School(3, 3, 4));
        testCM.setCell(2, 2, new Housing(2, 2));
        testCM.setCell(2, 3, new Industrial(2, 3));
        testCM.setCell(3, 2, new Commercial(3, 2));
        testCM.printMap();
    }
}
