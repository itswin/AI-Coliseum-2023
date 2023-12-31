package MPWorking;

import java.util.function.ToDoubleFunction;

import aic2023.user.*;

public class Pitcher extends Robot {
    final int ROUNDS_FOR_ONLY_STADIUM = 250;
    final int ROUNDS_FOR_ONLY_BASES = 1400;

    MicroPitcher microPitcher;

    boolean isTargetingBall;

    public Pitcher(UnitController u) {
        super(u);
        microPitcher = new MicroPitcher(uc, this);
        targetType = TargetType.STADIUM;
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementPitchers();

        if (shouldLoadNextTarget()) {
            loadNextTarget();
        }

        isExploring = false;
        isTargetingBase = false;
        isTargetingStadium = false;
        isTargetingBall = false;
    }

    // Pitchers don't rotate through enemy HQs
    public void rotateTargetType() {
        if (targetType == TargetType.STADIUM) {
            if (uc.getRound() <= ROUNDS_FOR_ONLY_STADIUM) {
                targetType = TargetType.EXPLORE;
                justStartedExploring = true;
            } else {
                targetType = TargetType.BASE;
                baseVisited = new boolean[comms.BASE_SLOTS];
            }
        } else if (targetType == TargetType.BASE) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            if (uc.getRound() >= ROUNDS_FOR_ONLY_BASES) {
                targetType = TargetType.BASE;
                baseVisited = new boolean[comms.BASE_SLOTS];
            } else {
                targetType = TargetType.STADIUM;
                stadiumVisited = new boolean[comms.STADIUM_SLOTS];
            }
        }
    }

    @Override
    public void takeTurn() {
        pickupBall();
        if (microPitcher.doMicro())
            return;

        Location currentLoc = uc.getLocation();
        ToDoubleFunction<Location> pred = (loc) -> {
            UnitInfo unit = uc.senseUnitAtLocation(loc);
            double score = 0;
            if (unit == null) {
                score += 400;
            } else if (unit.getID() == ID) {
                score += 600;
            } else if (unit.getTeam() != uc.getTeam()) {
                score += 200;
            } else {
                score = -200;
            }

            score -= Math.sqrt(hq.distanceSquared(loc)) + Math.sqrt(currentLoc.distanceSquared(loc));
            return score;
        };

        Location currentLocation = uc.getLocation();
        ToDoubleFunction<Location> ballPred = (loc) -> {
            return 100 - currentLocation.distanceSquared(loc);
        };

        Location visibleTarget = null;
        if (!uc.getInfo().isCarryingBall() &&
                (visibleTarget = getBestMapObj(MapObject.BALL, ballPred)) != null) {
            isTargetingBall = true;
        } else if (uc.getRound() < ROUNDS_FOR_ONLY_BASES &&
                (visibleTarget = getBestMapObj(MapObject.STADIUM, pred)) != null) {
            util.logStadiumAndReflection(visibleTarget);
            isTargetingStadium = true;
        } else if (uc.getRound() > ROUNDS_FOR_ONLY_STADIUM &&
                (visibleTarget = getBestMapObj(MapObject.BASE, pred)) != null) {
            util.logBaseAndReflection(visibleTarget);
            isTargetingBase = true;
        }

        if (visibleTarget != null) {
            setTarget(visibleTarget);
        }

        // If you're not standing on a resource, check kill switch
        // MapObject resource = uc.senseObjectAtLocation(currentLoc, false);
        // if (resource != MapObject.STADIUM && resource != MapObject.BASE) {
        // checkKillSwitch();
        // }

        nav.move(target);

        if (isTargetingBall) {
            pickupBall();
        } else {
            heartbeatTarget(isTargetingBase, isTargetingStadium);
        }
    }

    public void pickupBall() {
        if (uc.getInfo().isCarryingBall())
            return;

        Location[] mapObjs = uc.senseObjects(MapObject.BALL, 2);
        if (mapObjs.length > 0) {
            Direction dir = uc.getLocation().directionTo(mapObjs[0]);
            if (uc.canMoveBall(dir)) {
                uc.moveBall(dir);
            }
        }
    }

    public boolean shouldAttack() {
        return false;
    }

    // TODO: If holding a baseball and you can place it in front of a batter
    // such that they can hit an enemy, do so.
    public void attack() {
    }

    public void heartbeatTarget(boolean isTargetingBase, boolean isTargetingStadium) {
        // Only heartbeat if we're on the target
        if (!uc.getLocation().equals(target))
            return;

        if (isTargetingBase) {
            int baseSlot = util.getBaseSlot(target);
            if (baseSlot != -1) {
                comms.sendBasePitcherHeartbeat(baseSlot);
            }
        }

        if (isTargetingStadium) {
            int stadiumSlot = util.getStadiumSlot(target);
            if (stadiumSlot != -1) {
                comms.sendStadiumPitcherHeartbeat(stadiumSlot);
            }
        }
    }

    public void loadNextTarget() {
        if (targetType == TargetType.BASE) {
            if (!loadNextBase()) {
                rotateTargetType();
                loadNextTarget();
            }
        } else if (targetType == TargetType.STADIUM) {
            if (!loadNextStadium()) {
                rotateTargetType();
                loadNextTarget();
            }
        } else if (targetType == TargetType.EXPLORE) {
            if (justStartedExploring) {
                setTarget(explore.getExplore3Target());
                justStartedExploring = false;
            } else {
                Location newTarget = explore.getExplore3Target();
                // If we reset the explore target, rotate target types
                if (explore.changedExplore3Target) {
                    rotateTargetType();
                    loadNextTarget();
                } else {
                    setTarget(newTarget);
                }
            }
        }
    }

    @Override
    public boolean isBaseOccupied(int slot) {
        return !comms.isBasePitcherHeartbeatDead(slot);
    }

    @Override
    public boolean isStadiumOccupied(int slot) {
        return !comms.isStadiumPitcherHeartbeatDead(slot);
    }
}
