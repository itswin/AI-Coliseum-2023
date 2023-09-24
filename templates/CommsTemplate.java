package examplefuncsplayer;

import aic2023.user.*;

public class Comms {

    private UnitController uc;
    private Robot r;
    // CONSTS

    public Comms(UnitController u, Robot robot) {
        uc = u;
        r = robot;
    }

    public Location readHqLocation() {
        return new Location(readHqXCoord(), readHqYCoord());
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
    // MAIN READ AND WRITE METHODS

}
