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

public class Main {
    public static void main(String[] args) {
        CityMap testCM = new CityMap(5,5);
        testCM.setCell(1, 1, new Road(1, 1));
        testCM.setCell(1, 2, new PowerPlant(1, 2));
        testCM.setCell(1, 3, new WaterStation(1, 3));
        testCM.setCell(1, 4, new InternetHub(1, 4));
        testCM.printMap();
    }
}
