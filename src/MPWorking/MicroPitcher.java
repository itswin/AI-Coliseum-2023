package MPWorking;

import aic2023.user.*;

public class MicroPitcher {

    final int INF = 1000000;
    float ACTION_RANGE;

    final float RANGE_EXTENDED_BATTER = 8;
    final float RANGE_BATTER = 2;

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

        canAttack = uc.getInfo().isCarryingBall() && uc.canAct();

        MicroInfo[] microInfo = new MicroInfo[9];
        int i = 9;
        for (; --i >= 0;)
            microInfo[i] = new MicroInfo(dirs[i]);

        currentActionRadius = RANGE_BATTER;
        currentExtendedActionRadius = RANGE_EXTENDED_BATTER;
        if (!canAttack)
            currentActionRadius = RANGE_EXTENDED_BATTER;

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
            if (currentUnit.getType() != UnitType.BATTER)
                continue;
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
        if (canAttack) {
            i = 8;
            for (; --i >= 0;) {
                if (microInfo[i].isBetter(bestMicro)) {
                    bestMicro = microInfo[i];
                }
            }
        } else {
            i = 8;
            for (; --i >= 0;) {
                if (microInfo[i].isBetterForFleeing(bestMicro)) {
                    bestMicro = microInfo[i];
                }
            }
        }

