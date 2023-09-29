package MPWorking;

import aic2023.user.*;

public class Hq extends Robot {
    final class HqStateEnum {
        public final int INIT = 0;
        public final int NORMAL = 1;
        public final int DEFENSE = 2;
    }

    public final int NUM_INIT_BATTERS = 1;
    public final int NUM_INIT_PITCHERS = 1;

    public final float BATTERS_TO_PITCHERS_RATIO = 2;

    public HqStateEnum HqState = new HqStateEnum();
    public int state;

    public int nextFlag;

    public boolean[] canBuildInDir;
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
        loadExploreDirections();
        exploreDirIndex = 0;

        comms.writeHqXCoord(uc.getLocation().x);
        comms.writeHqYCoord(uc.getLocation().y);

        loadResources();
    }

    public void loadExploreDirections() {
        canBuildInDir = new boolean[9];
        Location loc;
        int numBuildableDirs = 0;
        for (Direction dir : Direction.values()) {
            if (dir == Direction.ZERO)
                continue;
            // Check that the location is on the map and is not water.
            loc = uc.getLocation().add(dir);
            canBuildInDir[dir.ordinal()] = uc.canSenseLocation(loc) &&
                    uc.senseObjectAtLocation(loc, false) != MapObject.WATER;
            if (canBuildInDir[dir.ordinal()]) {
                numBuildableDirs++;
            }
        }

        Direction[] optimalExploreOrder = explore.getOptimalExploreOrder();
        exploreDirections = new Direction[numBuildableDirs];
        int idx = 0;
        for (Direction dir : optimalExploreOrder) {
            if (canBuildInDir[dir.ordinal()]) {
                exploreDirections[idx++] = dir;
            }
        }
    }

    public void initTurn() {
        super.initTurn();

        numPitchers = comms.readNumPitchers();
        numBatters = comms.readNumBatters();
        numCatchers = comms.readNumCatchers();
        comms.resetUnitCount();

        decrementHeartbeats();
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
        } else if (state == HqState.NORMAL) {
            if (enemies.length > 0) {
                state = HqState.DEFENSE;
            }
        } else if (state == HqState.DEFENSE) {
            if (enemies.length == 0) {
                state = HqState.NORMAL;
            }
        }
    }

    public void doStateAction() {
        if (state == HqState.INIT) {
            initAction();
        } else if (state == HqState.NORMAL) {
            normalAction();
        } else if (state == HqState.DEFENSE) {
            defendAction();
        }
    }

    public void initAction() {
        UnitType recruitType;
        if (numBatters < NUM_INIT_BATTERS) {
            recruitType = UnitType.BATTER;
        } else {
            recruitType = UnitType.PITCHER;
        }

        Direction dir = getNextBuildDir();
        if (dir != null && uc.canRecruitUnit(recruitType, dir)) {
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

        Direction dir = getNextBuildDir();
        if (dir != null && uc.canRecruitUnit(recruitType, dir)) {
            uc.recruitUnit(recruitType, dir);
            exploreDirIndex++;
            exploreDirIndex %= exploreDirections.length;
            nextFlag = util.dirToFlag(dir);
        }
    }

    public void defendAction() {
        // CloseHQs doesn't build any pitchers without this lol.
        if (numPitchers < 1) {
            UnitType recruitType = UnitType.PITCHER;
            Direction dir = getNextBuildDir();
            if (dir != null && uc.canRecruitUnit(recruitType, dir)) {
                uc.recruitUnit(recruitType, dir);
                exploreDirIndex++;
                exploreDirIndex %= exploreDirections.length;
                nextFlag = util.dirToFlag(dir);
            }
        }

        UnitType recruitType = UnitType.BATTER;

        // Build 2 batters at a time if there are enemies around.
        final int NUM_BATTERS_TO_BUILD = 2;
        if (uc.getReputation() >= UnitType.BATTER.getStat(UnitStat.REP_COST) * NUM_BATTERS_TO_BUILD) {
            for (int i = 0; i < NUM_BATTERS_TO_BUILD; i++) {
                Direction dir = getNextBuildDir();
                if (dir != null && uc.canRecruitUnit(recruitType, dir)) {
                    uc.recruitUnit(recruitType, dir);
                    exploreDirIndex++;
                    exploreDirIndex %= exploreDirections.length;
                    nextFlag = util.dirToFlag(dir);
                }
            }
        }
    }

    // Returns the next direction to build in, or null if there are none
    public Direction getNextBuildDir() {
        Direction dir;
        Location loc = uc.getLocation();
        for (int tries = 0; tries < exploreDirections.length; tries++) {
            dir = exploreDirections[exploreDirIndex];
            if (uc.senseUnitAtLocation(loc.add(dir)) == null &&
                    uc.senseObjectAtLocation(loc, true) != MapObject.BALL) {
                return dir;
            }
            exploreDirIndex++;
            exploreDirIndex %= exploreDirections.length;
        }
        return null;
    }

    public void decrementHeartbeats() {
        for (int i = comms.BASE_SLOTS; --i >= 0;) {
            comms.decrementBasePitcherHeartbeat(i);
            comms.decrementBaseBatterHeartbeat(i);
        }

        for (int i = comms.STADIUM_SLOTS; --i >= 0;) {
            comms.decrementStadiumPitcherHeartbeat(i);
            comms.decrementStadiumBatterHeartbeat(i);
        }
    }

    public void loadResources() {
        Location[] objects = uc.senseObjects(MapObject.BASE, VISION_RANGE);
        for (Location base : objects) {
            comms.logBase(base);
        }
        objects = uc.senseObjects(MapObject.STADIUM, VISION_RANGE);
        for (Location stadium : objects) {
            comms.logStadium(stadium);
        }
    }
}
