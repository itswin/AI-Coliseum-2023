package MPExtraOomph;

import java.util.function.Predicate;

import aic2023.user.*;

public class Pitcher extends Robot {
    final int ROUNDS_FOR_ONLY_STADIUM = 250;

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
            targetType = TargetType.STADIUM;
            commsStadiumIndex = 0;
        }
    }

    @Override
    public void takeTurn() {
        if (roundSeenEnemyBatter == uc.getRound()) {
            if (shouldAttack()) {
                didMicro = microPitcher.doMicro(false);
                attack();
            }
            if (microPitcher.flee()) {
                didMicro = true;
            }
        }

        Predicate<Location> availablePred = (loc) -> {
            UnitInfo unit = uc.senseUnitAtLocation(loc);
            return unit == null ||
                    unit.getTeam() != uc.getTeam() ||
                    uc.getType() != UnitType.PITCHER ||
                    unit.getID() == uc.getInfo().getID();
        };
        Location visibleTarget = null;
        boolean isTargetingBase = false;
        boolean isTargetingStadium = false;
        if ((visibleTarget = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            util.logStadiumAndReflection(visibleTarget);
            isTargetingStadium = true;
        } else if (uc.getRound() > ROUNDS_FOR_ONLY_STADIUM &&
                (visibleTarget = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            util.logBaseAndReflection(visibleTarget);
            isTargetingBase = true;
        }

        if (visibleTarget != null) {
            target = visibleTarget;
        }

        nav.move(target);

        heartbeatTarget(isTargetingBase, isTargetingStadium);
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
            return !comms.isStadiumPitcherHeartbeatDead(commsStadiumIndex);
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
                if (!target.equals(newTarget)) {
                    rotateTargetType();
                    loadNextTarget();
                }
            }
        }
    }
}
