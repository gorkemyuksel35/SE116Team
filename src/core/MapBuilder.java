package core;

import cells.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapBuilder {

    public static CityMap buildFromFile(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));

            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new RuntimeException("Map File is empty: " + filePath);
            }

            String[] dimensions = firstLine.trim().split("\\s+");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            CityMap cityMap = new CityMap(rows, cols);

            for (int i = 0; i < rows; i++) {
                String line = reader.readLine();
                if (line == null) {
                    throw new RuntimeException("Map File has missing rows at line: " + i);
                }

                String[] tokens = line.trim().split("\\s+");

                if (tokens.length != cols) {
                    throw new RuntimeException("In Map File " + i + ". line token count is inconsistent. " + "Expected: " + cols + ", Found: " + tokens.length);
                }

                for (int j = 0; j < cols; j++) {
                    char symbol = tokens[j].charAt(0);
                    Cell cell = createCell(symbol, i, j);
                    cityMap.setCell(i, j, cell);
                }
            }
            return cityMap;

        } catch (IOException e) {
            throw new RuntimeException("Map File didn't read: " + filePath + " -> " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static Cell createCell(char symbol, int x, int y) {
        switch (symbol) {
            case 'H':
                return new Housing(x, y);
            case 'I':
                return new Industrial(x, y);
            case 'C':
                return new Commercial(x, y);
            case 'P':
                return new PowerPlant(x, y);
            case 'W':
                return new WaterStation(x, y);
            case 'T':
                return new InternetHub(x, y);
            case 'F':
                return new PoliceStation(x, y);
            case 'D':
                return new Hospital(x, y);
            case 'S':
                return new School(x, y);
            case 'R':
                return new Road(x, y);
            case 'E':
                return new Empty(x, y);
            default:
                throw new RuntimeException("Invalid Cell: '" + symbol + "' Coordinates: (" + x + ", " + y + ")");
        }
    }
}
