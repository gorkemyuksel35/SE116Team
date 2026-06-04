package core;

import cells.*;
import java.io.*;
import java.util.*;

public class MapBuilder {

    public static CityMap buildFromFile(String filePath) {
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }

            if (lines.isEmpty()) {
                throw new RuntimeException("Map File is empty: " + filePath);
            }

            int rows = lines.size();
            int cols = lines.get(0).length();

            System.out.println("Dynamically discovered map size: " + rows + " Rows x " + cols + " Columns.");

            CityMap cityMap = new CityMap(rows, cols);

            for (int i = 0; i < rows; i++) {
                String currentLine = lines.get(i);

                if (currentLine.length() != cols) {
                    throw new RuntimeException("Inconsistent column count at row " + i + ". Expected: " + cols + ", Found: " + currentLine.length());
                }

                for (int j = 0; j < cols; j++) {
                    char symbol = currentLine.charAt(j);
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