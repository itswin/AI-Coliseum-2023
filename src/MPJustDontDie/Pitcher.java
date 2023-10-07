package MPJustDontDie;

import java.util.function.ToDoubleFunction;

import aic2023.user.*;

public class Pitcher extends Robot {
    final int ROUNDS_FOR_ONLY_STADIUM = 250;
    final int ROUNDS_FOR_ONLY_BASES = 1400;

    MicroPitcher microPitcher;

    public Pitcher(UnitController u) {
        super(u);
        microPitcher = new MicroPitcher(uc, this);
        targetType = TargetType.STADIUM;
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementPitchers();
        didMicro = false;

        if (shouldLoadNextTarget()) {
            loadNextTarget();
        }
    }

    // Pitchers don't rotate through enemy HQs
    public void rotateTargetType() {
        if (targetType == TargetType.STADIUM) {
            if (uc.getRound() <= ROUNDS_FOR_ONLY_STADIUM) {
                targetType = TargetType.EXPLORE;
                justStartedExploring = true;
            } else {
                targetType = TargetType.BASE;
                commsBaseIndex = 0;
            }
        } else if (targetType == TargetType.BASE) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            if (uc.getRound() >= ROUNDS_FOR_ONLY_BASES) {
                targetType = TargetType.BASE;
                commsBaseIndex = 0;
            } else {
                targetType = TargetType.STADIUM;
                commsStadiumIndex = 0;
            }
        }
    }

    @Override
    public void takeTurn() {
        pickupBall();
        if (roundSeenEnemyBatter == uc.getRound()) {
            didMicro = microPitcher.doMicro();
        }

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
        boolean isTargetingBase = false;
        boolean isTargetingStadium = false;
        boolean isTargetingBall = false;
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
            target = visibleTarget;
        }

        // If you're not standing on a resource, check kill switch
        // MapObject resource = uc.senseObjectAtLocation(currentLoc, false);
        // if (resource != MapObject.STADIUM && resource != MapObject.BASE) {
        // checkKillSwitch();
        // }

        if (!didMicro) {
            nav.move(target);
        }

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

    @Override
    public boolean shouldLoadNextTarget() {
        if (super.shouldLoadNextTarget())
            return true;

        // If your slot has become occupied, rotate to the next target.
        if (targetType == TargetType.BASE) {
            return !comms.isBasePitcherHeartbeatDead(commsBaseIndex);
        } else if (targetType == TargetType.STADIUM) {
            return !comms.isStadiumPitcherHeartbeatDead(commsStadiumIndex) ||
                    uc.getRound() >= ROUNDS_FOR_ONLY_BASES;
        }

        return false;
    }

    public void loadNextTarget() {
        if (targetType == TargetType.BASE) {
            while (commsBaseIndex < comms.BASE_SLOTS &&
                    (target = comms.readBase(commsBaseIndex)).x != -1) {
                if (comms.isBasePitcherHeartbeatDead(commsBaseIndex)) {
                    return;
                }
                commsBaseIndex++;
            }
            rotateTargetType();
            loadNextTarget();
        } else if (targetType == TargetType.STADIUM) {
            while (commsStadiumIndex < comms.STADIUM_SLOTS &&
                    (target = comms.readStadium(commsStadiumIndex)).x != -1) {
                if (comms.isStadiumPitcherHeartbeatDead(commsStadiumIndex)) {
                    return;
                }
                commsStadiumIndex++;
            }
            rotateTargetType();
            loadNextTarget();
        } else if (targetType == TargetType.EXPLORE) {
            if (justStartedExploring) {
                target = explore.getExplore3Target();
                justStartedExploring = false;
            } else {
                Location newTarget = explore.getExplore3Target();
                // If we reset the explore target, rotate target types
                if (explore.changedExplore3Target) {
                    rotateTargetType();
                    loadNextTarget();
                } else {
                    target = newTarget;
                }
            }
        }
    }
}
