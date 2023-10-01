package MPWorking;

import aic2023.user.*;

public class MicroBatter {

    final int INF = 1000000;
    boolean alwaysInRange = false;
    float ACTION_RANGE;
    float VISION_RANGE;

    final int RANGE_EXTENDED_BATTER = 8;
    final int RANGE_BATTER = 2;

    final int RANGE_HQ = 13;
    final int RANGE_EXTENDED_HQ = 18;

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
    boolean canGlobalMove;
    Location currentLoc;
    UnitInfo currentUnit;

    boolean doMicro() {
        UnitInfo[] units = robot.enemies;
        if (units.length == 0)
            return false;
        canAttack = uc.canAct();
        canGlobalMove = uc.canMove();

        alwaysInRange = false;
        if (!canAttack)
            alwaysInRange = true;

        MicroInfo[] microInfo = new MicroInfo[9];
        int i = 9;
        for (; --i >= 0;)
            microInfo[i] = new MicroInfo(dirs[i]);

        boolean isThreatened = false;
        i = units.length;
        for (; --i >= 0;) {
            if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                break;
            currentUnit = units[i];
            if (currentUnit.getType() == UnitType.BATTER) {
                currentActionRadius = RANGE_BATTER;
                currentExtendedActionRadius = RANGE_EXTENDED_BATTER;
            } else if (currentUnit.getType() == UnitType.HQ) {
                currentActionRadius = RANGE_HQ;
                currentExtendedActionRadius = RANGE_EXTENDED_HQ;
            } else {
                currentActionRadius = 0;
                currentExtendedActionRadius = 0;
            }

            currentLoc = currentUnit.getLocation();

            // if (!robot.util.seesObstacleInWay(currentLoc))
            // isThreatened = true;
            if (!isThreatened && robot.nav.Bfs20.existsPathTo(currentLoc))
                isThreatened = true;

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

        if (!isThreatened)
            return false;

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

        // If you can't move, we have to choose the zero micro.
        if (!canGlobalMove) {
            // If the zero micro has an attack, apply it.
            if (applyAttack(microInfo[8])) {
                return true;
            }

            checkAllyScheduleDamageScore(microInfo);
            return true;
        }

        // If no movement is the best damage, attack first.
        boolean isZeroBestAttack = true;
        i = 8;
        MicroInfo bestMicro = microInfo[8];
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
                bestMicro.enemyDamageScore = 0;
                bestMicro.allyBatStrength = 0;
            }
        }

        i = 8;
        for (; --i >= 0;) {
            if (microInfo[i].isBetter(bestMicro))
                bestMicro = microInfo[i];
        }

        if (!apply(bestMicro)) {
            checkAllyScheduleDamageScore(microInfo);
        }

