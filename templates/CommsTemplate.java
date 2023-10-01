package examplefuncsplayer;

import aic2023.user.*;

public class Comms {

    private UnitController uc;
    private Robot robot;

    final int SHARED_MAP_SIZE = 121;
    final int MAP_SIZE = 60;

    int mapOffsetX = 0;
    int mapOffsetY = 0;

    final public class HqFlagsEnum {
        public int UNKNOWN_FLAG = 0;
        public int EXPLORE_NORTH = 1;
        public int EXPLORE_EAST = 2;
        public int EXPLORE_SOUTH = 3;
        public int EXPLORE_WEST = 4;
        public int EXPLORE_NORTHEAST = 5;
        public int EXPLORE_SOUTHEAST = 6;
        public int EXPLORE_SOUTHWEST = 7;
        public int EXPLORE_NORTHWEST = 8;
    }

    final HqFlagsEnum HqFlags = new HqFlagsEnum();
    // CONSTS

    public Comms(UnitController u, Robot r) {
        uc = u;
        robot = r;
    }

    public void loadMapOffset() {
        mapOffsetX = MAP_SIZE - robot.hq.x;
        mapOffsetY = MAP_SIZE - robot.hq.y;
    }

    public Location readHqLocation() {
        return new Location(readHqXCoord(), readHqYCoord());
    }

    public void init() {
        initMap();
        initSymmetry();
        // INIT METHOD CALLS
    }

    public void initMap() {
        writeMapWidth(-1);
        writeMapHeight(-1);
        writeMapXMin(-1);
        writeMapYMin(-1);
        writeMapXMax(-1);
        writeMapYMax(-1);
    }

    public void writeHqLocation(Location loc) {
        writeHqXCoord(loc.x);
        writeHqYCoord(loc.y);
    }

    public void initSymmetry() {
        writeSymmetryVertical(1);
        writeSymmetryHorizontal(1);
        writeSymmetryRotational(1);
    }

    public void resetUnitCount() {
        writeNumPitchersLast(readNumPitchers());
        writeNumBattersLast(readNumBatters());
        writeNumCatchersLast(readNumCatchers());
        resetPitchers();
        resetBatters();
        resetCatchers();
    }

    public boolean isExploreDirFlag(int flag) {
        return flag >= HqFlags.EXPLORE_NORTH && flag <= HqFlags.EXPLORE_NORTHWEST;
    }

    public void scheduleId(int id) {
        int slot = -1;
        for (; ++slot < SCHEDULE_SLOTS;) {
            if (readScheduleId(slot) == -1) {
                writeScheduleId(slot, id);
            }
        }
    }
    // INIT LOC METHODS
    // MAIN READ AND WRITE METHODS

}
