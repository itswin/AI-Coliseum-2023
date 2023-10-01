package MPIll.bfs;

import aic2023.user.*;

public class BFS10 {

    public UnitController uc;

    Location l94; // location representing relative coordinate (-3, -1)
    double d94; // shortest distance to location from current location
    // Direction dir94; // best direction to take now to optimally get to location
    double score94; // heuristic distance from location to target

    Location l109; // location representing relative coordinate (-3, 0)
    double d109; // shortest distance to location from current location
    // Direction dir109; // best direction to take now to optimally get to location
    double score109; // heuristic distance from location to target

    Location l124; // location representing relative coordinate (-3, 1)
    double d124; // shortest distance to location from current location
    // Direction dir124; // best direction to take now to optimally get to location
    double score124; // heuristic distance from location to target

    Location l80; // location representing relative coordinate (-2, -2)
    double d80; // shortest distance to location from current location
    // Direction dir80; // best direction to take now to optimally get to location
    double score80; // heuristic distance from location to target

    Location l95; // location representing relative coordinate (-2, -1)
    double d95; // shortest distance to location from current location
    // Direction dir95; // best direction to take now to optimally get to location
    double score95; // heuristic distance from location to target

    Location l110; // location representing relative coordinate (-2, 0)
    double d110; // shortest distance to location from current location
    // Direction dir110; // best direction to take now to optimally get to location
    double score110; // heuristic distance from location to target

    Location l125; // location representing relative coordinate (-2, 1)
    double d125; // shortest distance to location from current location
    // Direction dir125; // best direction to take now to optimally get to location
    double score125; // heuristic distance from location to target

    Location l140; // location representing relative coordinate (-2, 2)
    double d140; // shortest distance to location from current location
    // Direction dir140; // best direction to take now to optimally get to location
    double score140; // heuristic distance from location to target

    Location l66; // location representing relative coordinate (-1, -3)
    double d66; // shortest distance to location from current location
    // Direction dir66; // best direction to take now to optimally get to location
    double score66; // heuristic distance from location to target

    Location l81; // location representing relative coordinate (-1, -2)
    double d81; // shortest distance to location from current location
    // Direction dir81; // best direction to take now to optimally get to location
    double score81; // heuristic distance from location to target

    Location l96; // location representing relative coordinate (-1, -1)
    double d96; // shortest distance to location from current location
    // Direction dir96; // best direction to take now to optimally get to location
    double score96; // heuristic distance from location to target

    Location l111; // location representing relative coordinate (-1, 0)
    double d111; // shortest distance to location from current location
    // Direction dir111; // best direction to take now to optimally get to location
    double score111; // heuristic distance from location to target

    Location l126; // location representing relative coordinate (-1, 1)
    double d126; // shortest distance to location from current location
    // Direction dir126; // best direction to take now to optimally get to location
    double score126; // heuristic distance from location to target

    Location l141; // location representing relative coordinate (-1, 2)
    double d141; // shortest distance to location from current location
    // Direction dir141; // best direction to take now to optimally get to location
    double score141; // heuristic distance from location to target

    Location l156; // location representing relative coordinate (-1, 3)
    double d156; // shortest distance to location from current location
    // Direction dir156; // best direction to take now to optimally get to location
    double score156; // heuristic distance from location to target

    Location l67; // location representing relative coordinate (0, -3)
    double d67; // shortest distance to location from current location
    // Direction dir67; // best direction to take now to optimally get to location
    double score67; // heuristic distance from location to target

    Location l82; // location representing relative coordinate (0, -2)
    double d82; // shortest distance to location from current location
    // Direction dir82; // best direction to take now to optimally get to location
    double score82; // heuristic distance from location to target

    Location l97; // location representing relative coordinate (0, -1)
    double d97; // shortest distance to location from current location
    // Direction dir97; // best direction to take now to optimally get to location
    double score97; // heuristic distance from location to target

    Location l112; // location representing relative coordinate (0, 0)
    double d112; // shortest distance to location from current location
    // Direction dir112; // best direction to take now to optimally get to location
    double score112; // heuristic distance from location to target

    Location l127; // location representing relative coordinate (0, 1)
    double d127; // shortest distance to location from current location
    // Direction dir127; // best direction to take now to optimally get to location
    double score127; // heuristic distance from location to target

