package MPAnyResources;

import java.util.function.Predicate;

import aic2023.user.*;

public class Batter extends Robot {
    public Batter(UnitController u) {
        super(u);
    }

    public void takeTurn() {
        super.takeTurn();

        // None of our batters around the location
        Predicate<Location> availablePred = (loc) -> {
            Location[] adjLocs = util.sensableAdjLocations(loc);
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
        if ((target = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(target);
        } else if ((target = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(target);
        } else {
            target = explore.getExplore3Target();
        }

        nav.move(target);

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
