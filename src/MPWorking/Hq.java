package MPWorking;

import aic2023.user.*;

public class Hq extends Robot {
    final class HqStateEnum {
        public final int INIT = 0;
        public final int NORMAL = 1;
    }

    public final int NUM_INIT_BATTERS = 1;
    public final int NUM_INIT_PITCHERS = 1;

    public final float BATTERS_TO_PITCHERS_RATIO = 2;

    public HqStateEnum HqState = new HqStateEnum();
    public int state;

    public int nextFlag;

    public Direction[] exploreDirections;
    public int exploreDirIndex;

    public int numPitchers;
    public int numBatters;
    public int numCatchers;

    public Hq(UnitController u) {
        super(u);
        comms.init();

        state = HqState.INIT;
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

    @Override
    public void takeTurn() {
        switchState();
        doStateAction();

        comms.writeHqFlag(nextFlag);
        nextFlag = 0;
    }

    public void switchState() {
        if (state == HqState.INIT) {
            if (numBatters >= NUM_INIT_BATTERS && numPitchers >= NUM_INIT_PITCHERS) {
                state = HqState.NORMAL;
            }
        }
    }

    public void doStateAction() {
        if (state == HqState.INIT) {
            initAction();
        } else if (state == HqState.NORMAL) {
            normalAction();
        }
    }

    public void initAction() {
        UnitType recruitType;
        if (numBatters < NUM_INIT_BATTERS) {
            recruitType = UnitType.BATTER;
        } else {
            recruitType = UnitType.PITCHER;
        }

        Direction dir = exploreDirections[exploreDirIndex];
        if (uc.canRecruitUnit(recruitType, dir)) {
            uc.recruitUnit(recruitType, dir);
            exploreDirIndex++;
            exploreDirIndex %= exploreDirections.length;
            nextFlag = util.dirToFlag(dir);
        }
    }

    public void normalAction() {
        UnitType recruitType;
        if (numPitchers * BATTERS_TO_PITCHERS_RATIO < numBatters) {
            recruitType = UnitType.PITCHER;
        } else {
            recruitType = UnitType.BATTER;
        }

        Direction dir = exploreDirections[exploreDirIndex];
        if (uc.canRecruitUnit(recruitType, dir)) {
            uc.recruitUnit(recruitType, dir);
            exploreDirIndex++;
            exploreDirIndex %= exploreDirections.length;
            nextFlag = util.dirToFlag(dir);
        }
    }
}
