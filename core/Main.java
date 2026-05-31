package core;

import cells.*;
import zones.*;

public class Main {
    public static void main(String[] args) {
        CityMap testCMv2 = new CityMap(5,5);
        testCMv2.setCell(1, 1, new Housing(1, 1));
        testCMv2.setCell(1, 2, new Industrial(1, 2));
        testCMv2.setCell(1, 3, new Commercial(1, 3));
        Simulation simulation = new Simulation(testCMv2, 5);
        simulation.run();
    }
}
