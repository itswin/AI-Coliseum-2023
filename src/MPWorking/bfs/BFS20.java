package MPWorking.bfs;

import aic2023.user.*;

public class BFS20 {

    public UnitController uc;

    Location l78; // location representing relative coordinate (-4, -2)
    double d78; // shortest distance to location from current location
    // Direction dir78; // best direction to take now to optimally get to location
    double score78; // heuristic distance from location to target

    Location l93; // location representing relative coordinate (-4, -1)
    double d93; // shortest distance to location from current location
    // Direction dir93; // best direction to take now to optimally get to location
    double score93; // heuristic distance from location to target

    Location l108; // location representing relative coordinate (-4, 0)
    double d108; // shortest distance to location from current location
    // Direction dir108; // best direction to take now to optimally get to location
    double score108; // heuristic distance from location to target

    Location l123; // location representing relative coordinate (-4, 1)
    double d123; // shortest distance to location from current location
    // Direction dir123; // best direction to take now to optimally get to location
    double score123; // heuristic distance from location to target

    Location l138; // location representing relative coordinate (-4, 2)
    double d138; // shortest distance to location from current location
    // Direction dir138; // best direction to take now to optimally get to location
    double score138; // heuristic distance from location to target

    Location l64; // location representing relative coordinate (-3, -3)
    double d64; // shortest distance to location from current location
    // Direction dir64; // best direction to take now to optimally get to location
    double score64; // heuristic distance from location to target

    Location l79; // location representing relative coordinate (-3, -2)
    double d79; // shortest distance to location from current location
    // Direction dir79; // best direction to take now to optimally get to location
    double score79; // heuristic distance from location to target

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

    Location l139; // location representing relative coordinate (-3, 2)
    double d139; // shortest distance to location from current location
    // Direction dir139; // best direction to take now to optimally get to location
    double score139; // heuristic distance from location to target

    Location l154; // location representing relative coordinate (-3, 3)
    double d154; // shortest distance to location from current location
    // Direction dir154; // best direction to take now to optimally get to location
    double score154; // heuristic distance from location to target

    Location l50; // location representing relative coordinate (-2, -4)
    double d50; // shortest distance to location from current location
    // Direction dir50; // best direction to take now to optimally get to location
    double score50; // heuristic distance from location to target

    Location l65; // location representing relative coordinate (-2, -3)
    double d65; // shortest distance to location from current location
    // Direction dir65; // best direction to take now to optimally get to location
    double score65; // heuristic distance from location to target

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

    Location l155; // location representing relative coordinate (-2, 3)
    double d155; // shortest distance to location from current location
    // Direction dir155; // best direction to take now to optimally get to location
    double score155; // heuristic distance from location to target

    Location l170; // location representing relative coordinate (-2, 4)
    double d170; // shortest distance to location from current location
    // Direction dir170; // best direction to take now to optimally get to location
    double score170; // heuristic distance from location to target

    Location l51; // location representing relative coordinate (-1, -4)
    double d51; // shortest distance to location from current location
    // Direction dir51; // best direction to take now to optimally get to location
    double score51; // heuristic distance from location to target

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

    Location l171; // location representing relative coordinate (-1, 4)
    double d171; // shortest distance to location from current location
    // Direction dir171; // best direction to take now to optimally get to location
    double score171; // heuristic distance from location to target

    Location l52; // location representing relative coordinate (0, -4)
    double d52; // shortest distance to location from current location
    // Direction dir52; // best direction to take now to optimally get to location
    double score52; // heuristic distance from location to target

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

    Location l172; // location representing relative coordinate (0, 4)
    double d172; // shortest distance to location from current location
    // Direction dir172; // best direction to take now to optimally get to location
    double score172; // heuristic distance from location to target

    Location l53; // location representing relative coordinate (1, -4)
    double d53; // shortest distance to location from current location
    // Direction dir53; // best direction to take now to optimally get to location
    double score53; // heuristic distance from location to target

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

    Location l173; // location representing relative coordinate (1, 4)
    double d173; // shortest distance to location from current location
    // Direction dir173; // best direction to take now to optimally get to location
    double score173; // heuristic distance from location to target

