package MPWorking;

import aic2023.user.*;

public class Comms {

    private UnitController uc;
    private Robot r;

    public final int MAP_SLOTS = 1;
    public final int HQ_SLOTS = 1;
    public final int SYMMETRY_SLOTS = 1;

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

}
