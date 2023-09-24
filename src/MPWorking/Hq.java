package MPWorking;

import aic2023.user.*;

public class Hq extends Robot {
    public Hq(UnitController u) {
        super(u);
        comms.initMap();
        comms.initSymmetry();
    }

    public void takeTurn() {
        super.takeTurn();

        int randomNumberDir = util.randomInt(8);
        Direction dir = Direction.values()[randomNumberDir];

        /* If this unit is a HQ, try to recruit a pitcher following direction dir */
        if (uc.canRecruitUnit(UnitType.PITCHER, dir))
            uc.recruitUnit(UnitType.PITCHER, dir);
    }
}