        return true;
    }

    // @returns true if we moved
    boolean apply(MicroInfo bestMicro) {
        if (bestMicro.dir == Direction.ZERO) {
            // Attacking should have actually happened on the previous check,
            // but I put it here just in case.
            applyAttack(bestMicro);
            return false;
        }

        if (uc.canMove(bestMicro.dir)) {
            robot.move(bestMicro.dir);
            applyAttack(bestMicro);
            return true;
        }

        return false;
    }

    boolean applyAttack(MicroInfo bestMicro) {
        if (bestMicro.enemyTarget != null) {
            Direction dir = uc.getLocation().directionTo(bestMicro.enemyTarget);
            if (uc.canBat(dir, 3)) {
                uc.bat(dir, 3);
                return true;
            }
        }

        if (bestMicro.allyTarget != null) {
            Direction dir = uc.getLocation().directionTo(bestMicro.allyTarget);
            if (uc.canBat(dir, bestMicro.allyBatStrength)) {
                uc.schedule(bestMicro.allyId);
                uc.bat(dir, bestMicro.allyBatStrength);
                return true;
            }
        }

        return false;
    }

    public void checkAllyScheduleDamageScore(MicroInfo[] microInfo) {
        // Check if we have any positive allyScheduleDamageScores
        float bestAllyScheduleDamageScore = 0;
        int i = 8;
        for (; --i >= 0;) {
            if (microInfo[i].allyScheduleDamageScore > bestAllyScheduleDamageScore) {
                bestAllyScheduleDamageScore = microInfo[i].allyScheduleDamageScore;
            }
        }

        // If so, tell the HQ to schedule ourself.
        if (bestAllyScheduleDamageScore > 0) {
            robot.comms.scheduleId(uc.getInfo().getID());
        }
    }

    class MicroInfo {
        Direction dir;
        Location location;
        int minDistanceToEnemy = INF;

        float enemyDamageScore = 0;
        int enemyBatStrength = 0;
        int battersAttackRange = 0;
        int battersVisionRange = 0;
        int possibleEnemybatters = 0;
        int minDistToAlly = INF;
        Location enemyTarget = null;
        Location allyTarget = null;
        float allyDamageScore = 0;
        int allyBatStrength = 0;
        int allyId = 0;
        float allyScheduleDamageScore = 0;
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
                // Calculate a score based on how much "damage"
                // you deal by batting this enemy.

                float damageScore = 0;
                int batStrength = 0;
                Direction enemyDir = location.directionTo(currentLoc);
                Location battedEnemyLoc = currentLoc;
                MapObject mapObj;
                UnitInfo unitInfo;
                do {
                    battedEnemyLoc = battedEnemyLoc.add(enemyDir);
                    if (!uc.canSenseLocation(battedEnemyLoc)) {
                        // If this is within my vision radius,
                        // then it's out of the map and it's a kill.
                        if (uc.getLocation().distanceSquared(battedEnemyLoc) <= VISION_RANGE) {
                            damageScore = currentUnit.getType().getStat(UnitStat.REP_COST);
                        } else {
                            damageScore = currentUnit.getType().getStat(UnitStat.REP_COST) / 2;
                        }
                        // Otherwise we don't know what happens to this unit,
                        // so only give it half of the reward.
                        // This feasibly could hit one of our own units,
                        // but I'm accepting that risk.
                        batStrength++;
                        break;
                    }
                    mapObj = uc.senseObjectAtLocation(battedEnemyLoc, true);
                    if (mapObj == MapObject.WATER || mapObj == MapObject.BALL) {
                        // Note: Ignoring cost of ball
                        damageScore = currentUnit.getType().getStat(UnitStat.REP_COST);
                        batStrength++;
                        break;
                    }
                    unitInfo = uc.senseUnitAtLocation(battedEnemyLoc);
                    if (unitInfo != null) {
                        UnitType type = unitInfo.getType();
                        if (type == UnitType.HQ) {
                            damageScore = currentUnit.getType().getStat(UnitStat.REP_COST);
                            batStrength++;
                            break;
                        } else if (type == UnitType.CATCHER) {
                            // No damage, unit isn't killed.
                            batStrength++;
                            break;
                        } else {
                            // Batter or pitcher
                            if (unitInfo.getTeam() == robot.team) {
                                damageScore = currentUnit.getType().getStat(UnitStat.REP_COST) -
                                        type.getStat(UnitStat.REP_COST);
                            } else {
                                damageScore = currentUnit.getType().getStat(UnitStat.REP_COST) +
                                        type.getStat(UnitStat.REP_COST);
                            }
                            batStrength++;
                            break;
                        }
                    }
                    batStrength++;
                } while (batStrength < GameConstants.MAX_STRENGTH);

                damageScore += batStrength;
                if (damageScore > enemyDamageScore) {
                    enemyDamageScore = damageScore;
                    enemyBatStrength = batStrength;
                    enemyTarget = currentLoc;
                }
            }
        }

        void updateAlly() {
            if (!canMove)
                return;
            int dist = currentLoc.distanceSquared(location);
            if (dist < minDistToAlly && currentUnit.getType() == UnitType.BATTER)
                minDistToAlly = dist;

            // If the ally is adjacent to this location and we can schedule it,
            // calculate a score based on potential damage dealt to the enemy.
            int currentUnitID = currentUnit.getID();
            if (dist <= 2 &&
                    uc.canAct() &&
                    currentUnit.getType() == UnitType.BATTER) {
                boolean canScheduleUnit = uc.canSchedule(currentUnitID);
                float damageScore;
                int batStrength = 0;
                Direction allyDir = location.directionTo(currentLoc);
                Location battedAllyLoc = currentLoc;
                MapObject mapObj;
                UnitInfo unitInfo;
                do {
                    battedAllyLoc = battedAllyLoc.add(allyDir);
                    if (!uc.canSenseLocation(battedAllyLoc))
                        break;
                    mapObj = uc.senseObjectAtLocation(battedAllyLoc, true);
                    if (mapObj == MapObject.WATER || mapObj == MapObject.BALL)
                        break;
                    unitInfo = uc.senseUnitAtLocation(battedAllyLoc);
                    if (unitInfo != null)
                        break;
                    batStrength++;

                    // Calculate score here. If we broke out before this,
                    // we don't want to use this loops strength.
                    damageScore = batStrength;

                    // Loop through all enemy units. Scoring is kind of
                    // speculative, we give more points to being closer an
                    // enemy because that gives the unit more squares it can
                    // move to adjacent to the enemy when it does its own micro.
                    UnitInfo[] enemyUnits = robot.enemies;
                    UnitInfo currentEnemy;
                    for (int i = enemyUnits.length; --i >= 0;) {
                        if (uc.getEnergyLeft() < MAX_MICRO_BYTECODE_REMAINING)
                            break;
                        currentEnemy = enemyUnits[i];
                        if (currentEnemy.getType() != UnitType.BATTER)
                            continue;
                        // Note: battedAllyLoc should never be where an enemy is now.
                        damageScore += 15.0 - battedAllyLoc.distanceSquared(currentEnemy.getLocation());
                    }

                    if (canScheduleUnit) {
                        // These are units we can hit on this turn
                        // because they move after us in the turn order.
                        if (damageScore > allyDamageScore) {
                            allyBatStrength = batStrength;
                            allyDamageScore = damageScore;
                            allyTarget = currentLoc;
                            allyId = currentUnitID;
                        }
                    } else {
                        // These are units that move before us in the turn order.
                        // Enforce a minimum of batting near one ally or enemy
                        // to consider having the HQ schedule us.
                        if (damageScore > 3 && damageScore > allyScheduleDamageScore) {
                            allyScheduleDamageScore = damageScore;
                        }
                    }
                } while (batStrength < GameConstants.MAX_STRENGTH);
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

            if (enemyDamageScore > M.enemyDamageScore)
                return true;
            if (enemyDamageScore < M.enemyDamageScore)
                return false;

            if (battersAttackRange < M.battersAttackRange)
                return true;
            if (battersAttackRange > M.battersAttackRange)
                return false;

            if (possibleEnemybatters < M.possibleEnemybatters)
                return true;
            if (possibleEnemybatters > M.possibleEnemybatters)
                return false;

            if (allyDamageScore > M.allyDamageScore)
                return true;
            if (allyDamageScore < M.allyDamageScore)
                return false;

            if (minDistToAlly < M.minDistToAlly)
                return true;
            if (minDistToAlly > M.minDistToAlly)
                return false;

            if (inRange())
                return minDistanceToEnemy >= M.minDistanceToEnemy;
            else
                return minDistanceToEnemy <= M.minDistanceToEnemy;
        }

        boolean isBetterAttack(MicroInfo M) {
            if (enemyDamageScore > M.enemyDamageScore)
                return true;
            if (enemyDamageScore < M.enemyDamageScore)
                return false;

            return allyDamageScore > M.allyDamageScore;
        }
    }
}
