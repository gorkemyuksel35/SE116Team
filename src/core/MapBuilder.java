package core;

import cells.*;
import zones.Commercial;
import zones.Housing;
import zones.Industrial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RunTimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {

    public static CityMap buildFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read the file: " + filePath + e.getMessage());
        }

        if (lines.isEmpty()) {
            throw new RuntimeException("File is empty: " + filePath);
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        for (int i = 0; i < rows; i++) {
            if (lines.get(i).length() != cols) {
                throw new RuntimeException(
                        "   The line " + i + "is not consistent with the word count of other lines  "
                                + "Expected: " + cols + ", Founded: " + lines.get(i).length());
            }
        }
        CityMap cityMap = new CityMap(rows, cols);

        for (int i = 0; i < rows; i++) {
            String row = lines.get(i);
            for (int j = 0; j < cols; j++) {
                char symbol = row.charAt(j);
                Cell cell = createCell(symbol, i, j);
                cityMap.setCell(i, j, cell);
            }
        }

        return cityMap;
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
                return new PoliceStation(x, y, 5);
            case 'D':
                return new Hospital(x, y, 3);
            case 'S':
                return new School(x, y, 4);
            case 'R':
                return new Road(x, y);
            case 'E':
                return new Empty(x, y);
            default:
                throw new RuntimeException(
                        "Invalid cell symbol: " + symbol
                                + " Coordinates: (" + x + ", " + y + ")"
                );
        }
    }
}
