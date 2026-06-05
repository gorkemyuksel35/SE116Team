//package zones;
//
//import cells.Zone;
//
//public class Housing extends Zone {
//    public Housing(int x, int y) {
//        super(x, y, 'H');
//    }
//
//    @Override
//    public void updateZone() {
//        if (electricity == 0 || water == 0 || internet == 0) {
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
//        if (targetLvl == 2 && lifestyle > 0) {
//            targetLvl = 3;
//        }
//
//        if (targetLvl > level) {
//            level++;
//        } else if (targetLvl < level) {
//            level--;
//        }
//
//        System.out.println(symbol + " (" + x + "," + y + ") updated to level " + level);
//    }
//
//    @Override
//    public int calculateProduction() {
//        int m = Math.min(electricity, Math.min(water, internet));
//
//        switch (level) {
//            case 1:
//                return m;
//            case 2:
//                return 2 * m;
//            case 3:
//                return 2 * m + lifestyle; // 2m + lifestyle received
//            default:
//                return 0;
//        }
//    }
//}