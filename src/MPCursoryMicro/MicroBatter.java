package MPCursoryMicro;

import aic2023.user.*;

public class MicroBatter {

    final int INF = 1000000;
    boolean alwaysInRange = false;
    float ACTION_RANGE;
    float VISION_RANGE;

    final int RANGE_EXTENDED_BATTER = 8;
    final int RANGE_BATTER = 2;

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

    MicroBatter(UnitController uc, Robot r) {
        this.uc = uc;
        robot = r;
        ACTION_RANGE = robot.ACTION_RANGE;
        VISION_RANGE = robot.VISION_RANGE;
    }

    double currentActionRadius;
    double currentExtendedActionRadius;
    boolean canAttack;
    Location currentLoc;

    boolean doMicro() {
        if (!uc.canMove())
            return false;
        UnitInfo[] units = robot.enemies;
        if (units.length == 0)
            return false;
        canAttack = uc.canAct();

        alwaysInRange = false;
        if (!canAttack)
            alwaysInRange = true;

        MicroInfo[] microInfo = new MicroInfo[9];
        for (int i = 0; i < 9; ++i)
            microInfo[i] = new MicroInfo(dirs[i]);

        for (UnitInfo unit : units) {
            if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                break;
            if (unit.getType() == UnitType.BATTER) {
                currentActionRadius = RANGE_BATTER;
                currentExtendedActionRadius = RANGE_EXTENDED_BATTER;
            } else {
                currentActionRadius = 0;
                currentExtendedActionRadius = 0;
            }

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

        units = uc.senseUnits(VISION_RANGE, uc.getTeam());
        for (UnitInfo unit : units) {
            if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                break;
            if (unit.getType() == UnitType.BATTER) {
                currentActionRadius = RANGE_BATTER;
                currentExtendedActionRadius = RANGE_EXTENDED_BATTER;
            } else {
                currentActionRadius = 0;
                currentExtendedActionRadius = 0;
            }

            currentLoc = unit.getLocation();

            microInfo[0].updateAlly();
            microInfo[1].updateAlly();
            microInfo[2].updateAlly();
            microInfo[3].updateAlly();
            microInfo[4].updateAlly();
            microInfo[5].updateAlly();
            microInfo[6].updateAlly();
            microInfo[7].updateAlly();
            microInfo[8].updateAlly();
        }

        MicroInfo bestMicro = microInfo[8];
        for (int i = 0; i < 8; ++i) {
            if (microInfo[i].isBetter(bestMicro))
                bestMicro = microInfo[i];
        }

        return apply(bestMicro);
    }

    boolean apply(MicroInfo bestMicro) {
        if (bestMicro.dir == Direction.ZERO)
            return true;

        if (uc.canMove(bestMicro.dir)) {
            robot.move(bestMicro.dir);

            if (bestMicro.target != null) {
                Location currLoc = uc.getLocation();
                Direction dir = currLoc.directionTo(bestMicro.target);
                if (uc.canBat(dir, 3)) {
                    uc.bat(dir, 3);
                }
            }

            return true;
        }
        return false;
    }

    class MicroInfo {
        Direction dir;
        Location location;
        int minDistanceToEnemy = INF;

        int canLandHit = 0;
        int battersAttackRange = 0;
        int battersVisionRange = 0;
        int possibleEnemybatters = 0;
        int minDistToAlly = INF;
        Location target = null;
        boolean canMove = true;

        public MicroInfo(Direction dir) {
            this.dir = dir;
            this.location = uc.getLocation().add(dir);
            if (dir != Direction.ZERO && !uc.canMove(dir))
                canMove = false;
            minDistanceToEnemy = INF;
        }

        void updateEnemy() {
            if (!canMove)
                return;
            int dist = currentLoc.distanceSquared(location);
            if (dist < minDistanceToEnemy)
                minDistanceToEnemy = dist;
            if (dist <= currentActionRadius)
                battersAttackRange++;
            if (dist <= 20)
                battersVisionRange++;
            if (dist <= currentExtendedActionRadius)
                possibleEnemybatters++;
            if (dist <= ACTION_RANGE && canAttack) {
                canLandHit = 1;
                target = currentLoc;
            }
        }

        void updateAlly() {
            if (!canMove)
                return;
            int dist = currentLoc.distanceSquared(location);
            if (dist < minDistToAlly)
                minDistToAlly = dist;
            // if (dist <= 2) alliesTargeting += currentDMG;
        }

        boolean inRange() {
            if (alwaysInRange)
                return true;
            return minDistanceToEnemy <= ACTION_RANGE;
        }

        // equal => true
        boolean isBetter(MicroInfo M) {

            // if (safe() > M.safe()) return true;
            // if (safe() < M.safe()) return false;

            if (canMove && !M.canMove)
                return true;
            if (!canMove && M.canMove)
                return false;

            if (battersAttackRange - canLandHit < M.battersAttackRange - M.canLandHit)
                return true;
            if (battersAttackRange - canLandHit > M.battersAttackRange - M.canLandHit)
                return false;

            // if (battersVisionRange - canLandHit < M.battersVisionRange - M.canLandHit)
            // return true;
            // if (battersVisionRange - canLandHit > M.battersVisionRange - M.canLandHit)
            // return false;

            if (canLandHit > M.canLandHit)
                return true;
            if (canLandHit < M.canLandHit)
                return false;

            if (possibleEnemybatters < M.possibleEnemybatters)
                return true;
            if (possibleEnemybatters > M.possibleEnemybatters)
                return false;

            // if (minDistToAlly < M.minDistToAlly)
            // return true;
            // if (minDistToAlly > M.minDistToAlly)
            // return false;

            if (inRange())
                return minDistanceToEnemy >= M.minDistanceToEnemy;
            else
                return minDistanceToEnemy <= M.minDistanceToEnemy;
        }
    }
}
