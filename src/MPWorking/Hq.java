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

    public int invalidateX;
    public int invalidateY;

    public boolean resourcesReflected;

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

        invalidateX = -1;
        invalidateY = -1;

        resourcesReflected = false;
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
        scheduleUnits();
        writeMapBoundsInitialized();
    }

    @Override
    public void endTurn() {
        super.endTurn();
        reflectResources();
        invalidateSymmetry();
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
            if (roundSeenEnemyBatter == uc.getRound()) {
                state = HqState.DEFENSE;
            }
        } else if (state == HqState.DEFENSE) {
            if (roundSeenEnemyBatter != uc.getRound()) {
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
        for (int i = 0; i < comms.BASE_SLOTS; i++) {
            if (comms.readBaseX(i) == -1)
                break;
            comms.decrementBasePitcherHeartbeat(i);
            comms.decrementBaseBatterHeartbeat(i);
        }

        for (int i = 0; i < comms.STADIUM_SLOTS; i++) {
            if (comms.readStadiumX(i) == -1)
                break;
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

    public void scheduleUnits() {
        int id;
        for (int i = 0; i < comms.SCHEDULE_SLOTS; i++) {
            id = comms.readScheduleId(i);
            if (id == -1) {
                return;
            }

            if (uc.canSchedule(id)) {
                // debug.println("Scheduled unit " + id);
                uc.schedule(id);
            }
            comms.writeScheduleId(id, -1);
        }
    }

    public void writeMapBoundsInitialized() {
        if (util.mapBoundsInitialized)
            return;

        int mapXMin = comms.readMapXMin();
        int mapXMax = comms.readMapXMax();
        int mapYMin = comms.readMapYMin();
        int mapYMax = comms.readMapYMax();

        if (mapXMin == -1 || mapXMax == -1 || mapYMin == -1 || mapYMax == -1)
            return;

        comms.writeMapBoundsIntialized(1);
        util.mapBoundsInitialized = true;
    }

    // This only runs once after the symmetry is determined.
    public void reflectResources() {
        if (resourcesReflected)
            return;

        int vertSym = comms.readSymmetryVertical();
        int horizSym = comms.readSymmetryHorizontal();
        int rotSym = comms.readSymmetryRotational();

        if (vertSym + horizSym + rotSym != 1)
            return;

        resourcesReflected = true;

        Location resourceLoc;
        Location[] symLocs;
        for (int i = 0; i < comms.BASE_SLOTS; i++) {
            if (comms.readBaseX(i) == -1)
                break;
            resourceLoc = comms.readBase(i);
            symLocs = util.getValidSymmetryLocs(resourceLoc, vertSym == 1, horizSym == 1, rotSym == 1);
            if (symLocs != null && symLocs.length == 1) {
                comms.logBase(symLocs[0]);
            }
        }

        for (int i = 0; i < comms.STADIUM_SLOTS; i++) {
            if (comms.readStadiumX(i) == -1)
                break;
            resourceLoc = comms.readStadium(i);
            symLocs = util.getValidSymmetryLocs(resourceLoc, vertSym == 1, horizSym == 1, rotSym == 1);
            if (symLocs != null && symLocs.length == 1) {
                comms.logStadium(symLocs[0]);
            }
        }
    }

    public void invalidateSymmetry() {
        if (!util.mapBoundsInitialized)
            return;

        int vertSym = comms.readSymmetryVertical();
        int horizSym = comms.readSymmetryHorizontal();
        int rotSym = comms.readSymmetryRotational();

        // Only one symmetry left, do not invalidate
        if (vertSym + horizSym + rotSym == 1)
            return;

        int mapXMin = comms.readMapXMin();
        int mapXMax = comms.readMapXMax();
        int mapYMin = comms.readMapYMin();
        int mapYMax = comms.readMapYMax();

        if (invalidateX == -1 && invalidateY == -1) {
            invalidateX = mapXMin;
            invalidateY = mapYMin;
        }

        Location loc;
        for (; invalidateX < mapXMax; invalidateX++) {
            if (uc.getEnergyLeft() < mapTracker.VISITED_BC_LEFT)
                break;
            for (; invalidateY < mapYMax; invalidateY++) {
                if (uc.getEnergyLeft() < mapTracker.VISITED_BC_LEFT)
                    break;
                loc = new Location(invalidateX, invalidateY);
                mapTracker.invalidateSymmetries(loc);
            }
            invalidateY = mapYMin;
        }
    }
}
