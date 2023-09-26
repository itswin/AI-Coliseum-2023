package MPCursoryMicro;

import aic2023.user.*;

public class Debug {
    final boolean VERBOSE = false;
    public final boolean INFO = true;
    public final boolean PATHFINDING = false;
    public final boolean INDICATORS = true;

    private UnitController uc;
    private Robot robot;

    public Debug(UnitController u, Robot r) {
        uc = u;
        robot = r;
    }

    public void println(boolean cond, String s) {
        if (VERBOSE && cond) {
            uc.println("ID: " + uc.getInfo().getID() + "\tRound: " + uc.getRound() + "\t" + s);
        }
    }

    public void println(boolean cond, String s, int id) {
        if (VERBOSE && cond && (id < 0 || uc.getInfo().getID() == id)) {
            uc.println("ID: " + uc.getInfo().getID() + "\tRound: " + uc.getRound() + "\t" + s);
        }
    }

    public void println(String s) {
        println(INFO, s);
    }

    public void println(String s, int id) {
        println(INFO, s, id);
    }

    public void setIndicatorDot(boolean cond, Location loc, int r, int g, int b) {
        if (VERBOSE && INDICATORS && cond && loc != null) {
            uc.drawPointDebug(loc, g, r, g);
        }
    }

    public void setIndicatorLine(boolean cond, Location startLoc, Location endLoc, int r, int g, int b) {
        if (VERBOSE && INDICATORS && cond && startLoc != null && endLoc != null) {
            uc.drawLineDebug(startLoc, endLoc, r, g, b);
        }
    }

    public void setIndicatorDot(Location loc, int r, int g, int b) {
        setIndicatorDot(INDICATORS, loc, r, g, b);
    }

    public void setIndicatorLine(Location startLoc, Location endLoc, int r, int g, int b) {
        setIndicatorLine(INDICATORS, startLoc, endLoc, r, g, b);
    }
}