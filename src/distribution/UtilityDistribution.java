package distribution;

import cells.*;
import core.*;
import java.util.*;

public class UtilityDistribution {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void distribute(CityMap cityMap, UtilityProvider utilityProvider) {
        Queue<Cell> queue = new LinkedList<>();
        queue.add(utilityProvider);

        boolean[][] visited = new boolean[cityMap.getRows()][cityMap.getCols()];
        visited[utilityProvider.getX()][utilityProvider.getY()] = true;
        int remaining = utilityProvider.getCap();

        while (!queue.isEmpty() && remaining > 0) {
            Cell current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = current.getX() + dx[i];
                int newY = current.getY() + dy[i];

                if (!cityMap.isValidPosition(newX, newY)) {
                    continue;
                }

                if (visited[newX][newY]) {
                    continue;
                }

                Cell next = cityMap.getCell(newX, newY);
                if (next == null || !next.isConnectable()) {
                    continue;
                }

                visited[newX][newY] = true;
                queue.add(next);

                if (next instanceof Zone) {
                    Zone zone = (Zone) next;

                    int demand = zone.getDemand();
                    int given = Math.min(demand, remaining);

                    switch (utilityProvider.getUtilType()) {
                        case "Electricity":
                            zone.receiveElectricity(given);
                            break;
                        case "Water":
                            zone.receiveWater(given);
                            break;
                        case "Internet":
                            zone.receiveInternet(given);
                            break;
                    }

                    if (zone.getElectricity() > 0 && zone.getWater() > 0 && zone.getInternet() > 0) {
                        zone.setHasUtilities(true);
                    }
                    remaining -= given;
                }
            }
        }
    }
}