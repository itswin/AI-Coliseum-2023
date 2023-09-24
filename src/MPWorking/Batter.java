package MPWorking;

import java.util.function.Predicate;

import aic2023.user.*;

public class Batter extends Robot {
    public int commsBaseIndex;
    public int commsStadiumIndex;

    final class TargetTypeEnum {
        public final int BASE = 0;
        public final int STADIUM = 1;
        public final int EXPLORE = 2;
    }

    public TargetTypeEnum TargetType = new TargetTypeEnum();

    public int targetType;
    public Location target;
    public boolean justStartedExploring;

    public Batter(UnitController u) {
        super(u);

        targetType = TargetType.BASE;
        commsBaseIndex = 0;
        commsStadiumIndex = 0;
        targetType = TargetType.BASE;
        target = null;
        justStartedExploring = false;
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementBatters();

        if (shouldLoadNextTarget()) {
            loadNextTarget();
        }
    }

    public void takeTurn() {
        super.takeTurn();

        // None of our batters around the location
        Predicate<Location> availablePred = (loc) -> {
            Location[] adjLocs = util.getAdjLocs(loc);
            for (Location adjLoc : adjLocs) {
                UnitInfo unit = uc.senseUnitAtLocation(adjLoc);
                if (unit != null &&
                        unit.getTeam() == uc.getTeam() &&
                        unit.getType() == UnitType.BATTER &&
                        unit.getID() != uc.getInfo().getID()) {
                    return false;
                }
            }
            return true;
        };
        Location visibleTarget = null;
        boolean isExploring = false;
        if ((visibleTarget = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(visibleTarget);
        } else if ((visibleTarget = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(visibleTarget);
        }

        if (visibleTarget != null) {
            target = visibleTarget;
        }

        // If we're targeting a base/stadium and not all of the squares around
        // it are passable, then don't move if we're adjacent.
        if (isExploring) {
            nav.move(target);
        } else {
            boolean shouldNav = true;
            if (uc.getLocation().distanceSquared(target) <= 2) {
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

            if (shouldNav) {
                nav.move(target);
            }
        }

        // Bat away any adjacent enemies
        UnitInfo[] enemies = uc.senseUnits(ACTION_RANGE, uc.getTeam().getOpponent());
        if (enemies.length > 0) {
            Direction dir = uc.getLocation().directionTo(enemies[0].getLocation());
            for (int strength = GameConstants.MAX_STRENGTH + 1; --strength >= 0;) {
                if (uc.canBat(dir, strength)) {
                    uc.bat(dir, strength);
                    debug.println("Batting: " + dir + " " + strength);
                    break;
                }
            }
        }
    }

    public void rotateTargetType() {
        if (targetType == TargetType.BASE) {
            targetType = TargetType.STADIUM;
        } else if (targetType == TargetType.STADIUM) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            targetType = TargetType.BASE;
        }
    }

    public boolean shouldLoadNextTarget() {
        // Always load next target if exploring
        return target == null ||
                uc.getLocation().distanceSquared(target) <= VISION_RANGE ||
                targetType == TargetType.EXPLORE;
    }

    public void loadNextTarget() {
        if (targetType == TargetType.BASE) {
            if (commsBaseIndex >= comms.BASE_SLOTS ||
                    (target = comms.readBase(commsBaseIndex++)).x == -1) {
                commsBaseIndex = 0;
                rotateTargetType();
                loadNextTarget();
            }
        } else if (targetType == TargetType.STADIUM) {
            if (commsStadiumIndex >= comms.STADIUM_SLOTS ||
                    (target = comms.readStadium(commsStadiumIndex++)).x == -1) {
                rotateTargetType();
                loadNextTarget();
                commsStadiumIndex = 0;
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
                } else {
                    target = newTarget;
                }
            }
        }
    }
}