    Location l54; // location representing relative coordinate (2, -4)
    double d54; // shortest distance to location from current location
    // Direction dir54; // best direction to take now to optimally get to location
    double score54; // heuristic distance from location to target

    Location l69; // location representing relative coordinate (2, -3)
    double d69; // shortest distance to location from current location
    // Direction dir69; // best direction to take now to optimally get to location
    double score69; // heuristic distance from location to target

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

    Location l159; // location representing relative coordinate (2, 3)
    double d159; // shortest distance to location from current location
    // Direction dir159; // best direction to take now to optimally get to location
    double score159; // heuristic distance from location to target

    Location l174; // location representing relative coordinate (2, 4)
    double d174; // shortest distance to location from current location
    // Direction dir174; // best direction to take now to optimally get to location
    double score174; // heuristic distance from location to target

    Location l70; // location representing relative coordinate (3, -3)
    double d70; // shortest distance to location from current location
    // Direction dir70; // best direction to take now to optimally get to location
    double score70; // heuristic distance from location to target

    Location l85; // location representing relative coordinate (3, -2)
    double d85; // shortest distance to location from current location
    // Direction dir85; // best direction to take now to optimally get to location
    double score85; // heuristic distance from location to target

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

    Location l145; // location representing relative coordinate (3, 2)
    double d145; // shortest distance to location from current location
    // Direction dir145; // best direction to take now to optimally get to location
    double score145; // heuristic distance from location to target

    Location l160; // location representing relative coordinate (3, 3)
    double d160; // shortest distance to location from current location
    // Direction dir160; // best direction to take now to optimally get to location
    double score160; // heuristic distance from location to target

    Location l86; // location representing relative coordinate (4, -2)
    double d86; // shortest distance to location from current location
    // Direction dir86; // best direction to take now to optimally get to location
    double score86; // heuristic distance from location to target

    Location l101; // location representing relative coordinate (4, -1)
    double d101; // shortest distance to location from current location
    // Direction dir101; // best direction to take now to optimally get to location
    double score101; // heuristic distance from location to target

    Location l116; // location representing relative coordinate (4, 0)
    double d116; // shortest distance to location from current location
    // Direction dir116; // best direction to take now to optimally get to location
    double score116; // heuristic distance from location to target

    Location l131; // location representing relative coordinate (4, 1)
    double d131; // shortest distance to location from current location
    // Direction dir131; // best direction to take now to optimally get to location
    double score131; // heuristic distance from location to target

    Location l146; // location representing relative coordinate (4, 2)
    double d146; // shortest distance to location from current location
    // Direction dir146; // best direction to take now to optimally get to location
    double score146; // heuristic distance from location to target


    public BFS20(UnitController r) {
        uc = r;
    }

    private final Direction[] DIRECTIONS = new Direction[] {null, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHWEST, Direction.SOUTHEAST, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH};

    public final Direction NORTH = Direction.NORTH;
    public final Direction NORTHEAST = Direction.NORTHEAST;
    public final Direction EAST = Direction.EAST;
    public final Direction SOUTHEAST = Direction.SOUTHEAST;
    public final Direction SOUTH = Direction.SOUTH;
    public final Direction SOUTHWEST = Direction.SOUTHWEST;
    public final Direction WEST = Direction.WEST;
    public final Direction NORTHWEST = Direction.NORTHWEST;
    public final Direction ZERO = Direction.ZERO;

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

        l79 = l95.add(SOUTHWEST); // (-3, -2) from (-2, -1)
        d79 = 99999;
        // dir79 = null;

        l139 = l125.add(NORTHWEST); // (-3, 2) from (-2, 1)
        d139 = 99999;
        // dir139 = null;

        l65 = l81.add(SOUTHWEST); // (-2, -3) from (-1, -2)
        d65 = 99999;
        // dir65 = null;

        l155 = l141.add(NORTHWEST); // (-2, 3) from (-1, 2)
        d155 = 99999;
        // dir155 = null;

        l69 = l83.add(SOUTHEAST); // (2, -3) from (1, -2)
        d69 = 99999;
        // dir69 = null;

