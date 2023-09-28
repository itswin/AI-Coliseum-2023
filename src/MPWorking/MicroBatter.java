package MPWorking;

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
    UnitInfo currentUnit;

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
        int i = 9;
        for (; --i >= 0;)
            microInfo[i] = new MicroInfo(dirs[i]);

        i = units.length;
        for (; --i >= 0;) {
            if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                break;
            currentUnit = units[i];
            if (currentUnit.getType() == UnitType.BATTER) {
                currentActionRadius = RANGE_BATTER;
                currentExtendedActionRadius = RANGE_EXTENDED_BATTER;
            } else {
                currentActionRadius = 0;
                currentExtendedActionRadius = 0;
            }

            currentLoc = currentUnit.getLocation();

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

        units = robot.allies;
        i = units.length;
        for (; --i >= 0;) {
            if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                break;
            currentUnit = units[i];
            if (currentUnit.getType() == UnitType.BATTER) {
                currentActionRadius = RANGE_BATTER;
                currentExtendedActionRadius = RANGE_EXTENDED_BATTER;
            } else {
                currentActionRadius = 0;
                currentExtendedActionRadius = 0;
            }

            currentLoc = currentUnit.getLocation();

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
        i = 8;
        for (; --i >= 0;) {
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

            if (bestMicro.enemyTarget != null) {
                Direction dir = uc.getLocation().directionTo(bestMicro.enemyTarget);
                if (uc.canBat(dir, 3)) {
                    uc.bat(dir, 3);
                }
            }

            if (bestMicro.allyTarget != null) {
                Direction dir = uc.getLocation().directionTo(bestMicro.allyTarget);
                if (uc.canBat(dir, bestMicro.canBoostAlly)) {
                    uc.schedule(bestMicro.allyId);
                    uc.bat(dir, bestMicro.canBoostAlly);
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

        int canHitEnemy = 0;
        int battersAttackRange = 0;
        int battersVisionRange = 0;
        int possibleEnemybatters = 0;
        int minDistToAlly = INF;
        Location enemyTarget = null;
        Location allyTarget = null;
        int canBoostAlly = 0;
        int allyId = 0;
        boolean canMove = true;

        public MicroInfo(Direction dir) {
            this.dir = dir;
            this.location = uc.getLocation().add(dir);
            if (dir != Direction.ZERO && !uc.canMove(dir))
                canMove = false;
            // Don't micro onto bases or stadiums
            if (uc.canSenseLocation(location)) {
                MapObject mapObj = uc.senseObjectAtLocation(location, false);
                if (mapObj == MapObject.BASE || mapObj == MapObject.STADIUM)
                    canMove = false;
            }
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
                canHitEnemy = 1;
                enemyTarget = currentLoc;
            }
        }

        void updateAlly() {
            if (!canMove)
                return;
            int dist = currentLoc.distanceSquared(location);
            if (dist < minDistToAlly)
                minDistToAlly = dist;

            // If the ally is adjacent to this location and we can schedule it,
            // check how far we can bat it without hitting something.
            if (dist <= 2 && uc.canSchedule(currentUnit.getID()) && uc.canAct() &&
                    currentUnit.getType() == UnitType.BATTER) {
                int allyBoost = 0;
                Direction allyDir = location.directionTo(currentLoc);
                Location battedAllyLoc = currentLoc;
                MapObject mapObj;
                UnitInfo unitInfo;
                while (allyBoost < 3) {
                    battedAllyLoc = battedAllyLoc.add(allyDir);
                    if (!uc.canSenseLocation(battedAllyLoc))
                        break;
                    mapObj = uc.senseObjectAtLocation(battedAllyLoc, true);
                    if (mapObj == MapObject.WATER || mapObj == MapObject.BALL)
                        break;
                    unitInfo = uc.senseUnitAtLocation(battedAllyLoc);
                    if (unitInfo != null) {
                        break;
                    }
                    allyBoost++;
                }
                if (allyBoost > canBoostAlly) {
                    canBoostAlly = allyBoost;
                    allyTarget = battedAllyLoc;
                    allyId = currentUnit.getID();
                }
            }
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

            if (battersAttackRange - canHitEnemy < M.battersAttackRange - M.canHitEnemy)
                return true;
            if (battersAttackRange - canHitEnemy > M.battersAttackRange - M.canHitEnemy)
                return false;

            // if (battersVisionRange - canHitEnemy < M.battersVisionRange - M.canHitEnemy)
            // return true;
            // if (battersVisionRange - canHitEnemy > M.battersVisionRange - M.canHitEnemy)
            // return false;

            if (canHitEnemy > M.canHitEnemy)
                return true;
            if (canHitEnemy < M.canHitEnemy)
                return false;

            if (possibleEnemybatters < M.possibleEnemybatters)
                return true;
            if (possibleEnemybatters > M.possibleEnemybatters)
                return false;

            if (canBoostAlly > M.canBoostAlly)
                return true;
            if (canBoostAlly < M.canBoostAlly)
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
