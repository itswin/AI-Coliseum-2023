package MPWorking;

import aic2023.user.*;

public class Pitcher extends Robot {

    public Pitcher(UnitController u) {
        super(u);
    }

    public void takeTurn() {
        super.takeTurn();

        Location target;
        if ((target = getClosestMapObj(MapObject.BASE)) != null) {
            comms.logBase(target);
        } else if ((target = getClosestMapObj(MapObject.STADIUM)) != null) {
            comms.logStadium(target);
        } else {
            target = explore.getExplore3Target();
        }

        nav.move(target);
    }

    public Location getClosestMapObj(MapObject mapObj) {
        return util.getClosestAvailable(uc.senseObjects(mapObj, VISION_RANGE));
    }
}