        l159 = l143.add(NORTHEAST); // (2, 3) from (1, 2)
        d159 = 99999;
        // dir159 = null;

        l85 = l99.add(SOUTHEAST); // (3, -2) from (2, -1)
        d85 = 99999;
        // dir85 = null;

        l145 = l129.add(NORTHEAST); // (3, 2) from (2, 1)
        d145 = 99999;
        // dir145 = null;

        l108 = l109.add(WEST); // (-4, 0) from (-3, 0)
        d108 = 99999;
        // dir108 = null;

        l52 = l67.add(SOUTH); // (0, -4) from (0, -3)
        d52 = 99999;
        // dir52 = null;

        l172 = l157.add(NORTH); // (0, 4) from (0, 3)
        d172 = 99999;
        // dir172 = null;

        l116 = l115.add(EAST); // (4, 0) from (3, 0)
        d116 = 99999;
        // dir116 = null;

        l93 = l109.add(SOUTHWEST); // (-4, -1) from (-3, 0)
        d93 = 99999;
        // dir93 = null;

        l123 = l109.add(NORTHWEST); // (-4, 1) from (-3, 0)
        d123 = 99999;
        // dir123 = null;

        l51 = l67.add(SOUTHWEST); // (-1, -4) from (0, -3)
        d51 = 99999;
        // dir51 = null;

        l171 = l157.add(NORTHWEST); // (-1, 4) from (0, 3)
        d171 = 99999;
        // dir171 = null;

        l53 = l67.add(SOUTHEAST); // (1, -4) from (0, -3)
        d53 = 99999;
        // dir53 = null;

        l173 = l157.add(NORTHEAST); // (1, 4) from (0, 3)
        d173 = 99999;
        // dir173 = null;

        l101 = l115.add(SOUTHEAST); // (4, -1) from (3, 0)
        d101 = 99999;
        // dir101 = null;

        l131 = l115.add(NORTHEAST); // (4, 1) from (3, 0)
        d131 = 99999;
        // dir131 = null;

        l64 = l80.add(SOUTHWEST); // (-3, -3) from (-2, -2)
        d64 = 99999;
        // dir64 = null;

        l154 = l140.add(NORTHWEST); // (-3, 3) from (-2, 2)
        d154 = 99999;
        // dir154 = null;

        l70 = l84.add(SOUTHEAST); // (3, -3) from (2, -2)
        d70 = 99999;
        // dir70 = null;

        l160 = l144.add(NORTHEAST); // (3, 3) from (2, 2)
        d160 = 99999;
        // dir160 = null;

        l78 = l94.add(SOUTHWEST); // (-4, -2) from (-3, -1)
        d78 = 99999;
        // dir78 = null;

        l138 = l124.add(NORTHWEST); // (-4, 2) from (-3, 1)
        d138 = 99999;
        // dir138 = null;

        l50 = l66.add(SOUTHWEST); // (-2, -4) from (-1, -3)
        d50 = 99999;
        // dir50 = null;

        l170 = l156.add(NORTHWEST); // (-2, 4) from (-1, 3)
        d170 = 99999;
        // dir170 = null;

        l54 = l68.add(SOUTHEAST); // (2, -4) from (1, -3)
        d54 = 99999;
        // dir54 = null;

        l174 = l158.add(NORTHEAST); // (2, 4) from (1, 3)
        d174 = 99999;
        // dir174 = null;

        l86 = l100.add(SOUTHEAST); // (4, -2) from (3, -1)
        d86 = 99999;
        // dir86 = null;

