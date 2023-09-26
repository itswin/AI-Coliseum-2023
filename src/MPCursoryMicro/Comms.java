package MPCursoryMicro;

import aic2023.user.*;

public class Comms {

    private UnitController uc;
    private Robot robot;

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

    public final int MAP_SLOTS = 1;
    public final int HQ_SLOTS = 1;
    public final int SYMMETRY_SLOTS = 1;
    public final int BASE_SLOTS = 16;
    public final int STADIUM_SLOTS = 16;
    public final int NUM_SLOTS = 1;

    public Comms(UnitController u, Robot r) {
        uc = u;
        robot = r;
    }

    public Location readHqLocation() {
        return new Location(readHqXCoord(), readHqYCoord());
    }

    public void init() {
        initMap();
        initSymmetry();
            initBase();
            initStadium();
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

    public void initBase() {
        for (int i = BASE_SLOTS; --i >= 0;) {
            writeBase(i, new Location(-1, -1));
        }
    }

    public void initStadium() {
        for (int i = STADIUM_SLOTS; --i >= 0;) {
            writeStadium(i, new Location(-1, -1));
        }
    }


    public int readMapXMin() {
        return uc.read(0);
    }

    public void writeMapXMin(int value) {
        uc.write(0, value);
    }

    public int readMapXMax() {
        return uc.read(1);
    }

    public void writeMapXMax(int value) {
        uc.write(1, value);
    }

    public int readMapWidth() {
        return uc.read(2);
    }

    public void writeMapWidth(int value) {
        uc.write(2, value);
    }

    public int readMapYMin() {
        return uc.read(3);
    }

    public void writeMapYMin(int value) {
        uc.write(3, value);
    }

    public int readMapYMax() {
        return uc.read(4);
    }

    public void writeMapYMax(int value) {
        uc.write(4, value);
    }

    public int readMapHeight() {
        return uc.read(5);
    }

    public void writeMapHeight(int value) {
        uc.write(5, value);
    }

    public int readHqFlag() {
        return uc.read(6);
    }

    public void writeHqFlag(int value) {
        uc.write(6, value);
    }

    public int readHqXCoord() {
        return uc.read(7);
    }

    public void writeHqXCoord(int value) {
        uc.write(7, value);
    }

    public int readHqYCoord() {
        return uc.read(8);
    }

    public void writeHqYCoord(int value) {
        uc.write(8, value);
    }

    public int readSymmetryVertical() {
        return uc.read(9);
    }

    public void writeSymmetryVertical(int value) {
        uc.write(9, value);
    }

    public int readSymmetryHorizontal() {
        return uc.read(10);
    }

    public void writeSymmetryHorizontal(int value) {
        uc.write(10, value);
    }

    public int readSymmetryRotational() {
        return uc.read(11);
    }

    public void writeSymmetryRotational(int value) {
        uc.write(11, value);
    }

    public int readBaseX(int slot) {
        return uc.read(12 + slot * 2);
    }

    public void writeBaseX(int slot, int value) {
        uc.write(12 + slot * 2, value);
    }

    public int readBaseY(int slot) {
        return uc.read(28 + slot * 2);
    }

    public void writeBaseY(int slot, int value) {
        uc.write(28 + slot * 2, value);
    }

    public Location readBase(int slot) {
        return new Location(readBaseX(slot), readBaseY(slot));
    }

    public void writeBase(int slot, Location loc) {
        writeBaseX(slot, loc.x);
        writeBaseY(slot, loc.y);
    }

    public void logBase(Location loc) {
        int slot = -1;
        Location slotLoc;
        for (; ++slot < BASE_SLOTS;) {
            slotLoc = readBase(slot);
            if (slotLoc.x == -1) {
                writeBase(slot, loc);
                robot.debug.println("Logging base at " + loc + " in slot " + slot);
                return;
            } else if (slotLoc.equals(loc)) {
                return;
            }
        }
    }

    public int readStadiumX(int slot) {
        return uc.read(44 + slot * 2);
    }

    public void writeStadiumX(int slot, int value) {
        uc.write(44 + slot * 2, value);
    }

    public int readStadiumY(int slot) {
        return uc.read(60 + slot * 2);
    }

    public void writeStadiumY(int slot, int value) {
        uc.write(60 + slot * 2, value);
    }

    public Location readStadium(int slot) {
        return new Location(readStadiumX(slot), readStadiumY(slot));
    }

    public void writeStadium(int slot, Location loc) {
        writeStadiumX(slot, loc.x);
        writeStadiumY(slot, loc.y);
    }

    public void logStadium(Location loc) {
        int slot = -1;
        Location slotLoc;
        for (; ++slot < STADIUM_SLOTS;) {
            slotLoc = readStadium(slot);
            if (slotLoc.x == -1) {
                writeStadium(slot, loc);
                robot.debug.println("Logging stadium at " + loc + " in slot " + slot);
                return;
            } else if (slotLoc.equals(loc)) {
                return;
            }
        }
    }

    public int readNumPitchers() {
        return uc.read(76);
    }

    public void writeNumPitchers(int value) {
        uc.write(76, value);
    }

    public void incrementPitchers() {
        writeNumPitchers(readNumPitchers() + 1);
    }

    public void resetPitchers() {
        writeNumPitchers(0);
    }

    public int readNumPitchersLast() {
        return uc.read(77);
    }

    public void writeNumPitchersLast(int value) {
        uc.write(77, value);
    }

    public int readNumBatters() {
        return uc.read(78);
    }

    public void writeNumBatters(int value) {
        uc.write(78, value);
    }

    public void incrementBatters() {
        writeNumBatters(readNumBatters() + 1);
    }

    public void resetBatters() {
        writeNumBatters(0);
    }

    public int readNumBattersLast() {
        return uc.read(79);
    }

    public void writeNumBattersLast(int value) {
        uc.write(79, value);
    }

    public int readNumCatchers() {
        return uc.read(80);
    }

    public void writeNumCatchers(int value) {
        uc.write(80, value);
    }

    public void incrementCatchers() {
        writeNumCatchers(readNumCatchers() + 1);
    }

    public void resetCatchers() {
        writeNumCatchers(0);
    }

    public int readNumCatchersLast() {
        return uc.read(81);
    }

    public void writeNumCatchersLast(int value) {
        uc.write(81, value);
    }

}
