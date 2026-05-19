//
//
//
//
// For now, this class is test class.
// I tested my codes and everything is working properly. Now, we have something that actually works.


package core;

import cells.Road;

public class Main {
    public static void main(String[] args) {
        CityMap testCM = new CityMap(5,5);
        testCM.setCell(1, 1, new Road(1, 1));
        testCM.setCell(2, 1, new Road(2, 1));
        testCM.printMap();
    }
}
