package zones;

import cells.Zone;

public class Industrial extends Zone {
    public Industrial(int x, int y) {
        super(x, y, 'I');
    }

    @Override
    public void updateZone() {
        if (electricity == 0 || water == 0 || population == 0) {
            level = 0;
            return;
        }

        int targetLvl = 1;

        if (security) {
            targetLvl = 2;
        }

        if (targetLvl == 2 && population > getDemand()) {
            targetLvl = 3;
        }

        if (targetLvl > level) {
            level++;
        } else if (targetLvl < level) {
            level--;
        }

        lastProduction = calculateProduction();
    }

    @Override
    public int calculateProduction() {
        int clcltPrdctn = Math.min(electricity, water);

        switch (level) {
            case 1:
                return clcltPrdctn;
            case 2:
                return 2 * clcltPrdctn;
            case 3:
                return 2 * clcltPrdctn + population;
            default:
                return 0;
        }
    }
}
