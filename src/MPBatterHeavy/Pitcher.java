package MPBatterHeavy;

import java.util.function.Predicate;

import aic2023.user.*;

public class Pitcher extends Robot {

    public Pitcher(UnitController u) {
        super(u);
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementPitchers();
    }

    // Pitchers don't rotate through enemy HQs
    @Override
    public void rotateTargetType() {
        if (targetType == TargetType.BASE) {
            targetType = TargetType.STADIUM;
            commsStadiumIndex = 0;
        } else if (targetType == TargetType.STADIUM) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            targetType = TargetType.BASE;
            commsBaseIndex = 0;
        }
    }

    public void takeTurn() {
        super.takeTurn();

        Predicate<Location> availablePred = (loc) -> {
            UnitInfo unit = uc.senseUnitAtLocation(loc);
            return unit == null ||
                    unit.getTeam() != uc.getTeam() ||
                    uc.getType() != UnitType.PITCHER ||
                    unit.getID() == uc.getInfo().getID();
        };
        Location visibleTarget = null;
        if ((visibleTarget = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(visibleTarget);
        } else if ((visibleTarget = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(visibleTarget);
        }

        if (visibleTarget != null) {
            target = visibleTarget;
        }

        nav.move(target);
    }
}
