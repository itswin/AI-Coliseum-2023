package MPWorking;

import aic2023.user.*;

public class Hq extends Robot {
    public int nextFlag;

    public Direction[] exploreDirections;
    public int exploreDirIndex;

    public int numPitchers;
    public int numBatters;
    public int numCatchers;

    public Hq(UnitController u) {
        super(u);
        comms.init();

        nextFlag = 0;
        exploreDirections = explore.getOptimalExploreOrder();
        exploreDirIndex = 0;
    }

    public void initTurn() {
        super.initTurn();

        numPitchers = comms.readNumPitchers();
        numBatters = comms.readNumBatters();
        numCatchers = comms.readNumCatchers();
        comms.resetUnitCount();
    }

    public void takeTurn() {
        super.takeTurn();

        Direction dir = exploreDirections[exploreDirIndex++ % exploreDirections.length];
        UnitType recruitType;
        if (numPitchers < 2 * numBatters + 5) {
            recruitType = UnitType.PITCHER;
        } else {
            recruitType = UnitType.BATTER;
        }

        if (uc.canRecruitUnit(recruitType, dir)) {
            uc.recruitUnit(recruitType, dir);
            nextFlag = util.dirToFlag(dir);
        }

        comms.writeHqFlag(nextFlag);
        nextFlag = 0;
    }
}
