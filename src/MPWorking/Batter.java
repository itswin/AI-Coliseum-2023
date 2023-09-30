package MPWorking;

import java.util.function.ToDoubleFunction;

import aic2023.user.*;

public class Batter extends Robot {
    final int ROUNDS_FOR_ONLY_STADIUM = 250;

    MicroBatter microBatter;

    public Batter(UnitController u) {
        super(u);
        microBatter = new MicroBatter(uc, this);
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementBatters();

        if (shouldLoadNextTarget()) {
            loadNextTarget();
        }
    }

    @Override
    public void takeTurn() {
        if (!microBatter.doMicro()) {
            moveToTarget();
        }
    }

    public void moveToTarget() {
        // None of our batters around the location
        ToDoubleFunction<Location> pred = (loc) -> {
            Location[] adjLocs = util.getAdjLocs(loc);
            double score = 100;
            for (Location adjLoc : adjLocs) {
                UnitInfo unit = uc.senseUnitAtLocation(adjLoc);
                if (unit != null &&
                        unit.getTeam() == uc.getTeam() &&
                        unit.getType() == UnitType.BATTER &&
                        unit.getID() != uc.getInfo().getID()) {
                    return -1;
                }
            }

            score -= Math.sqrt(hq.distanceSquared(loc));
            return score;
        };
        Location visibleTarget = null;
        boolean isExploring = false;
        boolean isTargetingBase = false;
        boolean isTargetingStadium = false;
        if ((visibleTarget = getBestMapObj(MapObject.STADIUM, pred)) != null) {
            util.logStadiumAndReflection(visibleTarget);
            isTargetingStadium = true;
        } else if (uc.getRound() > ROUNDS_FOR_ONLY_STADIUM &&
                (visibleTarget = getBestMapObj(MapObject.BASE, pred)) != null) {
            comms.logBase(visibleTarget);
            isTargetingBase = true;
        }

        if (visibleTarget != null) {
            target = visibleTarget;
        }

        // Semi-solves race condition of multiple batters walking up to
        // a target at the same time.
        if (uc.canSenseLocation(target)) {
            MapObject mapObj = uc.senseObjectAtLocation(target, false);
            if (mapObj == MapObject.STADIUM) {
                isTargetingStadium = true;
            } else if (mapObj == MapObject.BASE) {
                isTargetingBase = true;
            }
        }

        if (isExploring) {
            nav.move(target);
        } else {
            boolean shouldNav = true;
            boolean greedy = false;
            // If we're targeting a base/stadium and not all of the squares around
            // it are passable, then don't move if we're adjacent.
            if (uc.getLocation().distanceSquared(target) <= 2) {
                // Make the target impassable if it's a base/stadium
                boolean[] imp = new boolean[9];
                Direction dir = uc.getLocation().directionTo(target);
                imp[dir.ordinal()] = true;
                pathfinding.setImpassable(imp);
                greedy = true;

                Location[] adjLocs = util.getAdjLocs(target);
                int idx = adjLocs.length;
                MapObject mapObj;
                Location adjLoc;
                for (; --idx >= 0;) {
                    adjLoc = adjLocs[idx];
                    mapObj = uc.senseObjectAtLocation(adjLoc, false);
                    if (mapObj == MapObject.WATER) {
                        shouldNav = false;
                        break;
                    }
                }
            }

            // If you've ended up on the target due to micro, then move off of it.
            if (uc.getLocation().equals(target)) {
                shouldNav = true;
            }

            if (shouldNav) {
                nav.move(target, greedy);
            }

            heartbeatTarget(isTargetingBase, isTargetingStadium);
        }
    }

    public void heartbeatTarget(boolean isTargetingBase, boolean isTargetingStadium) {
        // Only heartbeat if we're adjacent to the target
        if (uc.getLocation().distanceSquared(target) > 2)
            return;

        if (isTargetingBase) {
            int baseSlot = util.getBaseSlot(target);
            if (baseSlot != -1) {
                comms.sendBaseBatterHeartbeat(baseSlot);
            }
        }

        if (isTargetingStadium) {
            int stadiumSlot = util.getStadiumSlot(target);
            if (stadiumSlot != -1) {
                comms.sendStadiumBatterHeartbeat(stadiumSlot);
            }
        }
    }

    public void rotateTargetType() {
        if (targetType == TargetType.STADIUM) {
            targetType = TargetType.BASE;
            commsBaseIndex = 0;
        } else if (targetType == TargetType.BASE) {
            targetType = TargetType.ENEMY_HQ;
            enemyHqIndex = 0;
        } else if (targetType == TargetType.ENEMY_HQ) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            targetType = TargetType.STADIUM;
            commsStadiumIndex = 0;
        }
    }

    @Override
    public boolean shouldLoadNextTarget() {
        if (super.shouldLoadNextTarget())
            return true;

        // If your slot has become occupied, rotate to the next target.
        if (targetType == TargetType.BASE) {
            return !comms.isBaseBatterHeartbeatDead(commsBaseIndex);
        } else if (targetType == TargetType.STADIUM) {
            return !comms.isStadiumBatterHeartbeatDead(commsStadiumIndex);
        }

        return false;
    }

    public void loadNextTarget() {
        if (targetType == TargetType.BASE) {
            while (commsBaseIndex < comms.BASE_SLOTS &&
                    (target = comms.readBase(commsBaseIndex)).x != -1) {
                if (comms.isBaseBatterHeartbeatDead(commsBaseIndex)) {
                    return;
                }
                commsBaseIndex++;
            }
            rotateTargetType();
            loadNextTarget();
        } else if (targetType == TargetType.STADIUM) {
            while (commsStadiumIndex < comms.STADIUM_SLOTS &&
                    (target = comms.readStadium(commsStadiumIndex)).x != -1) {
                if (comms.isStadiumBatterHeartbeatDead(commsStadiumIndex)) {
                    return;
                }
                commsStadiumIndex++;
            }
            rotateTargetType();
            loadNextTarget();
        } else if (targetType == TargetType.ENEMY_HQ) {
            if (!util.mapBoundsInitialized) {
                rotateTargetType();
                loadNextTarget();
            } else {
                Location[] enemyHqLocations = util.getValidSymmetryLocs(hq);
                if (enemyHqIndex >= enemyHqLocations.length) {
                    rotateTargetType();
                    loadNextTarget();
                } else {
                    target = enemyHqLocations[enemyHqIndex++];
                }
            }
        } else if (targetType == TargetType.EXPLORE) {
            if (justStartedExploring) {
                target = explore.getExplore3Target();
                justStartedExploring = false;
            } else {
                Location newTarget = explore.getExplore3Target();
                // If we reset the explore target, rotate target types
                if (!target.equals(newTarget)) {
                    rotateTargetType();
                    loadNextTarget();
                }
            }
        }
    }
}