    Location l142; // location representing relative coordinate (0, 2)
    double d142; // shortest distance to location from current location
    // Direction dir142; // best direction to take now to optimally get to location
    double score142; // heuristic distance from location to target

    Location l157; // location representing relative coordinate (0, 3)
    double d157; // shortest distance to location from current location
    // Direction dir157; // best direction to take now to optimally get to location
    double score157; // heuristic distance from location to target

    Location l68; // location representing relative coordinate (1, -3)
    double d68; // shortest distance to location from current location
    // Direction dir68; // best direction to take now to optimally get to location
    double score68; // heuristic distance from location to target

    Location l83; // location representing relative coordinate (1, -2)
    double d83; // shortest distance to location from current location
    // Direction dir83; // best direction to take now to optimally get to location
    double score83; // heuristic distance from location to target

    Location l98; // location representing relative coordinate (1, -1)
    double d98; // shortest distance to location from current location
    // Direction dir98; // best direction to take now to optimally get to location
    double score98; // heuristic distance from location to target

    Location l113; // location representing relative coordinate (1, 0)
    double d113; // shortest distance to location from current location
    // Direction dir113; // best direction to take now to optimally get to location
    double score113; // heuristic distance from location to target

    Location l128; // location representing relative coordinate (1, 1)
    double d128; // shortest distance to location from current location
    // Direction dir128; // best direction to take now to optimally get to location
    double score128; // heuristic distance from location to target

    Location l143; // location representing relative coordinate (1, 2)
    double d143; // shortest distance to location from current location
    // Direction dir143; // best direction to take now to optimally get to location
    double score143; // heuristic distance from location to target

    Location l158; // location representing relative coordinate (1, 3)
    double d158; // shortest distance to location from current location
    // Direction dir158; // best direction to take now to optimally get to location
    double score158; // heuristic distance from location to target

    Location l84; // location representing relative coordinate (2, -2)
    double d84; // shortest distance to location from current location
    // Direction dir84; // best direction to take now to optimally get to location
    double score84; // heuristic distance from location to target

    Location l99; // location representing relative coordinate (2, -1)
    double d99; // shortest distance to location from current location
    // Direction dir99; // best direction to take now to optimally get to location
    double score99; // heuristic distance from location to target

    Location l114; // location representing relative coordinate (2, 0)
    double d114; // shortest distance to location from current location
    // Direction dir114; // best direction to take now to optimally get to location
    double score114; // heuristic distance from location to target

    Location l129; // location representing relative coordinate (2, 1)
    double d129; // shortest distance to location from current location
    // Direction dir129; // best direction to take now to optimally get to location
    double score129; // heuristic distance from location to target

    Location l144; // location representing relative coordinate (2, 2)
    double d144; // shortest distance to location from current location
    // Direction dir144; // best direction to take now to optimally get to location
    double score144; // heuristic distance from location to target

    Location l100; // location representing relative coordinate (3, -1)
    double d100; // shortest distance to location from current location
    // Direction dir100; // best direction to take now to optimally get to location
    double score100; // heuristic distance from location to target

    Location l115; // location representing relative coordinate (3, 0)
    double d115; // shortest distance to location from current location
    // Direction dir115; // best direction to take now to optimally get to location
    double score115; // heuristic distance from location to target

    Location l130; // location representing relative coordinate (3, 1)
    double d130; // shortest distance to location from current location
    // Direction dir130; // best direction to take now to optimally get to location
    double score130; // heuristic distance from location to target


    public BFS10(UnitController r) {
        uc = r;
    }

    public final Direction NORTH = Direction.NORTH;
    public final Direction NORTHEAST = Direction.NORTHEAST;
    public final Direction EAST = Direction.EAST;
    public final Direction SOUTHEAST = Direction.SOUTHEAST;
    public final Direction SOUTH = Direction.SOUTH;
    public final Direction SOUTHWEST = Direction.SOUTHWEST;
    public final Direction WEST = Direction.WEST;
    public final Direction NORTHWEST = Direction.NORTHWEST;
    public final Direction ZERO = Direction.ZERO;

    private final Direction[] DIRECTIONS = new Direction[] {null, EAST, NORTH, WEST, SOUTH, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST};

    public double ans;
    public double bestScore;
    public double currDist;
    public MapObject mapObj;