        return apply(bestMicro);
    }

    boolean apply(MicroInfo bestMicro) {
        boolean didMicro = false;
        if (bestMicro.dir != Direction.ZERO) {
            if (uc.canMove(bestMicro.dir)) {
                uc.move(bestMicro.dir);
                didMicro = true;
            }
        } else {
            didMicro = true;
        }

        if (bestMicro.allyAssistID != -1) {
            if (uc.canSchedule(bestMicro.allyAssistID) && uc.canMoveBall(bestMicro.allyAssistDir)) {
                uc.schedule(bestMicro.allyAssistID);
                uc.moveBall(bestMicro.allyAssistDir);
                didMicro = true;
            }
        } else if (bestMicro.allyScheduleDamageScore > 0) {
            robot.comms.scheduleId(uc.getInfo().getID());
        }

        return didMicro;
    }

    class MicroInfo {
        Direction dir;
        Location location;
        int minDistanceToEnemy = INF;

        int battersTargeting = 0;
        int possibleEnemyBatters = 0;
        boolean canMove = true;

        int minDistToAlly = INF;
        // Direction.ordinal() corresponds to the location a batter
        // needs to be in to hit the ball into an enemy
        Location[] ballPlacementAttackLocs = new Location[8];
        float[] ballAttackScores = new float[8];
        float maxBallAttackScore = 0;
        int allyAssistID = -1;
        Direction allyAssistDir = null;
        int allyScheduleDamageScore = 0;

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
            if (dist <= currentExtendedActionRadius)
                possibleEnemyBatters++;

            if (currentUnit.getType() == UnitType.CATCHER)
                return;

            // Only check the cardinal direction which is most prominent.
            // Checking more is too bytecode intensive.

            // We assume that both ally and enemy batter micros will seek to
            // minimize the distance to the opponent batter while remaining
            // outside of our attack radius. This places them in cardinal directions.
            int dx = currentLoc.x - location.x;
            int dy = currentLoc.y - location.y;
            int absDx = Math.abs(dx);
            int absDy = Math.abs(dy);

            if (absDx == absDy)
                return;

            // If this happens, we cannot bat the ball in the correct direction,
            // as the pitcher will be in the batting location.
            if (absDx == 0 || absDy == 0)
                return;

            Direction dir;
            if (absDx > absDy) {
                if (dx > 0) {
                    dir = Direction.WEST;
                } else {
                    dir = Direction.EAST;
                }
            } else {
                if (dy > 0) {
                    dir = Direction.SOUTH;
                } else {
                    dir = Direction.NORTH;
                }
            }

            Location potentialBall = currentLoc;
            UnitInfo unitInfo;
            MapObject mapObj;
            boolean lookedAhead = false;
            Location nextLocation;
            boolean canPlace = false;
            int ballPlacementIndex;
            // Just assign some positive cost to HQs.
            float damageScore = currentUnit.getType() == UnitType.HQ ? 25
                    : currentUnit.getType().getStat(UnitStat.REP_COST);
            for (int i = 0; i < 3; i++) {
                if (lookedAhead) {
                    lookedAhead = false;
                } else {
                    potentialBall = potentialBall.add(dir);
                    if (!uc.canSenseLocation(potentialBall))
                        break;
                    unitInfo = uc.senseUnitAtLocation(potentialBall);
                    if (unitInfo != null) {
                        // You can hit through other units
                        if (unitInfo.getType() == UnitType.CATCHER || unitInfo.getType() == UnitType.HQ)
                            break;
                        if (unitInfo.getTeam() == robot.team) {
                            damageScore -= unitInfo.getType().getStat(UnitStat.REP_COST);
                        } else {
                            damageScore += unitInfo.getType().getStat(UnitStat.REP_COST);
                        }
                    }
                    mapObj = uc.senseObjectAtLocation(potentialBall, true);
                    if (mapObj == MapObject.WATER || mapObj == MapObject.BALL)
                        break;
                }

                if (potentialBall.distanceSquared(location) <= ACTION_RANGE) {
                    // Check the next location to make sure that a batter can be there
                    nextLocation = potentialBall.add(dir);
                    canPlace = true;
                    if (!uc.canSenseLocation(nextLocation))
                        break;
                    unitInfo = uc.senseUnitAtLocation(nextLocation);
                    if (unitInfo != null) {
                        if (unitInfo.getType() == UnitType.CATCHER || unitInfo.getType() == UnitType.HQ)
                            break;
                        if (unitInfo.getTeam() == robot.team) {
                            damageScore -= unitInfo.getType().getStat(UnitStat.REP_COST);
                        } else {
                            damageScore += unitInfo.getType().getStat(UnitStat.REP_COST);
                        }
                        canPlace = false;
                    }
                    mapObj = uc.senseObjectAtLocation(nextLocation, true);
                    if (mapObj == MapObject.WATER || mapObj == MapObject.BALL)
                        break;

                    if (canPlace) {
                        ballPlacementIndex = location.directionTo(potentialBall).ordinal();
                        if (damageScore > ballAttackScores[ballPlacementIndex]) {
                            ballAttackScores[ballPlacementIndex] = damageScore;
                            ballPlacementAttackLocs[ballPlacementIndex] = nextLocation;
                        }
                    }

                    lookedAhead = true;
                    potentialBall = nextLocation;
                }
            }
        }

        void updateAlly() {
            if (!canMove)
                return;
            int dist = currentLoc.distanceSquared(location);
            // Unit type is always batter
            if (dist < minDistToAlly)
                minDistToAlly = dist;

            // Loop through all valid ball placements.
            // If the current unit is adjacent to the attack location,
            // it can hit it.
            Location attackLoc;
            int distToAttackLoc;
            for (int i = 0; i < 8; i++) {
                if (ballPlacementAttackLocs[i] == null)
                    continue;
                attackLoc = ballPlacementAttackLocs[i];
                distToAttackLoc = currentLoc.distanceSquared(attackLoc);
                if (distToAttackLoc <= 2) {
                    if (uc.canSchedule(currentUnit.getID())) {
                        if (distToAttackLoc == 0 || currentUnit.getCurrentMovementCooldown() < 2) {
                            if (ballAttackScores[i] > maxBallAttackScore) {
                                allyAssistID = currentUnit.getID();
                                allyAssistDir = dirs[i];
                                maxBallAttackScore = ballAttackScores[i];
                            }
                        }
                    } else {
                        allyScheduleDamageScore = 1;
                    }
                    return;
                }
            }
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

            if (battersTargeting < M.battersTargeting)
                return true;
            if (M.battersTargeting < battersTargeting)
                return false;

            if (maxBallAttackScore > M.maxBallAttackScore)
                return true;
            if (M.maxBallAttackScore > maxBallAttackScore)
                return false;

            if (possibleEnemyBatters < M.possibleEnemyBatters)
                return true;
            if (possibleEnemyBatters > M.possibleEnemyBatters)
                return false;

            if (allyScheduleDamageScore > M.allyScheduleDamageScore)
                return true;
            if (M.allyScheduleDamageScore > allyScheduleDamageScore)
                return false;

            if (minDistToAlly < M.minDistToAlly)
                return true;
            if (M.minDistToAlly < minDistToAlly)
                return false;

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

            if (possibleEnemyBatters < M.possibleEnemyBatters)
                return true;
            if (possibleEnemyBatters > M.possibleEnemyBatters)
                return false;

            return minDistanceToEnemy > M.minDistanceToEnemy;
        }
    }
}
