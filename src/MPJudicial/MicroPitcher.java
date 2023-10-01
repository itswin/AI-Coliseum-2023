package MPJudicial;

import aic2023.user.*;

public class MicroPitcher {

    final int INF = 1000000;
    final float RANGE_EXTENDED_BATTER = 20;
    final float RANGE_BATTER = 2;
    float ACTION_RANGE;

    final Direction[] dirs = {
            Direction.NORTH,
            Direction.NORTHWEST,
            Direction.WEST,
            Direction.SOUTHWEST,
            Direction.SOUTH,
            Direction.SOUTHEAST,
            Direction.EAST,
            Direction.NORTHEAST,
            Direction.ZERO
    };

    final int MAX_MICRO_BYTECODE_REMAINING = 2000;

    UnitController uc;
    Robot robot;

    public MicroPitcher(UnitController u, Robot r) {
        uc = u;
        robot = r;
        ACTION_RANGE = robot.ACTION_RANGE;
    }

    double currentActionRadius;
    Location currentLoc;

    boolean flee() {
        boolean didMicro = false;
        if (uc.canMove()) {
            doMicro(true);
            didMicro = !uc.canMove();
        }
        return didMicro;
    }

    boolean doMicro(boolean flee) {
        if (!uc.canMove())
            return false;
        UnitInfo[] units = robot.enemies;
        if (units.length == 0)
            return false;

        MicroInfo[] microInfo = new MicroInfo[9];
        int i = 9;
        for (; --i >= 0;)
            microInfo[i] = new MicroInfo(dirs[i]);

        currentActionRadius = RANGE_BATTER;
        if (flee)
            currentActionRadius = RANGE_EXTENDED_BATTER;

        UnitInfo unit;
        i = units.length;
        for (; --i >= 0;) {
            if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                break;
            unit = units[i];
            if (unit.getType() != UnitType.BATTER)
                continue; // TODO: maybe add pitchers
            currentLoc = unit.getLocation();
            microInfo[0].updateEnemy();
            microInfo[1].updateEnemy();
            microInfo[2].updateEnemy();
            microInfo[3].updateEnemy();
            microInfo[4].updateEnemy();
            microInfo[5].updateEnemy();
            microInfo[6].updateEnemy();
            microInfo[7].updateEnemy();
            microInfo[8].updateEnemy();
        }

        MicroInfo bestMicro = microInfo[8];
        if (flee) {
            i = 8;
            for (; --i >= 0;) {
                if (microInfo[i].isBetterForFleeing(bestMicro)) {
                    bestMicro = microInfo[i];
                }
            }
        } else {
            i = 8;
            for (; --i >= 0;) {
                if (microInfo[i].isBetter(bestMicro)) {
                    bestMicro = microInfo[i];
                }
            }
        }

        if (bestMicro.dir == Direction.ZERO)
            return true;

        if (uc.canMove(bestMicro.dir)) {
            robot.move(bestMicro.dir);
            return true;
        }

        return false;
    }

    class MicroInfo {
        Direction dir;
        Location location;
        int minDistanceToEnemy = INF;

        int battersTargeting = 0;
        boolean canMove = true;

        public MicroInfo(Direction dir) {
            this.dir = dir;
            this.location = uc.getLocation().add(dir);
            if (dir != Direction.ZERO && !uc.canMove(dir))
                canMove = false;
            else {
                minDistanceToEnemy = INF;
            }
        }

        void updateEnemy() {
            if (!canMove)
                return;
            int dist = currentLoc.distanceSquared(location);
            if (dist < minDistanceToEnemy)
                minDistanceToEnemy = dist;
            if (dist <= currentActionRadius)
                ++battersTargeting;
        }

        int safe() {
            if (!canMove)
                return -1;
            return 1;
        }

        boolean inRange() {
            return minDistanceToEnemy <= ACTION_RANGE;
        }

        // equal => true
        boolean isBetter(MicroInfo M) {

            if (safe() > M.safe())
                return true;
            if (safe() < M.safe())
                return false;

            if (inRange() && !M.inRange())
                return true;
            if (!inRange() && M.inRange())
                return false;

            if (battersTargeting < M.battersTargeting)
                return true;
            if (M.battersTargeting < battersTargeting)
                return false;

            if (inRange()) {
                return minDistanceToEnemy >= M.minDistanceToEnemy;
            }
            if (dir == Direction.ZERO)
                return true;
            if (M.dir == Direction.ZERO)
                return false;
            return minDistanceToEnemy <= M.minDistanceToEnemy;
        }

        boolean isBetterForFleeing(MicroInfo M) {
            if (safe() > M.safe())
                return true;
            if (safe() < M.safe())
                return false;

            if (battersTargeting < M.battersTargeting)
                return true;
            if (M.battersTargeting < battersTargeting)
                return false;

            return minDistanceToEnemy > M.minDistanceToEnemy;
        }
    }
}
