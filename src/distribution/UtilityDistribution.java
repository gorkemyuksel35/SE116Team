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
            List<Cell> neighbors = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                int newX = current.getX() + dx[i];
                int newY = current.getY() + dy[i];

                if (cityMap.isValidPosition(newX, newY) && !visited[newX][newY]) {
                    Cell next = cityMap.getCell(newX, newY);
                    if (next != null && next.isConnectable()) {
                        neighbors.add(next);
                    }
                }
            }

            neighbors.sort((c1, c2) -> {
                if (c1.getX() != c2.getX()) {
                    return Integer.compare(c1.getX(), c2.getX());
                }
                return Integer.compare(c1.getY(), c2.getY());
            });

            for (Cell next : neighbors) {
                if (remaining <= 0) {
                    break;
                }
                if (visited[next.getX()][next.getY()]) {
                    continue;
                }

                visited[next.getX()][next.getY()] = true;
                queue.add(next);

                if (next instanceof Zone) {
                    Zone zone = (Zone) next;
                    int demand = zone.getDemand();
                    int given = Math.min(demand, remaining);

                    if (given > 0) {
                        String typeName = zone.getClass().getSimpleName();
                        if (typeName.equals("Housing")) {
                            typeName = "House";
                        }

                        switch (utilityProvider.getUtilType()) {
                            case "Electricity":
                                zone.receiveElectricity(given);
                                System.out.println(typeName + " at (" + next.getX() + "," + next.getY() + ") received " + given + " electricity");
                                break;
                            case "Water":
                                zone.receiveWater(given);
                                System.out.println(typeName + " at (" + next.getX() + "," + next.getY() + ") received " + given + " water");
                                break;
                            case "Internet":
                                zone.receiveInternet(given);
                                System.out.println(typeName + " at (" + next.getX() + "," + next.getY() + ") received " + given + " internet");
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
}