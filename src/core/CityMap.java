package core;

import cells.Cell;
import cells.Empty;


public class CityMap {
    protected Cell[][] grid;
    protected int rows;
    protected int cols;

    public CityMap(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new RuntimeException("Map dimensions must be positive!");
        }
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Empty(i, j);
            }
        }
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }

    public void setCell(int x, int y, Cell cell) {
        if (!isValidPosition(x, y)) {
            throw new RuntimeException("Invalid position: (" + x + ", " + y + ")");
        }
        grid[x][y] = cell;
    }

    public Cell getCell(int x, int y) {
        if (!isValidPosition(x, y)) {
            throw new RuntimeException("Invalid position: (" + x + ", " + y + ")");
        }
        return grid[x][y];
    }

    public void printMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }
}