        l146 = l130.add(NORTHEAST); // (4, 2) from (3, 1)
        d146 = 99999;
        // dir146 = null;



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
            d96 = 10 + Math.min(0.4375, Math.min(d111, d97));
        }

        // check (-1, 1)
        if (uc.canSenseLocation(l126) && 
                (mapObj = uc.senseObjectAtLocation(l126, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d126 = 10 + Math.min(0.375, Math.min(d111, d127));
        }

        // check (1, -1)
        if (uc.canSenseLocation(l98) && 
                (mapObj = uc.senseObjectAtLocation(l98, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d98 = 10 + Math.min(0.5, Math.min(d97, d113));
        }

        // check (1, 1)
        if (uc.canSenseLocation(l128) && 
                (mapObj = uc.senseObjectAtLocation(l128, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d128 = 10 + Math.min(0.3125, Math.min(d127, d113));
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

        // check (-3, -2)
        if (uc.canSenseLocation(l79) && 
                (mapObj = uc.senseObjectAtLocation(l79, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d79 = 10 + Math.min(d95 + 4, Math.min(d80, d94));
        }

        // check (-3, 2)
        if (uc.canSenseLocation(l139) && 
                (mapObj = uc.senseObjectAtLocation(l139, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d139 = 10 + Math.min(d125 + 4, Math.min(d140, d124));
        }

        // check (-2, -3)
        if (uc.canSenseLocation(l65) && 
                (mapObj = uc.senseObjectAtLocation(l65, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d65 = 10 + Math.min(d81 + 4, Math.min(d80, Math.min(d66, d79 + 4)));
        }

        // check (-2, 3)
        if (uc.canSenseLocation(l155) && 
                (mapObj = uc.senseObjectAtLocation(l155, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d155 = 10 + Math.min(d141 + 4, Math.min(d140, Math.min(d156, d139 + 4)));
        }

        // check (2, -3)
        if (uc.canSenseLocation(l69) && 
                (mapObj = uc.senseObjectAtLocation(l69, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d69 = 10 + Math.min(d83 + 4, Math.min(d84, d68));
        }

        // check (2, 3)
        if (uc.canSenseLocation(l159) && 
                (mapObj = uc.senseObjectAtLocation(l159, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d159 = 10 + Math.min(d143 + 4, Math.min(d144, d158));
        }

        // check (3, -2)
        if (uc.canSenseLocation(l85) && 
                (mapObj = uc.senseObjectAtLocation(l85, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d85 = 10 + Math.min(d99 + 4, Math.min(d84, Math.min(d100, d69 + 4)));
        }

        // check (3, 2)
        if (uc.canSenseLocation(l145) && 
                (mapObj = uc.senseObjectAtLocation(l145, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d145 = 10 + Math.min(d129 + 4, Math.min(d144, Math.min(d130, d159 + 4)));
        }

        // check (-4, 0)
        if (uc.canSenseLocation(l108) && 
                (mapObj = uc.senseObjectAtLocation(l108, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d108 = 10 + Math.min(d109, Math.min(d94 + 4, d124 + 4));
        }

        // check (0, -4)
        if (uc.canSenseLocation(l52) && 
                (mapObj = uc.senseObjectAtLocation(l52, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d52 = 10 + Math.min(d67, Math.min(d66 + 4, d68 + 4));
        }

        // check (0, 4)
        if (uc.canSenseLocation(l172) && 
                (mapObj = uc.senseObjectAtLocation(l172, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d172 = 10 + Math.min(d157, Math.min(d156 + 4, d158 + 4));
        }

        // check (4, 0)
        if (uc.canSenseLocation(l116) && 
                (mapObj = uc.senseObjectAtLocation(l116, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d116 = 10 + Math.min(d115, Math.min(d100 + 4, d130 + 4));
        }

        // check (-4, -1)
        if (uc.canSenseLocation(l93) && 
                (mapObj = uc.senseObjectAtLocation(l93, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d93 = 10 + Math.min(d109 + 4, Math.min(d94, Math.min(d79 + 4, d108)));
        }

        // check (-4, 1)
        if (uc.canSenseLocation(l123) && 
                (mapObj = uc.senseObjectAtLocation(l123, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d123 = 10 + Math.min(d109 + 4, Math.min(d124, Math.min(d139 + 4, d108)));
        }

        // check (-1, -4)
        if (uc.canSenseLocation(l51) && 
                (mapObj = uc.senseObjectAtLocation(l51, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d51 = 10 + Math.min(d67 + 4, Math.min(d66, Math.min(d65 + 4, d52)));
        }

        // check (-1, 4)
        if (uc.canSenseLocation(l171) && 
                (mapObj = uc.senseObjectAtLocation(l171, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d171 = 10 + Math.min(d157 + 4, Math.min(d156, Math.min(d155 + 4, d172)));
        }

        // check (1, -4)
        if (uc.canSenseLocation(l53) && 
                (mapObj = uc.senseObjectAtLocation(l53, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d53 = 10 + Math.min(d67 + 4, Math.min(d68, Math.min(d69 + 4, d52)));
        }

        // check (1, 4)
        if (uc.canSenseLocation(l173) && 
                (mapObj = uc.senseObjectAtLocation(l173, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d173 = 10 + Math.min(d157 + 4, Math.min(d158, Math.min(d159 + 4, d172)));
        }

        // check (4, -1)
        if (uc.canSenseLocation(l101) && 
                (mapObj = uc.senseObjectAtLocation(l101, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d101 = 10 + Math.min(d115 + 4, Math.min(d100, Math.min(d85 + 4, d116)));
        }

        // check (4, 1)
        if (uc.canSenseLocation(l131) && 
                (mapObj = uc.senseObjectAtLocation(l131, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d131 = 10 + Math.min(d115 + 4, Math.min(d130, Math.min(d145 + 4, d116)));
        }

        // check (-3, -3)
        if (uc.canSenseLocation(l64) && 
                (mapObj = uc.senseObjectAtLocation(l64, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d64 = 10 + Math.min(d80 + 4, Math.min(d79, d65));
        }

        // check (-3, 3)
        if (uc.canSenseLocation(l154) && 
                (mapObj = uc.senseObjectAtLocation(l154, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d154 = 10 + Math.min(d140 + 4, Math.min(d139, d155));
        }

        // check (3, -3)
        if (uc.canSenseLocation(l70) && 
                (mapObj = uc.senseObjectAtLocation(l70, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d70 = 10 + Math.min(d84 + 4, Math.min(d69, d85));
        }

        // check (3, 3)
        if (uc.canSenseLocation(l160) && 
                (mapObj = uc.senseObjectAtLocation(l160, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d160 = 10 + Math.min(d144 + 4, Math.min(d159, d145));
        }

        // check (-4, -2)
        if (uc.canSenseLocation(l78) && 
                (mapObj = uc.senseObjectAtLocation(l78, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d78 = 10 + Math.min(d94 + 4, Math.min(d79, Math.min(d93, d64 + 4)));
        }

        // check (-4, 2)
        if (uc.canSenseLocation(l138) && 
                (mapObj = uc.senseObjectAtLocation(l138, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d138 = 10 + Math.min(d124 + 4, Math.min(d139, Math.min(d123, d154 + 4)));
        }

        // check (-2, -4)
        if (uc.canSenseLocation(l50) && 
                (mapObj = uc.senseObjectAtLocation(l50, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d50 = 10 + Math.min(d66 + 4, Math.min(d65, Math.min(d51, d64 + 4)));
        }

        // check (-2, 4)
        if (uc.canSenseLocation(l170) && 
                (mapObj = uc.senseObjectAtLocation(l170, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d170 = 10 + Math.min(d156 + 4, Math.min(d155, Math.min(d171, d154 + 4)));
        }

        // check (2, -4)
        if (uc.canSenseLocation(l54) && 
                (mapObj = uc.senseObjectAtLocation(l54, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d54 = 10 + Math.min(d68 + 4, Math.min(d69, Math.min(d53, d70 + 4)));
        }

        // check (2, 4)
        if (uc.canSenseLocation(l174) && 
                (mapObj = uc.senseObjectAtLocation(l174, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d174 = 10 + Math.min(d158 + 4, Math.min(d159, Math.min(d173, d160 + 4)));
        }

        // check (4, -2)
        if (uc.canSenseLocation(l86) && 
                (mapObj = uc.senseObjectAtLocation(l86, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d86 = 10 + Math.min(d100 + 4, Math.min(d85, Math.min(d101, d70 + 4)));
        }

        // check (4, 2)
        if (uc.canSenseLocation(l146) && 
                (mapObj = uc.senseObjectAtLocation(l146, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d146 = 10 + Math.min(d130 + 4, Math.min(d145, Math.min(d131, d160 + 4)));
        }


        // System.out.println("LOCAL DISTANCES:");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d170 + "\t" + d171 + "\t" + d172 + "\t" + d173 + "\t" + d174 + "\t" + "\t" + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + d154 + "\t" + d155 + "\t" + d156 + "\t" + d157 + "\t" + d158 + "\t" + d159 + "\t" + d160 + "\t" + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + d138 + "\t" + d139 + "\t" + d140 + "\t" + d141 + "\t" + d142 + "\t" + d143 + "\t" + d144 + "\t" + d145 + "\t" + d146 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + d123 + "\t" + d124 + "\t" + d125 + "\t" + d126 + "\t" + d127 + "\t" + d128 + "\t" + d129 + "\t" + d130 + "\t" + d131 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + d108 + "\t" + d109 + "\t" + d110 + "\t" + d111 + "\t" + d112 + "\t" + d113 + "\t" + d114 + "\t" + d115 + "\t" + d116 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + d93 + "\t" + d94 + "\t" + d95 + "\t" + d96 + "\t" + d97 + "\t" + d98 + "\t" + d99 + "\t" + d100 + "\t" + d101 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + d78 + "\t" + d79 + "\t" + d80 + "\t" + d81 + "\t" + d82 + "\t" + d83 + "\t" + d84 + "\t" + d85 + "\t" + d86 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + d64 + "\t" + d65 + "\t" + d66 + "\t" + d67 + "\t" + d68 + "\t" + d69 + "\t" + d70 + "\t" + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d50 + "\t" + d51 + "\t" + d52 + "\t" + d53 + "\t" + d54 + "\t" + "\t" + "\t" + "\t" + "\t");
        // System.out.println("DIRECTIONS:");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir170 + "\t" + dir171 + "\t" + dir172 + "\t" + dir173 + "\t" + dir174 + "\t" + "\t" + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + dir154 + "\t" + dir155 + "\t" + dir156 + "\t" + dir157 + "\t" + dir158 + "\t" + dir159 + "\t" + dir160 + "\t" + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + dir138 + "\t" + dir139 + "\t" + dir140 + "\t" + dir141 + "\t" + dir142 + "\t" + dir143 + "\t" + dir144 + "\t" + dir145 + "\t" + dir146 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + dir123 + "\t" + dir124 + "\t" + dir125 + "\t" + dir126 + "\t" + dir127 + "\t" + dir128 + "\t" + dir129 + "\t" + dir130 + "\t" + dir131 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + dir108 + "\t" + dir109 + "\t" + dir110 + "\t" + dir111 + "\t" + dir112 + "\t" + dir113 + "\t" + dir114 + "\t" + dir115 + "\t" + dir116 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + dir93 + "\t" + dir94 + "\t" + dir95 + "\t" + dir96 + "\t" + dir97 + "\t" + dir98 + "\t" + dir99 + "\t" + dir100 + "\t" + dir101 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + dir78 + "\t" + dir79 + "\t" + dir80 + "\t" + dir81 + "\t" + dir82 + "\t" + dir83 + "\t" + dir84 + "\t" + dir85 + "\t" + dir86 + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + dir64 + "\t" + dir65 + "\t" + dir66 + "\t" + dir67 + "\t" + dir68 + "\t" + dir69 + "\t" + dir70 + "\t" + "\t" + "\t" + "\t");
        // System.out.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir50 + "\t" + dir51 + "\t" + dir52 + "\t" + dir53 + "\t" + dir54 + "\t" + "\t" + "\t" + "\t" + "\t");

        if (target.distanceSquared(l112) <= 20) {
            int target_dx = target.x - l112.x;
            int target_dy = target.y - l112.y;
if (target_dx >= 0) {
    if (target_dx >= 3) {
        if (target_dx == 3) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d160); // destination is at relative location (3, 3)
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
            return direction(d145); // destination is at relative location (3, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d100); // destination is at relative location (3, -1)
        } else {
            return direction(d85); // destination is at relative location (3, -2)
        }
    } else {
        if (target_dy == -3) {
            return direction(d70); // destination is at relative location (3, -3)
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == 4) {
            
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
            return direction(d116); // destination is at relative location (4, 0)
        } else if (target_dy == 1) {
            return direction(d131); // destination is at relative location (4, 1)
        } else {
            return direction(d146); // destination is at relative location (4, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d101); // destination is at relative location (4, -1)
        } else {
            return direction(d86); // destination is at relative location (4, -2)
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
            return direction(d172); // destination is at relative location (0, 4)
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
            return direction(d52); // destination is at relative location (0, -4)
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
            return direction(d173); // destination is at relative location (1, 4)
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
            return direction(d53); // destination is at relative location (1, -4)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d159); // destination is at relative location (2, 3)
        } else if (target_dy == 4) {
            return direction(d174); // destination is at relative location (2, 4)
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
            return direction(d69); // destination is at relative location (2, -3)
        } else if (target_dy == -4) {
            return direction(d54); // destination is at relative location (2, -4)
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
            return direction(d171); // destination is at relative location (-1, 4)
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
            return direction(d51); // destination is at relative location (-1, -4)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d155); // destination is at relative location (-2, 3)
        } else if (target_dy == 4) {
            return direction(d170); // destination is at relative location (-2, 4)
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
            return direction(d65); // destination is at relative location (-2, -3)
        } else if (target_dy == -4) {
            return direction(d50); // destination is at relative location (-2, -4)
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
            return direction(d154); // destination is at relative location (-3, 3)
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
            return direction(d139); // destination is at relative location (-3, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d94); // destination is at relative location (-3, -1)
        } else {
            return direction(d79); // destination is at relative location (-3, -2)
        }
    } else {
        if (target_dy == -3) {
            return direction(d64); // destination is at relative location (-3, -3)
        } else if (target_dy == -4) {
            uc.println("BFS: Invalid loc"); return null;
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == -4) {
            
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
            return direction(d108); // destination is at relative location (-4, 0)
        } else if (target_dy == 1) {
            return direction(d123); // destination is at relative location (-4, 1)
        } else {
            return direction(d138); // destination is at relative location (-4, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d93); // destination is at relative location (-4, -1)
        } else {
            return direction(d78); // destination is at relative location (-4, -2)
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

        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        }
        
        ans = Double.POSITIVE_INFINITY;
        bestScore = 0;
        currDist = Math.sqrt(l112.distanceSquared(target));
        
        score78 = (currDist - Math.sqrt(l78.distanceSquared(target))) / d78;
        if (score78 > bestScore) {
            bestScore = score78;
            ans = d78;
        }

        score93 = (currDist - Math.sqrt(l93.distanceSquared(target))) / d93;
        if (score93 > bestScore) {
            bestScore = score93;
            ans = d93;
        }

        score108 = (currDist - Math.sqrt(l108.distanceSquared(target))) / d108;
        if (score108 > bestScore) {
            bestScore = score108;
            ans = d108;
        }

        score123 = (currDist - Math.sqrt(l123.distanceSquared(target))) / d123;
        if (score123 > bestScore) {
            bestScore = score123;
            ans = d123;
        }

        score138 = (currDist - Math.sqrt(l138.distanceSquared(target))) / d138;
        if (score138 > bestScore) {
            bestScore = score138;
            ans = d138;
        }

        score64 = (currDist - Math.sqrt(l64.distanceSquared(target))) / d64;
        if (score64 > bestScore) {
            bestScore = score64;
            ans = d64;
        }

        score79 = (currDist - Math.sqrt(l79.distanceSquared(target))) / d79;
        if (score79 > bestScore) {
            bestScore = score79;
            ans = d79;
        }

        score139 = (currDist - Math.sqrt(l139.distanceSquared(target))) / d139;
        if (score139 > bestScore) {
            bestScore = score139;
            ans = d139;
        }

        score154 = (currDist - Math.sqrt(l154.distanceSquared(target))) / d154;
        if (score154 > bestScore) {
            bestScore = score154;
            ans = d154;
        }

        score50 = (currDist - Math.sqrt(l50.distanceSquared(target))) / d50;
        if (score50 > bestScore) {
            bestScore = score50;
            ans = d50;
        }

        score65 = (currDist - Math.sqrt(l65.distanceSquared(target))) / d65;
        if (score65 > bestScore) {
            bestScore = score65;
            ans = d65;
        }

        score155 = (currDist - Math.sqrt(l155.distanceSquared(target))) / d155;
        if (score155 > bestScore) {
            bestScore = score155;
            ans = d155;
        }

        score170 = (currDist - Math.sqrt(l170.distanceSquared(target))) / d170;
        if (score170 > bestScore) {
            bestScore = score170;
            ans = d170;
        }

        score51 = (currDist - Math.sqrt(l51.distanceSquared(target))) / d51;
        if (score51 > bestScore) {
            bestScore = score51;
            ans = d51;
        }

        score171 = (currDist - Math.sqrt(l171.distanceSquared(target))) / d171;
        if (score171 > bestScore) {
            bestScore = score171;
            ans = d171;
        }

        score52 = (currDist - Math.sqrt(l52.distanceSquared(target))) / d52;
        if (score52 > bestScore) {
            bestScore = score52;
            ans = d52;
        }

        score172 = (currDist - Math.sqrt(l172.distanceSquared(target))) / d172;
        if (score172 > bestScore) {
            bestScore = score172;
            ans = d172;
        }

        score53 = (currDist - Math.sqrt(l53.distanceSquared(target))) / d53;
        if (score53 > bestScore) {
            bestScore = score53;
            ans = d53;
        }

        score173 = (currDist - Math.sqrt(l173.distanceSquared(target))) / d173;
        if (score173 > bestScore) {
            bestScore = score173;
            ans = d173;
        }

        score54 = (currDist - Math.sqrt(l54.distanceSquared(target))) / d54;
        if (score54 > bestScore) {
            bestScore = score54;
            ans = d54;
        }

        score69 = (currDist - Math.sqrt(l69.distanceSquared(target))) / d69;
        if (score69 > bestScore) {
            bestScore = score69;
            ans = d69;
        }

        score159 = (currDist - Math.sqrt(l159.distanceSquared(target))) / d159;
        if (score159 > bestScore) {
            bestScore = score159;
            ans = d159;
        }

        score174 = (currDist - Math.sqrt(l174.distanceSquared(target))) / d174;
        if (score174 > bestScore) {
            bestScore = score174;
            ans = d174;
        }

        score70 = (currDist - Math.sqrt(l70.distanceSquared(target))) / d70;
        if (score70 > bestScore) {
            bestScore = score70;
            ans = d70;
        }

        score85 = (currDist - Math.sqrt(l85.distanceSquared(target))) / d85;
        if (score85 > bestScore) {
            bestScore = score85;
            ans = d85;
        }

        score145 = (currDist - Math.sqrt(l145.distanceSquared(target))) / d145;
        if (score145 > bestScore) {
            bestScore = score145;
            ans = d145;
        }

        score160 = (currDist - Math.sqrt(l160.distanceSquared(target))) / d160;
        if (score160 > bestScore) {
            bestScore = score160;
            ans = d160;
        }

        score86 = (currDist - Math.sqrt(l86.distanceSquared(target))) / d86;
        if (score86 > bestScore) {
            bestScore = score86;
            ans = d86;
        }

        score101 = (currDist - Math.sqrt(l101.distanceSquared(target))) / d101;
        if (score101 > bestScore) {
            bestScore = score101;
            ans = d101;
        }

        score116 = (currDist - Math.sqrt(l116.distanceSquared(target))) / d116;
        if (score116 > bestScore) {
            bestScore = score116;
            ans = d116;
        }

        score131 = (currDist - Math.sqrt(l131.distanceSquared(target))) / d131;
        if (score131 > bestScore) {
            bestScore = score131;
            ans = d131;
        }

        score146 = (currDist - Math.sqrt(l146.distanceSquared(target))) / d146;
        if (score146 > bestScore) {
            bestScore = score146;
            ans = d146;
        }

        
        return direction(ans);
    }
}
