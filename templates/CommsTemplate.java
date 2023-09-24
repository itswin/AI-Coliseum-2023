package examplefuncsplayer;

import aic2023.user.*;

public class Comms {

    private UnitController uc;
    private Robot r;

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

    public Comms(UnitController u, Robot robot) {
        uc = u;
        r = robot;
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

    public boolean isExploreDirFlag(int flag) {
        return flag >= HqFlags.EXPLORE_NORTH && flag <= HqFlags.EXPLORE_NORTHWEST;
    }
    // INIT LOC METHODS

    // MAIN READ AND WRITE METHODS

}
