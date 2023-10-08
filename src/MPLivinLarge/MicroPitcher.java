package MPLivinLarge;

import aic2023.user.*;

public class MicroPitcher {

    final int INF = 1000000;
    float ACTION_RANGE;

    final int RANGE_EXTENDED_BALL_BATTER = 16;
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

    boolean alwaysInRange;
    double currentActionRadius;
    double currentExtendedActionRadius;
    boolean canAttack;
    Location currentLoc;
    UnitInfo currentUnit;
    boolean isArmedEnemyPitcher;
    int numBatters;

    boolean doMicro() {
        if (!uc.canMove())
            return false;
        UnitInfo[] units = robot.enemies;
        if (units.length == 0)
            return false;

        canAttack = uc.getInfo().isCarryingBall() && uc.canAct();
        isArmedEnemyPitcher = false;

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
                numBatters++;
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

        alwaysInRange = !canAttack || numBatters >= 2 || (isArmedEnemyPitcher && numBatters > 0);

        MicroInfo bestMicro = microInfo[8];
        if (canAttack) {
            // If no movement is the best damage, attack first.
            boolean isZeroBestAttack = true;
            i = 8;
            if (canAttack) {
                for (; --i >= 0;) {
                    if (microInfo[i].isBetterAttack(bestMicro)) {
                        isZeroBestAttack = false;
                        break;
                    }
                }

                if (isZeroBestAttack) {
                    applyAttack(bestMicro);
                    // Ignore the zero direction's attack scores
                    bestMicro.maxBallAttackScore = 0;
                    bestMicro.allyScheduleDamageScore = 0;
                    bestMicro.allyAssistID = -1;
                }
            }

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
        if (bestMicro.dir != Direction.ZERO) {
            if (uc.canMove(bestMicro.dir)) {
                uc.move(bestMicro.dir);
                applyAttack(bestMicro);
                return true;
            }
        } else {
            applyAttack(bestMicro);
            return true;
        }

        return false;
    }

    boolean applyAttack(MicroInfo bestMicro) {
        if (bestMicro.allyAssistID != -1) {
            if (uc.canSchedule(bestMicro.allyAssistID) && uc.canMoveBall(bestMicro.allyAssistDir)) {
                uc.schedule(bestMicro.allyAssistID);
                uc.moveBall(bestMicro.allyAssistDir);
                return true;
            }
        } else if (bestMicro.allyScheduleDamageScore > 0) {
            robot.comms.scheduleId(robot.ID);
        }
        return false;
    }

    class MicroInfo {
        Direction dir;
        Location location;
        int minDistanceToEnemy = INF;

        int battersTargeting = 0;
        int possibleEnemyBatters = 0;
        int possibleEnemyAssists = 0;
        boolean isSupported = false;
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
            if (!isArmedEnemyPitcher && currentUnit.isCarryingBall())
                isArmedEnemyPitcher = true;
            if (dist <= RANGE_EXTENDED_BALL_BATTER && currentUnit.getType() == UnitType.BATTER)
                possibleEnemyAssists++;

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
            boolean subtractAfter = false;
            int ballPlacementIndex;
            // Just assign some positive cost to HQs.
            float damageScore = currentUnit.getType() == UnitType.HQ ? 25
                    : currentUnit.getType().getStat(UnitStat.REP_COST);
            for (int i = 0; i < 3; i++) {
                if (lookedAhead) {
                    lookedAhead = false;
                } else {
                    canPlace = true;
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
                        canPlace = false;
                    }
                    mapObj = uc.senseObjectAtLocation(potentialBall, true);
                    if (mapObj == MapObject.WATER || mapObj == MapObject.BALL)
                        break;
                }

                if (!canPlace)
                    continue;

                if (potentialBall.distanceSquared(location) <= ACTION_RANGE) {
                    // Check the next location to make sure that a batter can be there
                    nextLocation = potentialBall.add(dir);
                    if (!uc.canSenseLocation(nextLocation))
                        break;
                    unitInfo = uc.senseUnitAtLocation(nextLocation);
                    if (unitInfo != null) {
                        if (unitInfo.getType() == UnitType.CATCHER || unitInfo.getType() == UnitType.HQ)
                            break;
                        if (unitInfo.getTeam() == robot.team) {
                            subtractAfter = true;
                            if (unitInfo.getType() != UnitType.BATTER) {
                                canPlace = false;
                            }
                        } else {
                            damageScore += unitInfo.getType().getStat(UnitStat.REP_COST);
                            canPlace = false;
                        }
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

                    if (subtractAfter) {
                        damageScore -= unitInfo.getType().getStat(UnitStat.REP_COST);
                        subtractAfter = false;
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
            if (currentUnit.getType() == UnitType.BATTER || currentUnit.isCarryingBall())
                isSupported = true;

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

        boolean inRange() {
            if (alwaysInRange)
                return true;
            return minDistanceToEnemy <= ACTION_RANGE;
        }

        // equal => true
        boolean isBetter(MicroInfo M) {
            if (canMove && !M.canMove)
                return true;
            if (!canMove && M.canMove)
                return false;

            // If we can't guarantee a kill, check possible enemy kills on us first.
            if (maxBallAttackScore < 35 && M.maxBallAttackScore < 35) {
                if (inRange() && !isSupported) {
                    if (possibleEnemyAssists < M.possibleEnemyAssists)
                        return true;
                    if (possibleEnemyAssists > M.possibleEnemyAssists)
                        return false;
                }
            }

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

            if (inRange() && !isSupported) {
                if (possibleEnemyAssists < M.possibleEnemyAssists)
                    return true;
                if (possibleEnemyAssists > M.possibleEnemyAssists)
                    return false;
            }

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

            if (inRange())
                return minDistanceToEnemy >= M.minDistanceToEnemy;
            else
                return minDistanceToEnemy <= M.minDistanceToEnemy;
        }

        boolean isBetterForFleeing(MicroInfo M) {
            if (canMove && !M.canMove)
                return true;
            if (!canMove && M.canMove)
                return false;

            if (battersTargeting < M.battersTargeting)
                return true;
            if (M.battersTargeting < battersTargeting)
                return false;

            if (possibleEnemyBatters < M.possibleEnemyBatters)
                return true;
            if (possibleEnemyBatters > M.possibleEnemyBatters)
                return false;

            if (inRange() && !isSupported) {
                if (possibleEnemyAssists < M.possibleEnemyAssists)
                    return true;
                if (possibleEnemyAssists > M.possibleEnemyAssists)
                    return false;
            }

            return minDistanceToEnemy > M.minDistanceToEnemy;
        }

        boolean isBetterAttack(MicroInfo M) {
            if (maxBallAttackScore > M.maxBallAttackScore)
                return true;
            if (M.maxBallAttackScore > maxBallAttackScore)
                return false;

            return allyScheduleDamageScore > M.allyScheduleDamageScore;
        }
    }
}
