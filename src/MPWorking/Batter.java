package MPWorking;

import java.util.function.Predicate;

import aic2023.user.*;

public class Batter extends Robot {
    public Batter(UnitController u) {
        super(u);
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementBatters();
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
        Location target;
        boolean isExploring = false;
        if ((target = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(target);
        } else if ((target = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(target);
        } else {
            target = explore.getExplore3Target();
            isExploring = true;
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
}
