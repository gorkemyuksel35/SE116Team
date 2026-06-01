package core;

import cells.*;
import zones.*;

public class Main {
    public static void main(String[] args) {
        CityMap testCMv2 = new CityMap(6, 6);
        for (int i = 0; i < 6; i++) {
            testCMv2.setCell(i, 1, new Road(i, 1));
            testCMv2.setCell(i, 3, new Road(i, 3));
        }

        testCMv2.setCell(2, 2, new Road(2, 2));
        testCMv2.setCell(3, 2, new Road(3, 2));
        testCMv2.setCell(0, 1, new PowerPlant(0, 1));
        testCMv2.setCell(5, 1, new WaterStation(5, 1));
        testCMv2.setCell(0, 3, new InternetHub(0, 3));
        testCMv2.setCell(1, 2, new PoliceStation(1, 2));
        testCMv2.setCell(4, 2, new Hospital(4, 2));
        testCMv2.setCell(5, 2, new School(5, 2));
        testCMv2.setCell(1, 0, new Housing(1, 0));
        testCMv2.setCell(2, 0, new Housing(2, 0));
        testCMv2.setCell(3, 0, new Housing(3, 0));
        testCMv2.setCell(4, 0, new Housing(4, 0));
        testCMv2.setCell(2, 4, new Industrial(2, 4));
        testCMv2.setCell(4, 4, new Commercial(4, 4));
        Simulation simulation = new Simulation(testCMv2, 15);
        simulation.run();
    }
}
