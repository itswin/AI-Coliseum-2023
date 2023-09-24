package MPAnyResources;

import java.util.function.Predicate;

import aic2023.user.*;

public class Pitcher extends Robot {

    public Pitcher(UnitController u) {
        super(u);
    }

    public void takeTurn() {
        super.takeTurn();

        Location target;
        Predicate<Location> availablePred = (loc) -> {
            UnitInfo unit = uc.senseUnitAtLocation(loc);
            return unit == null ||
                    unit.getTeam() != uc.getTeam() ||
                    uc.getType() != UnitType.PITCHER ||
                    unit.getID() == uc.getInfo().getID();
        };
        if ((target = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(target);
        } else if ((target = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(target);
        } else {
            target = explore.getExplore3Target();
        }

        nav.move(target);
    }
}
