package MPWorking;

import aic2023.user.*;
import MPWorking.fast.*;

public class Robot {
    public UnitController uc;

    public Location hq;

    public FastMath fastMath;
    public Debug debug;
    public Util util;
    public MapTracker mapTracker;
    public Explore explore;
    public Comms comms;

    public Robot(UnitController u) {
        uc = u;

        hq = null;
        if (uc.getType() == UnitType.HQ) {
            hq = uc.getLocation();
        } else {
            for (UnitInfo loc : uc.senseUnits(2, uc.getTeam())) {
                if (loc.getType() == UnitType.HQ) {
                    hq = loc.getLocation();
                    break;
                }
            }

            if (hq == null) {
                uc.println("ERROR: Did not find HQ!");
                hq = uc.getLocation();
            }
        }

        fastMath = new FastMath(u);
        debug = new Debug(u, this);
        util = new Util(u, this);
        mapTracker = new MapTracker(u, this);
        explore = new Explore(u, this);
        comms = new Comms(u, this);
    }

    public void initTurn() {
    }

    public void takeTurn() {
    }

    public void endTurn() {
        mapTracker.initialize();
        mapTracker.markSeen();
    }

}