//package zones;
//
//import cells.Zone;
//
//public class Industrial extends Zone {
//    public Industrial(int x, int y) {
//        super(x, y, 'I');
//    }
//
//    @Override
//    public void updateZone() {
//        if (electricity == 0 || water == 0) {
//            level = 0;
//            return;
//        }
//
//        int targetLvl = 1;
//
//        if (security && health && education) {
//            targetLvl = 2;
//        }
//
//        if (targetLvl == 2 && population > 0) {
//            targetLvl = 3;
//        }
//
//        if (targetLvl > level) {
//            level++;
//        } else if (targetLvl < level) {
//            level--;
//        }
//    }
//
//    @Override
//    public int calculateProduction() {
//        int m = Math.min(electricity, water);
//
//        switch (level) {
//            case 1:
//                return m;
//            case 2:
//                return 2 * m;
//            case 3:
//                return 2 * m + population;
//            default:
//                return 0;
//        }
//    }
//}