    public Direction direction(double dist) {
        if (dist==Double.POSITIVE_INFINITY) {
            return null;
        }
        return DIRECTIONS[(int)(dist * 16 % 16)];
    }

    public Direction bestDir(Location target) {

        l112 = uc.getLocation();
        d112 = 0;
        // dir112 = ZERO;

        l111 = l112.add(WEST); // (-1, 0) from (0, 0)
        d111 = 99999;
        // dir111 = null;

        l97 = l112.add(SOUTH); // (0, -1) from (0, 0)
        d97 = 99999;
        // dir97 = null;

        l127 = l112.add(NORTH); // (0, 1) from (0, 0)
        d127 = 99999;
        // dir127 = null;

        l113 = l112.add(EAST); // (1, 0) from (0, 0)
        d113 = 99999;
        // dir113 = null;

        l96 = l112.add(SOUTHWEST); // (-1, -1) from (0, 0)
        d96 = 99999;
        // dir96 = null;

        l126 = l112.add(NORTHWEST); // (-1, 1) from (0, 0)
        d126 = 99999;
        // dir126 = null;

        l98 = l112.add(SOUTHEAST); // (1, -1) from (0, 0)
        d98 = 99999;
        // dir98 = null;

        l128 = l112.add(NORTHEAST); // (1, 1) from (0, 0)
        d128 = 99999;
        // dir128 = null;

        l110 = l111.add(WEST); // (-2, 0) from (-1, 0)
        d110 = 99999;
        // dir110 = null;

        l82 = l97.add(SOUTH); // (0, -2) from (0, -1)
        d82 = 99999;
        // dir82 = null;

        l142 = l127.add(NORTH); // (0, 2) from (0, 1)
        d142 = 99999;
        // dir142 = null;

        l114 = l113.add(EAST); // (2, 0) from (1, 0)
        d114 = 99999;
        // dir114 = null;

        l95 = l111.add(SOUTHWEST); // (-2, -1) from (-1, 0)
        d95 = 99999;
        // dir95 = null;

        l125 = l111.add(NORTHWEST); // (-2, 1) from (-1, 0)
        d125 = 99999;
        // dir125 = null;

        l81 = l97.add(SOUTHWEST); // (-1, -2) from (0, -1)
        d81 = 99999;
        // dir81 = null;

        l141 = l127.add(NORTHWEST); // (-1, 2) from (0, 1)
        d141 = 99999;
        // dir141 = null;

        l83 = l97.add(SOUTHEAST); // (1, -2) from (0, -1)
        d83 = 99999;
        // dir83 = null;

        l143 = l127.add(NORTHEAST); // (1, 2) from (0, 1)
        d143 = 99999;
        // dir143 = null;

        l99 = l113.add(SOUTHEAST); // (2, -1) from (1, 0)
        d99 = 99999;
        // dir99 = null;

        l129 = l113.add(NORTHEAST); // (2, 1) from (1, 0)
        d129 = 99999;
        // dir129 = null;

        l80 = l96.add(SOUTHWEST); // (-2, -2) from (-1, -1)
        d80 = 99999;
        // dir80 = null;

        l140 = l126.add(NORTHWEST); // (-2, 2) from (-1, 1)
        d140 = 99999;
        // dir140 = null;

        l84 = l98.add(SOUTHEAST); // (2, -2) from (1, -1)
        d84 = 99999;
        // dir84 = null;

        l144 = l128.add(NORTHEAST); // (2, 2) from (1, 1)
        d144 = 99999;
        // dir144 = null;

        l109 = l110.add(WEST); // (-3, 0) from (-2, 0)
        d109 = 99999;
        // dir109 = null;

        l67 = l82.add(SOUTH); // (0, -3) from (0, -2)
        d67 = 99999;
        // dir67 = null;

        l157 = l142.add(NORTH); // (0, 3) from (0, 2)
        d157 = 99999;
        // dir157 = null;

        l115 = l114.add(EAST); // (3, 0) from (2, 0)
        d115 = 99999;
        // dir115 = null;

        l94 = l110.add(SOUTHWEST); // (-3, -1) from (-2, 0)
        d94 = 99999;
        // dir94 = null;

        l124 = l110.add(NORTHWEST); // (-3, 1) from (-2, 0)
        d124 = 99999;
        // dir124 = null;

        l66 = l82.add(SOUTHWEST); // (-1, -3) from (0, -2)
        d66 = 99999;
        // dir66 = null;

        l156 = l142.add(NORTHWEST); // (-1, 3) from (0, 2)
        d156 = 99999;
        // dir156 = null;

        l68 = l82.add(SOUTHEAST); // (1, -3) from (0, -2)
        d68 = 99999;
        // dir68 = null;

        l158 = l142.add(NORTHEAST); // (1, 3) from (0, 2)
        d158 = 99999;
        // dir158 = null;

        l100 = l114.add(SOUTHEAST); // (3, -1) from (2, 0)
        d100 = 99999;
        // dir100 = null;

        l130 = l114.add(NORTHEAST); // (3, 1) from (2, 0)
        d130 = 99999;
        // dir130 = null;



        // check (-1, 0)
        if (uc.canSenseLocation(l111) && 
                (mapObj = uc.senseObjectAtLocation(l111, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d111 = 10 + 0.1875;
        }

        // check (0, -1)
        if (uc.canSenseLocation(l97) && 
                (mapObj = uc.senseObjectAtLocation(l97, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d97 = 10 + Math.min(0.25, d111 + 4);
        }

        // check (0, 1)
        if (uc.canSenseLocation(l127) && 
                (mapObj = uc.senseObjectAtLocation(l127, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d127 = 10 + Math.min(0.125, d111 + 4);
        }

        // check (1, 0)
        if (uc.canSenseLocation(l113) && 
                (mapObj = uc.senseObjectAtLocation(l113, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d113 = 10 + Math.min(0.0625, Math.min(d97 + 4, d127 + 4));
        }

        // check (-1, -1)
        if (uc.canSenseLocation(l96) && 
                (mapObj = uc.senseObjectAtLocation(l96, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d96 = 10 + Math.min(4.4375, Math.min(d111, d97));
        }

        // check (-1, 1)
        if (uc.canSenseLocation(l126) && 
                (mapObj = uc.senseObjectAtLocation(l126, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d126 = 10 + Math.min(4.375, Math.min(d111, d127));
        }

        // check (1, -1)
        if (uc.canSenseLocation(l98) && 
                (mapObj = uc.senseObjectAtLocation(l98, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d98 = 10 + Math.min(4.5, Math.min(d97, d113));
        }

        // check (1, 1)
        if (uc.canSenseLocation(l128) && 
                (mapObj = uc.senseObjectAtLocation(l128, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d128 = 10 + Math.min(4.3125, Math.min(d127, d113));
        }

        // check (-2, 0)
        if (uc.canSenseLocation(l110) && 
                (mapObj = uc.senseObjectAtLocation(l110, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d110 = 10 + Math.min(d111, Math.min(d96 + 4, d126 + 4));
        }

        // check (0, -2)
        if (uc.canSenseLocation(l82) && 
                (mapObj = uc.senseObjectAtLocation(l82, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d82 = 10 + Math.min(d97, Math.min(d96 + 4, d98 + 4));
        }

        // check (0, 2)
        if (uc.canSenseLocation(l142) && 
                (mapObj = uc.senseObjectAtLocation(l142, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d142 = 10 + Math.min(d127, Math.min(d126 + 4, d128 + 4));
        }

        // check (2, 0)
        if (uc.canSenseLocation(l114) && 
                (mapObj = uc.senseObjectAtLocation(l114, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d114 = 10 + Math.min(d113, Math.min(d98 + 4, d128 + 4));
        }

        // check (-2, -1)
        if (uc.canSenseLocation(l95) && 
                (mapObj = uc.senseObjectAtLocation(l95, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d95 = 10 + Math.min(d111 + 4, Math.min(d96, d110));
        }

        // check (-2, 1)
        if (uc.canSenseLocation(l125) && 
                (mapObj = uc.senseObjectAtLocation(l125, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d125 = 10 + Math.min(d111 + 4, Math.min(d126, d110));
        }

        // check (-1, -2)
        if (uc.canSenseLocation(l81) && 
                (mapObj = uc.senseObjectAtLocation(l81, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d81 = 10 + Math.min(d97 + 4, Math.min(d96, Math.min(d82, d95 + 4)));
        }

        // check (-1, 2)
        if (uc.canSenseLocation(l141) && 
                (mapObj = uc.senseObjectAtLocation(l141, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d141 = 10 + Math.min(d127 + 4, Math.min(d126, Math.min(d142, d125 + 4)));
        }

        // check (1, -2)
        if (uc.canSenseLocation(l83) && 
                (mapObj = uc.senseObjectAtLocation(l83, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d83 = 10 + Math.min(d97 + 4, Math.min(d98, d82));
        }

        // check (1, 2)
        if (uc.canSenseLocation(l143) && 
                (mapObj = uc.senseObjectAtLocation(l143, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d143 = 10 + Math.min(d127 + 4, Math.min(d128, d142));
        }

        // check (2, -1)
        if (uc.canSenseLocation(l99) && 
                (mapObj = uc.senseObjectAtLocation(l99, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d99 = 10 + Math.min(d113 + 4, Math.min(d98, Math.min(d114, d83 + 4)));
        }

        // check (2, 1)
        if (uc.canSenseLocation(l129) && 
                (mapObj = uc.senseObjectAtLocation(l129, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d129 = 10 + Math.min(d113 + 4, Math.min(d128, Math.min(d114, d143 + 4)));
        }

        // check (-2, -2)
        if (uc.canSenseLocation(l80) && 
                (mapObj = uc.senseObjectAtLocation(l80, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d80 = 10 + Math.min(d96 + 4, Math.min(d95, d81));
        }

        // check (-2, 2)
        if (uc.canSenseLocation(l140) && 
                (mapObj = uc.senseObjectAtLocation(l140, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d140 = 10 + Math.min(d126 + 4, Math.min(d125, d141));
        }

        // check (2, -2)
        if (uc.canSenseLocation(l84) && 
                (mapObj = uc.senseObjectAtLocation(l84, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d84 = 10 + Math.min(d98 + 4, Math.min(d83, d99));
        }

        // check (2, 2)
        if (uc.canSenseLocation(l144) && 
                (mapObj = uc.senseObjectAtLocation(l144, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d144 = 10 + Math.min(d128 + 4, Math.min(d143, d129));
        }

        // check (-3, 0)
        if (uc.canSenseLocation(l109) && 
                (mapObj = uc.senseObjectAtLocation(l109, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d109 = 10 + Math.min(d110, Math.min(d95 + 4, d125 + 4));
        }

        // check (0, -3)
        if (uc.canSenseLocation(l67) && 
                (mapObj = uc.senseObjectAtLocation(l67, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d67 = 10 + Math.min(d82, Math.min(d81 + 4, d83 + 4));
        }

        // check (0, 3)
        if (uc.canSenseLocation(l157) && 
                (mapObj = uc.senseObjectAtLocation(l157, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d157 = 10 + Math.min(d142, Math.min(d141 + 4, d143 + 4));
        }

        // check (3, 0)
        if (uc.canSenseLocation(l115) && 
                (mapObj = uc.senseObjectAtLocation(l115, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d115 = 10 + Math.min(d114, Math.min(d99 + 4, d129 + 4));
        }

        // check (-3, -1)
        if (uc.canSenseLocation(l94) && 
                (mapObj = uc.senseObjectAtLocation(l94, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d94 = 10 + Math.min(d110 + 4, Math.min(d95, Math.min(d80 + 4, d109)));
        }

        // check (-3, 1)
        if (uc.canSenseLocation(l124) && 
                (mapObj = uc.senseObjectAtLocation(l124, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d124 = 10 + Math.min(d110 + 4, Math.min(d125, Math.min(d140 + 4, d109)));
        }

        // check (-1, -3)
        if (uc.canSenseLocation(l66) && 
                (mapObj = uc.senseObjectAtLocation(l66, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d66 = 10 + Math.min(d82 + 4, Math.min(d81, Math.min(d80 + 4, d67)));
        }

        // check (-1, 3)
        if (uc.canSenseLocation(l156) && 
                (mapObj = uc.senseObjectAtLocation(l156, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d156 = 10 + Math.min(d142 + 4, Math.min(d141, Math.min(d140 + 4, d157)));
        }

        // check (1, -3)
        if (uc.canSenseLocation(l68) && 
                (mapObj = uc.senseObjectAtLocation(l68, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d68 = 10 + Math.min(d82 + 4, Math.min(d83, Math.min(d84 + 4, d67)));
        }

        // check (1, 3)
        if (uc.canSenseLocation(l158) && 
                (mapObj = uc.senseObjectAtLocation(l158, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d158 = 10 + Math.min(d142 + 4, Math.min(d143, Math.min(d144 + 4, d157)));
        }

        // check (3, -1)
        if (uc.canSenseLocation(l100) && 
                (mapObj = uc.senseObjectAtLocation(l100, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d100 = 10 + Math.min(d114 + 4, Math.min(d99, Math.min(d84 + 4, d115)));
        }

        // check (3, 1)
        if (uc.canSenseLocation(l130) && 
                (mapObj = uc.senseObjectAtLocation(l130, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d130 = 10 + Math.min(d114 + 4, Math.min(d129, Math.min(d144 + 4, d115)));
        }


        // uc.println("LOCAL DISTANCES:");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d156 + "\t" + d157 + "\t" + d158 + "\t" + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d140 + "\t" + d141 + "\t" + d142 + "\t" + d143 + "\t" + d144 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + d124 + "\t" + d125 + "\t" + d126 + "\t" + d127 + "\t" + d128 + "\t" + d129 + "\t" + d130 + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + d109 + "\t" + d110 + "\t" + d111 + "\t" + d112 + "\t" + d113 + "\t" + d114 + "\t" + d115 + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + d94 + "\t" + d95 + "\t" + d96 + "\t" + d97 + "\t" + d98 + "\t" + d99 + "\t" + d100 + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d80 + "\t" + d81 + "\t" + d82 + "\t" + d83 + "\t" + d84 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d66 + "\t" + d67 + "\t" + d68 + "\t" + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("DIRECTIONS:");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir156 + "\t" + dir157 + "\t" + dir158 + "\t" + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir140 + "\t" + dir141 + "\t" + dir142 + "\t" + dir143 + "\t" + dir144 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + dir124 + "\t" + dir125 + "\t" + dir126 + "\t" + dir127 + "\t" + dir128 + "\t" + dir129 + "\t" + dir130 + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + dir109 + "\t" + dir110 + "\t" + dir111 + "\t" + dir112 + "\t" + dir113 + "\t" + dir114 + "\t" + dir115 + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + dir94 + "\t" + dir95 + "\t" + dir96 + "\t" + dir97 + "\t" + dir98 + "\t" + dir99 + "\t" + dir100 + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir80 + "\t" + dir81 + "\t" + dir82 + "\t" + dir83 + "\t" + dir84 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir66 + "\t" + dir67 + "\t" + dir68 + "\t" + "\t" + "\t" + "\t" + "\t" + "\t");

        if (target.distanceSquared(l112) <= 10) {
            int target_dx = target.x - l112.x;
            int target_dy = target.y - l112.y;
if (target_dx >= 0) {
    if (target_dx >= 3) {
        if (target_dx == 3) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d115); // destination is at relative location (3, 0)
        } else if (target_dy == 1) {
            return direction(d130); // destination is at relative location (3, 1)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d100); // destination is at relative location (3, -1)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == -3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dx == 0) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d157); // destination is at relative location (0, 3)
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d112); // destination is at relative location (0, 0)
        } else if (target_dy == 1) {
            return direction(d127); // destination is at relative location (0, 1)
        } else {
            return direction(d142); // destination is at relative location (0, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d97); // destination is at relative location (0, -1)
        } else {
            return direction(d82); // destination is at relative location (0, -2)
        }
    } else {
        if (target_dy == -3) {
            return direction(d67); // destination is at relative location (0, -3)
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == 1) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d158); // destination is at relative location (1, 3)
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d113); // destination is at relative location (1, 0)
        } else if (target_dy == 1) {
            return direction(d128); // destination is at relative location (1, 1)
        } else {
            return direction(d143); // destination is at relative location (1, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d98); // destination is at relative location (1, -1)
        } else {
            return direction(d83); // destination is at relative location (1, -2)
        }
    } else {
        if (target_dy == -3) {
            return direction(d68); // destination is at relative location (1, -3)
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d114); // destination is at relative location (2, 0)
        } else if (target_dy == 1) {
            return direction(d129); // destination is at relative location (2, 1)
        } else {
            return direction(d144); // destination is at relative location (2, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d99); // destination is at relative location (2, -1)
        } else {
            return direction(d84); // destination is at relative location (2, -2)
        }
    } else {
        if (target_dy == -3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        }
    }
} else {
    if (target_dx >= -2) {
        if (target_dx == -1) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d156); // destination is at relative location (-1, 3)
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d111); // destination is at relative location (-1, 0)
        } else if (target_dy == 1) {
            return direction(d126); // destination is at relative location (-1, 1)
        } else {
            return direction(d141); // destination is at relative location (-1, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d96); // destination is at relative location (-1, -1)
        } else {
            return direction(d81); // destination is at relative location (-1, -2)
        }
    } else {
        if (target_dy == -3) {
            return direction(d66); // destination is at relative location (-1, -3)
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d110); // destination is at relative location (-2, 0)
        } else if (target_dy == 1) {
            return direction(d125); // destination is at relative location (-2, 1)
        } else {
            return direction(d140); // destination is at relative location (-2, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d95); // destination is at relative location (-2, -1)
        } else {
            return direction(d80); // destination is at relative location (-2, -2)
        }
    } else {
        if (target_dy == -3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        }
    } else {
        if (target_dx == -3) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == 4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == 0) {
            return direction(d109); // destination is at relative location (-3, 0)
        } else if (target_dy == 1) {
            return direction(d124); // destination is at relative location (-3, 1)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d94); // destination is at relative location (-3, -1)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    } else {
        if (target_dy == -3) {
            uc.println("BFS: Invalid loc"); return null;
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        }
        
        ans = Double.POSITIVE_INFINITY;
        bestScore = 0;
        currDist = Math.sqrt(l112.distanceSquared(target));
        
        score94 = (currDist - Math.sqrt(l94.distanceSquared(target))) / d94;
        if (score94 > bestScore) {
            bestScore = score94;
            ans = d94;
        }

        score109 = (currDist - Math.sqrt(l109.distanceSquared(target))) / d109;
        if (score109 > bestScore) {
            bestScore = score109;
            ans = d109;
        }

        score124 = (currDist - Math.sqrt(l124.distanceSquared(target))) / d124;
        if (score124 > bestScore) {
            bestScore = score124;
            ans = d124;
        }

        score80 = (currDist - Math.sqrt(l80.distanceSquared(target))) / d80;
        if (score80 > bestScore) {
            bestScore = score80;
            ans = d80;
        }

        score140 = (currDist - Math.sqrt(l140.distanceSquared(target))) / d140;
        if (score140 > bestScore) {
            bestScore = score140;
            ans = d140;
        }

        score66 = (currDist - Math.sqrt(l66.distanceSquared(target))) / d66;
        if (score66 > bestScore) {
            bestScore = score66;
            ans = d66;
        }

        score156 = (currDist - Math.sqrt(l156.distanceSquared(target))) / d156;
        if (score156 > bestScore) {
            bestScore = score156;
            ans = d156;
        }

        score67 = (currDist - Math.sqrt(l67.distanceSquared(target))) / d67;
        if (score67 > bestScore) {
            bestScore = score67;
            ans = d67;
        }

        score157 = (currDist - Math.sqrt(l157.distanceSquared(target))) / d157;
        if (score157 > bestScore) {
            bestScore = score157;
            ans = d157;
        }

        score68 = (currDist - Math.sqrt(l68.distanceSquared(target))) / d68;
        if (score68 > bestScore) {
            bestScore = score68;
            ans = d68;
        }

        score158 = (currDist - Math.sqrt(l158.distanceSquared(target))) / d158;
        if (score158 > bestScore) {
            bestScore = score158;
            ans = d158;
        }

        score84 = (currDist - Math.sqrt(l84.distanceSquared(target))) / d84;
        if (score84 > bestScore) {
            bestScore = score84;
            ans = d84;
        }

        score144 = (currDist - Math.sqrt(l144.distanceSquared(target))) / d144;
        if (score144 > bestScore) {
            bestScore = score144;
            ans = d144;
        }

        score100 = (currDist - Math.sqrt(l100.distanceSquared(target))) / d100;
        if (score100 > bestScore) {
            bestScore = score100;
            ans = d100;
        }

        score115 = (currDist - Math.sqrt(l115.distanceSquared(target))) / d115;
        if (score115 > bestScore) {
            bestScore = score115;
            ans = d115;
        }

        score130 = (currDist - Math.sqrt(l130.distanceSquared(target))) / d130;
        if (score130 > bestScore) {
            bestScore = score130;
            ans = d130;
        }

        
        return direction(ans);
    }
}
