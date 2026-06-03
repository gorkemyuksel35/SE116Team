package core;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== CENGGANG OBJECTVILLE ===");
        if (args.length < 2) {
            throw new IllegalArgumentException("Missing command-line arguments!");
        }

        String filePath = args[0];
        String tickInput = args[1];

        File mapFile = new File(filePath);

        if (!mapFile.exists()) {
            throw new RuntimeException("Target map file could not be found!");
        }

        int maxTicks;
        try {
            maxTicks = Integer.parseInt(tickInput);
            if (maxTicks <= 0) {
                throw new IllegalArgumentException("Simulation tick count must be a positive integer!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid tick format provided in terminal! Expected integer!");
        }

        System.out.println("Executing Map Engine for: " + mapFile.getName());
        CityMap cityMap = null;

        try {
            cityMap = MapBuilder.buildFromFile(filePath);
            System.out.println("Map structural verification successfully completed!");

        } catch (RuntimeException e) {
            throw new RuntimeException("Map parsing failed! Reason: " + e.getMessage(), e);
        }

        Simulation simulation = new Simulation(cityMap, maxTicks);
        simulation.run();
    }
}