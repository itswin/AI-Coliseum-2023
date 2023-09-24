package MPWorking;

import aic2023.user.*;

public class Hq extends Robot {
    public int nextFlag;

    public Direction[] exploreDirections;
    public int exploreDirIndex;

    public Hq(UnitController u) {
        super(u);
        comms.init();

        nextFlag = 0;
        exploreDirections = explore.getOptimalExploreOrder();
        exploreDirIndex = 0;
    }

    public void takeTurn() {
        super.takeTurn();

        int randomNumberDir = util.randomInt(8);
        Direction dir = Direction.values()[randomNumberDir];

        /* If this unit is a HQ, try to recruit a pitcher following direction dir */
        if (uc.canRecruitUnit(UnitType.PITCHER, dir)) {
            uc.recruitUnit(UnitType.PITCHER, dir);
            nextFlag = util.dirToFlag(exploreDirections[exploreDirIndex++ % exploreDirections.length]);
        }

        comms.writeHqFlag(nextFlag);
        nextFlag = 0;
    }
}
