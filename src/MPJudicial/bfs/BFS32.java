package MPJudicial.bfs;

import aic2023.user.*;

public class BFS32 {

    public UnitController uc;

    Location l77; // location representing relative coordinate (-5, -2)
    double d77; // shortest distance to location from current location
    // Direction dir77; // best direction to take now to optimally get to location
    double score77; // heuristic distance from location to target

    Location l92; // location representing relative coordinate (-5, -1)
    double d92; // shortest distance to location from current location
    // Direction dir92; // best direction to take now to optimally get to location
    double score92; // heuristic distance from location to target

    Location l107; // location representing relative coordinate (-5, 0)
    double d107; // shortest distance to location from current location
    // Direction dir107; // best direction to take now to optimally get to location
    double score107; // heuristic distance from location to target

    Location l122; // location representing relative coordinate (-5, 1)
    double d122; // shortest distance to location from current location
    // Direction dir122; // best direction to take now to optimally get to location
    double score122; // heuristic distance from location to target

    Location l137; // location representing relative coordinate (-5, 2)
    double d137; // shortest distance to location from current location
    // Direction dir137; // best direction to take now to optimally get to location
    double score137; // heuristic distance from location to target

    Location l48; // location representing relative coordinate (-4, -4)
    double d48; // shortest distance to location from current location
    // Direction dir48; // best direction to take now to optimally get to location
    double score48; // heuristic distance from location to target

    Location l63; // location representing relative coordinate (-4, -3)
    double d63; // shortest distance to location from current location
    // Direction dir63; // best direction to take now to optimally get to location
    double score63; // heuristic distance from location to target

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

    Location l153; // location representing relative coordinate (-4, 3)
    double d153; // shortest distance to location from current location
    // Direction dir153; // best direction to take now to optimally get to location
    double score153; // heuristic distance from location to target

    Location l168; // location representing relative coordinate (-4, 4)
    double d168; // shortest distance to location from current location
    // Direction dir168; // best direction to take now to optimally get to location
    double score168; // heuristic distance from location to target

    Location l49; // location representing relative coordinate (-3, -4)
    double d49; // shortest distance to location from current location
    // Direction dir49; // best direction to take now to optimally get to location
    double score49; // heuristic distance from location to target

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

    Location l169; // location representing relative coordinate (-3, 4)
    double d169; // shortest distance to location from current location
    // Direction dir169; // best direction to take now to optimally get to location
    double score169; // heuristic distance from location to target

    Location l35; // location representing relative coordinate (-2, -5)
    double d35; // shortest distance to location from current location
    // Direction dir35; // best direction to take now to optimally get to location
    double score35; // heuristic distance from location to target

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

    Location l185; // location representing relative coordinate (-2, 5)
    double d185; // shortest distance to location from current location
    // Direction dir185; // best direction to take now to optimally get to location
    double score185; // heuristic distance from location to target

    Location l36; // location representing relative coordinate (-1, -5)
    double d36; // shortest distance to location from current location
    // Direction dir36; // best direction to take now to optimally get to location
    double score36; // heuristic distance from location to target

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

    Location l186; // location representing relative coordinate (-1, 5)
    double d186; // shortest distance to location from current location
    // Direction dir186; // best direction to take now to optimally get to location
    double score186; // heuristic distance from location to target

    Location l37; // location representing relative coordinate (0, -5)
    double d37; // shortest distance to location from current location
    // Direction dir37; // best direction to take now to optimally get to location
    double score37; // heuristic distance from location to target

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

    Location l187; // location representing relative coordinate (0, 5)
    double d187; // shortest distance to location from current location
    // Direction dir187; // best direction to take now to optimally get to location
    double score187; // heuristic distance from location to target

    Location l38; // location representing relative coordinate (1, -5)
    double d38; // shortest distance to location from current location
    // Direction dir38; // best direction to take now to optimally get to location
    double score38; // heuristic distance from location to target

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

    Location l188; // location representing relative coordinate (1, 5)
    double d188; // shortest distance to location from current location
    // Direction dir188; // best direction to take now to optimally get to location
    double score188; // heuristic distance from location to target

    Location l39; // location representing relative coordinate (2, -5)
    double d39; // shortest distance to location from current location
    // Direction dir39; // best direction to take now to optimally get to location
    double score39; // heuristic distance from location to target

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

    Location l189; // location representing relative coordinate (2, 5)
    double d189; // shortest distance to location from current location
    // Direction dir189; // best direction to take now to optimally get to location
    double score189; // heuristic distance from location to target

    Location l55; // location representing relative coordinate (3, -4)
    double d55; // shortest distance to location from current location
    // Direction dir55; // best direction to take now to optimally get to location
    double score55; // heuristic distance from location to target

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

    Location l175; // location representing relative coordinate (3, 4)
    double d175; // shortest distance to location from current location
    // Direction dir175; // best direction to take now to optimally get to location
    double score175; // heuristic distance from location to target

    Location l56; // location representing relative coordinate (4, -4)
    double d56; // shortest distance to location from current location
    // Direction dir56; // best direction to take now to optimally get to location
    double score56; // heuristic distance from location to target

    Location l71; // location representing relative coordinate (4, -3)
    double d71; // shortest distance to location from current location
    // Direction dir71; // best direction to take now to optimally get to location
    double score71; // heuristic distance from location to target

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

    Location l161; // location representing relative coordinate (4, 3)
    double d161; // shortest distance to location from current location
    // Direction dir161; // best direction to take now to optimally get to location
    double score161; // heuristic distance from location to target

    Location l176; // location representing relative coordinate (4, 4)
    double d176; // shortest distance to location from current location
    // Direction dir176; // best direction to take now to optimally get to location
    double score176; // heuristic distance from location to target

    Location l87; // location representing relative coordinate (5, -2)
    double d87; // shortest distance to location from current location
    // Direction dir87; // best direction to take now to optimally get to location
    double score87; // heuristic distance from location to target

    Location l102; // location representing relative coordinate (5, -1)
    double d102; // shortest distance to location from current location
    // Direction dir102; // best direction to take now to optimally get to location
    double score102; // heuristic distance from location to target

    Location l117; // location representing relative coordinate (5, 0)
    double d117; // shortest distance to location from current location
    // Direction dir117; // best direction to take now to optimally get to location
    double score117; // heuristic distance from location to target

    Location l132; // location representing relative coordinate (5, 1)
    double d132; // shortest distance to location from current location
    // Direction dir132; // best direction to take now to optimally get to location
    double score132; // heuristic distance from location to target

    Location l147; // location representing relative coordinate (5, 2)
    double d147; // shortest distance to location from current location
    // Direction dir147; // best direction to take now to optimally get to location
    double score147; // heuristic distance from location to target


    public BFS32(UnitController r) {
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
    public boolean hasCalced = false;

    public Direction direction(double dist) {
        if (dist==Double.POSITIVE_INFINITY) {
            return null;
        }
        return DIRECTIONS[(int)(dist * 16 % 16)];
    }

    public void init() {
        hasCalced = false;
    }

    public Direction bestDir(Location target) {
        if (hasCalced) {
            return dirTo(target);
        }

        hasCalced = true;

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

        l107 = l108.add(WEST); // (-5, 0) from (-4, 0)
        d107 = 99999;
        // dir107 = null;

        l63 = l79.add(SOUTHWEST); // (-4, -3) from (-3, -2)
        d63 = 99999;
        // dir63 = null;

        l153 = l139.add(NORTHWEST); // (-4, 3) from (-3, 2)
        d153 = 99999;
        // dir153 = null;

        l49 = l65.add(SOUTHWEST); // (-3, -4) from (-2, -3)
        d49 = 99999;
        // dir49 = null;

        l169 = l155.add(NORTHWEST); // (-3, 4) from (-2, 3)
        d169 = 99999;
        // dir169 = null;

        l37 = l52.add(SOUTH); // (0, -5) from (0, -4)
        d37 = 99999;
        // dir37 = null;

        l187 = l172.add(NORTH); // (0, 5) from (0, 4)
        d187 = 99999;
        // dir187 = null;

        l55 = l69.add(SOUTHEAST); // (3, -4) from (2, -3)
        d55 = 99999;
        // dir55 = null;

        l175 = l159.add(NORTHEAST); // (3, 4) from (2, 3)
        d175 = 99999;
        // dir175 = null;

        l71 = l85.add(SOUTHEAST); // (4, -3) from (3, -2)
        d71 = 99999;
        // dir71 = null;

        l161 = l145.add(NORTHEAST); // (4, 3) from (3, 2)
        d161 = 99999;
        // dir161 = null;

        l117 = l116.add(EAST); // (5, 0) from (4, 0)
        d117 = 99999;
        // dir117 = null;

        l92 = l108.add(SOUTHWEST); // (-5, -1) from (-4, 0)
        d92 = 99999;
        // dir92 = null;

        l122 = l108.add(NORTHWEST); // (-5, 1) from (-4, 0)
        d122 = 99999;
        // dir122 = null;

        l36 = l52.add(SOUTHWEST); // (-1, -5) from (0, -4)
        d36 = 99999;
        // dir36 = null;

        l186 = l172.add(NORTHWEST); // (-1, 5) from (0, 4)
        d186 = 99999;
        // dir186 = null;

        l38 = l52.add(SOUTHEAST); // (1, -5) from (0, -4)
        d38 = 99999;
        // dir38 = null;

        l188 = l172.add(NORTHEAST); // (1, 5) from (0, 4)
        d188 = 99999;
        // dir188 = null;

        l102 = l116.add(SOUTHEAST); // (5, -1) from (4, 0)
        d102 = 99999;
        // dir102 = null;

        l132 = l116.add(NORTHEAST); // (5, 1) from (4, 0)
        d132 = 99999;
        // dir132 = null;

        l77 = l93.add(SOUTHWEST); // (-5, -2) from (-4, -1)
        d77 = 99999;
        // dir77 = null;

        l137 = l123.add(NORTHWEST); // (-5, 2) from (-4, 1)
        d137 = 99999;
        // dir137 = null;

        l35 = l51.add(SOUTHWEST); // (-2, -5) from (-1, -4)
        d35 = 99999;
        // dir35 = null;

        l185 = l171.add(NORTHWEST); // (-2, 5) from (-1, 4)
        d185 = 99999;
        // dir185 = null;

        l39 = l53.add(SOUTHEAST); // (2, -5) from (1, -4)
        d39 = 99999;
        // dir39 = null;

        l189 = l173.add(NORTHEAST); // (2, 5) from (1, 4)
        d189 = 99999;
        // dir189 = null;

        l87 = l101.add(SOUTHEAST); // (5, -2) from (4, -1)
        d87 = 99999;
        // dir87 = null;

        l147 = l131.add(NORTHEAST); // (5, 2) from (4, 1)
        d147 = 99999;
        // dir147 = null;

        l48 = l64.add(SOUTHWEST); // (-4, -4) from (-3, -3)
        d48 = 99999;
        // dir48 = null;

        l168 = l154.add(NORTHWEST); // (-4, 4) from (-3, 3)
        d168 = 99999;
        // dir168 = null;

        l56 = l70.add(SOUTHEAST); // (4, -4) from (3, -3)
        d56 = 99999;
        // dir56 = null;

        l176 = l160.add(NORTHEAST); // (4, 4) from (3, 3)
        d176 = 99999;
        // dir176 = null;



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

        // check (-5, 0)
        if (uc.canSenseLocation(l107) && 
                (mapObj = uc.senseObjectAtLocation(l107, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d107 = 10 + Math.min(d108, Math.min(d93 + 4, d123 + 4));
        }

        // check (-4, -3)
        if (uc.canSenseLocation(l63) && 
                (mapObj = uc.senseObjectAtLocation(l63, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d63 = 10 + Math.min(d79 + 4, Math.min(d64, d78));
        }

        // check (-4, 3)
        if (uc.canSenseLocation(l153) && 
                (mapObj = uc.senseObjectAtLocation(l153, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d153 = 10 + Math.min(d139 + 4, Math.min(d154, d138));
        }

        // check (-3, -4)
        if (uc.canSenseLocation(l49) && 
                (mapObj = uc.senseObjectAtLocation(l49, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d49 = 10 + Math.min(d65 + 4, Math.min(d64, Math.min(d50, d63 + 4)));
        }

        // check (-3, 4)
        if (uc.canSenseLocation(l169) && 
                (mapObj = uc.senseObjectAtLocation(l169, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d169 = 10 + Math.min(d155 + 4, Math.min(d154, Math.min(d170, d153 + 4)));
        }

        // check (0, -5)
        if (uc.canSenseLocation(l37) && 
                (mapObj = uc.senseObjectAtLocation(l37, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d37 = 10 + Math.min(d52, Math.min(d51 + 4, d53 + 4));
        }

        // check (0, 5)
        if (uc.canSenseLocation(l187) && 
                (mapObj = uc.senseObjectAtLocation(l187, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d187 = 10 + Math.min(d172, Math.min(d171 + 4, d173 + 4));
        }

        // check (3, -4)
        if (uc.canSenseLocation(l55) && 
                (mapObj = uc.senseObjectAtLocation(l55, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d55 = 10 + Math.min(d69 + 4, Math.min(d70, d54));
        }

        // check (3, 4)
        if (uc.canSenseLocation(l175) && 
                (mapObj = uc.senseObjectAtLocation(l175, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d175 = 10 + Math.min(d159 + 4, Math.min(d160, d174));
        }

        // check (4, -3)
        if (uc.canSenseLocation(l71) && 
                (mapObj = uc.senseObjectAtLocation(l71, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d71 = 10 + Math.min(d85 + 4, Math.min(d70, Math.min(d86, d55 + 4)));
        }

        // check (4, 3)
        if (uc.canSenseLocation(l161) && 
                (mapObj = uc.senseObjectAtLocation(l161, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d161 = 10 + Math.min(d145 + 4, Math.min(d160, Math.min(d146, d175 + 4)));
        }

        // check (5, 0)
        if (uc.canSenseLocation(l117) && 
                (mapObj = uc.senseObjectAtLocation(l117, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d117 = 10 + Math.min(d116, Math.min(d101 + 4, d131 + 4));
        }

        // check (-5, -1)
        if (uc.canSenseLocation(l92) && 
                (mapObj = uc.senseObjectAtLocation(l92, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d92 = 10 + Math.min(d108 + 4, Math.min(d93, Math.min(d78 + 4, d107)));
        }

        // check (-5, 1)
        if (uc.canSenseLocation(l122) && 
                (mapObj = uc.senseObjectAtLocation(l122, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d122 = 10 + Math.min(d108 + 4, Math.min(d123, Math.min(d138 + 4, d107)));
        }

        // check (-1, -5)
        if (uc.canSenseLocation(l36) && 
                (mapObj = uc.senseObjectAtLocation(l36, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d36 = 10 + Math.min(d52 + 4, Math.min(d51, Math.min(d50 + 4, d37)));
        }

        // check (-1, 5)
        if (uc.canSenseLocation(l186) && 
                (mapObj = uc.senseObjectAtLocation(l186, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d186 = 10 + Math.min(d172 + 4, Math.min(d171, Math.min(d170 + 4, d187)));
        }

        // check (1, -5)
        if (uc.canSenseLocation(l38) && 
                (mapObj = uc.senseObjectAtLocation(l38, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d38 = 10 + Math.min(d52 + 4, Math.min(d53, Math.min(d54 + 4, d37)));
        }

        // check (1, 5)
        if (uc.canSenseLocation(l188) && 
                (mapObj = uc.senseObjectAtLocation(l188, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d188 = 10 + Math.min(d172 + 4, Math.min(d173, Math.min(d174 + 4, d187)));
        }

        // check (5, -1)
        if (uc.canSenseLocation(l102) && 
                (mapObj = uc.senseObjectAtLocation(l102, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d102 = 10 + Math.min(d116 + 4, Math.min(d101, Math.min(d86 + 4, d117)));
        }

        // check (5, 1)
        if (uc.canSenseLocation(l132) && 
                (mapObj = uc.senseObjectAtLocation(l132, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d132 = 10 + Math.min(d116 + 4, Math.min(d131, Math.min(d146 + 4, d117)));
        }

        // check (-5, -2)
        if (uc.canSenseLocation(l77) && 
                (mapObj = uc.senseObjectAtLocation(l77, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d77 = 10 + Math.min(d93 + 4, Math.min(d78, Math.min(d63 + 4, d92)));
        }

        // check (-5, 2)
        if (uc.canSenseLocation(l137) && 
                (mapObj = uc.senseObjectAtLocation(l137, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d137 = 10 + Math.min(d123 + 4, Math.min(d138, Math.min(d153 + 4, d122)));
        }

        // check (-2, -5)
        if (uc.canSenseLocation(l35) && 
                (mapObj = uc.senseObjectAtLocation(l35, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d35 = 10 + Math.min(d51 + 4, Math.min(d50, Math.min(d49 + 4, d36)));
        }

        // check (-2, 5)
        if (uc.canSenseLocation(l185) && 
                (mapObj = uc.senseObjectAtLocation(l185, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d185 = 10 + Math.min(d171 + 4, Math.min(d170, Math.min(d169 + 4, d186)));
        }

        // check (2, -5)
        if (uc.canSenseLocation(l39) && 
                (mapObj = uc.senseObjectAtLocation(l39, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d39 = 10 + Math.min(d53 + 4, Math.min(d54, Math.min(d55 + 4, d38)));
        }

        // check (2, 5)
        if (uc.canSenseLocation(l189) && 
                (mapObj = uc.senseObjectAtLocation(l189, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d189 = 10 + Math.min(d173 + 4, Math.min(d174, Math.min(d175 + 4, d188)));
        }

        // check (5, -2)
        if (uc.canSenseLocation(l87) && 
                (mapObj = uc.senseObjectAtLocation(l87, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d87 = 10 + Math.min(d101 + 4, Math.min(d86, Math.min(d71 + 4, d102)));
        }

        // check (5, 2)
        if (uc.canSenseLocation(l147) && 
                (mapObj = uc.senseObjectAtLocation(l147, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d147 = 10 + Math.min(d131 + 4, Math.min(d146, Math.min(d161 + 4, d132)));
        }

        // check (-4, -4)
        if (uc.canSenseLocation(l48) && 
                (mapObj = uc.senseObjectAtLocation(l48, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d48 = 10 + Math.min(d64 + 4, Math.min(d63, d49));
        }

        // check (-4, 4)
        if (uc.canSenseLocation(l168) && 
                (mapObj = uc.senseObjectAtLocation(l168, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d168 = 10 + Math.min(d154 + 4, Math.min(d153, d169));
        }

        // check (4, -4)
        if (uc.canSenseLocation(l56) && 
                (mapObj = uc.senseObjectAtLocation(l56, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d56 = 10 + Math.min(d70 + 4, Math.min(d55, d71));
        }

        // check (4, 4)
        if (uc.canSenseLocation(l176) && 
                (mapObj = uc.senseObjectAtLocation(l176, true)) != MapObject.WATER &&
                mapObj != MapObject.BALL) { 
            d176 = 10 + Math.min(d160 + 4, Math.min(d175, d161));
        }


        // uc.println("LOCAL DISTANCES:");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d185 + "\t" + d186 + "\t" + d187 + "\t" + d188 + "\t" + d189 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + d168 + "\t" + d169 + "\t" + d170 + "\t" + d171 + "\t" + d172 + "\t" + d173 + "\t" + d174 + "\t" + d175 + "\t" + d176 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + d153 + "\t" + d154 + "\t" + d155 + "\t" + d156 + "\t" + d157 + "\t" + d158 + "\t" + d159 + "\t" + d160 + "\t" + d161 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + d137 + "\t" + d138 + "\t" + d139 + "\t" + d140 + "\t" + d141 + "\t" + d142 + "\t" + d143 + "\t" + d144 + "\t" + d145 + "\t" + d146 + "\t" + d147 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + d122 + "\t" + d123 + "\t" + d124 + "\t" + d125 + "\t" + d126 + "\t" + d127 + "\t" + d128 + "\t" + d129 + "\t" + d130 + "\t" + d131 + "\t" + d132 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + d107 + "\t" + d108 + "\t" + d109 + "\t" + d110 + "\t" + d111 + "\t" + d112 + "\t" + d113 + "\t" + d114 + "\t" + d115 + "\t" + d116 + "\t" + d117 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + d92 + "\t" + d93 + "\t" + d94 + "\t" + d95 + "\t" + d96 + "\t" + d97 + "\t" + d98 + "\t" + d99 + "\t" + d100 + "\t" + d101 + "\t" + d102 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + d77 + "\t" + d78 + "\t" + d79 + "\t" + d80 + "\t" + d81 + "\t" + d82 + "\t" + d83 + "\t" + d84 + "\t" + d85 + "\t" + d86 + "\t" + d87 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + d63 + "\t" + d64 + "\t" + d65 + "\t" + d66 + "\t" + d67 + "\t" + d68 + "\t" + d69 + "\t" + d70 + "\t" + d71 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + d48 + "\t" + d49 + "\t" + d50 + "\t" + d51 + "\t" + d52 + "\t" + d53 + "\t" + d54 + "\t" + d55 + "\t" + d56 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + d35 + "\t" + d36 + "\t" + d37 + "\t" + d38 + "\t" + d39 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("DIRECTIONS:");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir185 + "\t" + dir186 + "\t" + dir187 + "\t" + dir188 + "\t" + dir189 + "\t" + "\t" + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + dir168 + "\t" + dir169 + "\t" + dir170 + "\t" + dir171 + "\t" + dir172 + "\t" + dir173 + "\t" + dir174 + "\t" + dir175 + "\t" + dir176 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + dir153 + "\t" + dir154 + "\t" + dir155 + "\t" + dir156 + "\t" + dir157 + "\t" + dir158 + "\t" + dir159 + "\t" + dir160 + "\t" + dir161 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + dir137 + "\t" + dir138 + "\t" + dir139 + "\t" + dir140 + "\t" + dir141 + "\t" + dir142 + "\t" + dir143 + "\t" + dir144 + "\t" + dir145 + "\t" + dir146 + "\t" + dir147 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + dir122 + "\t" + dir123 + "\t" + dir124 + "\t" + dir125 + "\t" + dir126 + "\t" + dir127 + "\t" + dir128 + "\t" + dir129 + "\t" + dir130 + "\t" + dir131 + "\t" + dir132 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + dir107 + "\t" + dir108 + "\t" + dir109 + "\t" + dir110 + "\t" + dir111 + "\t" + dir112 + "\t" + dir113 + "\t" + dir114 + "\t" + dir115 + "\t" + dir116 + "\t" + dir117 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + dir92 + "\t" + dir93 + "\t" + dir94 + "\t" + dir95 + "\t" + dir96 + "\t" + dir97 + "\t" + dir98 + "\t" + dir99 + "\t" + dir100 + "\t" + dir101 + "\t" + dir102 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + dir77 + "\t" + dir78 + "\t" + dir79 + "\t" + dir80 + "\t" + dir81 + "\t" + dir82 + "\t" + dir83 + "\t" + dir84 + "\t" + dir85 + "\t" + dir86 + "\t" + dir87 + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + dir63 + "\t" + dir64 + "\t" + dir65 + "\t" + dir66 + "\t" + dir67 + "\t" + dir68 + "\t" + dir69 + "\t" + dir70 + "\t" + dir71 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + dir48 + "\t" + dir49 + "\t" + dir50 + "\t" + dir51 + "\t" + dir52 + "\t" + dir53 + "\t" + dir54 + "\t" + dir55 + "\t" + dir56 + "\t" + "\t" + "\t");
        // uc.println("\t" + "\t" + "\t" + "\t" + "\t" + "\t" + dir35 + "\t" + dir36 + "\t" + dir37 + "\t" + dir38 + "\t" + dir39 + "\t" + "\t" + "\t" + "\t" + "\t");

        return dirTo(target);
    }

    private Direction dirTo(Location target) {
        if (target.distanceSquared(l112) <= 32) {
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
            return direction(d175); // destination is at relative location (3, 4)
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
            return direction(d55); // destination is at relative location (3, -4)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == 4) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d161); // destination is at relative location (4, 3)
        } else if (target_dy == 4) {
            return direction(d176); // destination is at relative location (4, 4)
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
            return direction(d71); // destination is at relative location (4, -3)
        } else if (target_dy == -4) {
            return direction(d56); // destination is at relative location (4, -4)
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
            return direction(d117); // destination is at relative location (5, 0)
        } else if (target_dy == 1) {
            return direction(d132); // destination is at relative location (5, 1)
        } else {
            return direction(d147); // destination is at relative location (5, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d102); // destination is at relative location (5, -1)
        } else {
            return direction(d87); // destination is at relative location (5, -2)
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
        if (target_dx == 0) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d157); // destination is at relative location (0, 3)
        } else if (target_dy == 4) {
            return direction(d172); // destination is at relative location (0, 4)
        } else {
            return direction(d187); // destination is at relative location (0, 5)
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
            return direction(d37); // destination is at relative location (0, -5)
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
            return direction(d188); // destination is at relative location (1, 5)
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
            return direction(d38); // destination is at relative location (1, -5)
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
            return direction(d189); // destination is at relative location (2, 5)
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
            return direction(d39); // destination is at relative location (2, -5)
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
            return direction(d186); // destination is at relative location (-1, 5)
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
            return direction(d36); // destination is at relative location (-1, -5)
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
            return direction(d185); // destination is at relative location (-2, 5)
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
            return direction(d35); // destination is at relative location (-2, -5)
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
            return direction(d169); // destination is at relative location (-3, 4)
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
            return direction(d49); // destination is at relative location (-3, -4)
        } else {
            uc.println("BFS: Invalid loc"); return null;
        }
    }
}

        } else if (target_dx == -4) {
            
if (target_dy >= 0) {
    if (target_dy >= 3) {
        if (target_dy == 3) {
            return direction(d153); // destination is at relative location (-4, 3)
        } else if (target_dy == 4) {
            return direction(d168); // destination is at relative location (-4, 4)
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
            return direction(d63); // destination is at relative location (-4, -3)
        } else if (target_dy == -4) {
            return direction(d48); // destination is at relative location (-4, -4)
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
            return direction(d107); // destination is at relative location (-5, 0)
        } else if (target_dy == 1) {
            return direction(d122); // destination is at relative location (-5, 1)
        } else {
            return direction(d137); // destination is at relative location (-5, 2)
        }
    }
} else {
    if (target_dy >= -2) {
        if (target_dy == -1) {
            return direction(d92); // destination is at relative location (-5, -1)
        } else {
            return direction(d77); // destination is at relative location (-5, -2)
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
}

        }
        
        ans = Double.POSITIVE_INFINITY;
        bestScore = 0;
        currDist = Math.sqrt(l112.distanceSquared(target));
        
        score77 = (currDist - Math.sqrt(l77.distanceSquared(target))) / d77;
        if (score77 > bestScore) {
            bestScore = score77;
            ans = d77;
        }

        score92 = (currDist - Math.sqrt(l92.distanceSquared(target))) / d92;
        if (score92 > bestScore) {
            bestScore = score92;
            ans = d92;
        }

        score107 = (currDist - Math.sqrt(l107.distanceSquared(target))) / d107;
        if (score107 > bestScore) {
            bestScore = score107;
            ans = d107;
        }

        score122 = (currDist - Math.sqrt(l122.distanceSquared(target))) / d122;
        if (score122 > bestScore) {
            bestScore = score122;
            ans = d122;
        }

        score137 = (currDist - Math.sqrt(l137.distanceSquared(target))) / d137;
        if (score137 > bestScore) {
            bestScore = score137;
            ans = d137;
        }

        score48 = (currDist - Math.sqrt(l48.distanceSquared(target))) / d48;
        if (score48 > bestScore) {
            bestScore = score48;
            ans = d48;
        }

        score63 = (currDist - Math.sqrt(l63.distanceSquared(target))) / d63;
        if (score63 > bestScore) {
            bestScore = score63;
            ans = d63;
        }

        score153 = (currDist - Math.sqrt(l153.distanceSquared(target))) / d153;
        if (score153 > bestScore) {
            bestScore = score153;
            ans = d153;
        }

        score168 = (currDist - Math.sqrt(l168.distanceSquared(target))) / d168;
        if (score168 > bestScore) {
            bestScore = score168;
            ans = d168;
        }

        score49 = (currDist - Math.sqrt(l49.distanceSquared(target))) / d49;
        if (score49 > bestScore) {
            bestScore = score49;
            ans = d49;
        }

        score169 = (currDist - Math.sqrt(l169.distanceSquared(target))) / d169;
        if (score169 > bestScore) {
            bestScore = score169;
            ans = d169;
        }

        score35 = (currDist - Math.sqrt(l35.distanceSquared(target))) / d35;
        if (score35 > bestScore) {
            bestScore = score35;
            ans = d35;
        }

        score185 = (currDist - Math.sqrt(l185.distanceSquared(target))) / d185;
        if (score185 > bestScore) {
            bestScore = score185;
            ans = d185;
        }

        score36 = (currDist - Math.sqrt(l36.distanceSquared(target))) / d36;
        if (score36 > bestScore) {
            bestScore = score36;
            ans = d36;
        }

        score186 = (currDist - Math.sqrt(l186.distanceSquared(target))) / d186;
        if (score186 > bestScore) {
            bestScore = score186;
            ans = d186;
        }

        score37 = (currDist - Math.sqrt(l37.distanceSquared(target))) / d37;
        if (score37 > bestScore) {
            bestScore = score37;
            ans = d37;
        }

        score187 = (currDist - Math.sqrt(l187.distanceSquared(target))) / d187;
        if (score187 > bestScore) {
            bestScore = score187;
            ans = d187;
        }

        score38 = (currDist - Math.sqrt(l38.distanceSquared(target))) / d38;
        if (score38 > bestScore) {
            bestScore = score38;
            ans = d38;
        }

        score188 = (currDist - Math.sqrt(l188.distanceSquared(target))) / d188;
        if (score188 > bestScore) {
            bestScore = score188;
            ans = d188;
        }

        score39 = (currDist - Math.sqrt(l39.distanceSquared(target))) / d39;
        if (score39 > bestScore) {
            bestScore = score39;
            ans = d39;
        }

        score189 = (currDist - Math.sqrt(l189.distanceSquared(target))) / d189;
        if (score189 > bestScore) {
            bestScore = score189;
            ans = d189;
        }

        score55 = (currDist - Math.sqrt(l55.distanceSquared(target))) / d55;
        if (score55 > bestScore) {
            bestScore = score55;
            ans = d55;
        }

        score175 = (currDist - Math.sqrt(l175.distanceSquared(target))) / d175;
        if (score175 > bestScore) {
            bestScore = score175;
            ans = d175;
        }

        score56 = (currDist - Math.sqrt(l56.distanceSquared(target))) / d56;
        if (score56 > bestScore) {
            bestScore = score56;
            ans = d56;
        }

        score71 = (currDist - Math.sqrt(l71.distanceSquared(target))) / d71;
        if (score71 > bestScore) {
            bestScore = score71;
            ans = d71;
        }

        score161 = (currDist - Math.sqrt(l161.distanceSquared(target))) / d161;
        if (score161 > bestScore) {
            bestScore = score161;
            ans = d161;
        }

        score176 = (currDist - Math.sqrt(l176.distanceSquared(target))) / d176;
        if (score176 > bestScore) {
            bestScore = score176;
            ans = d176;
        }

        score87 = (currDist - Math.sqrt(l87.distanceSquared(target))) / d87;
        if (score87 > bestScore) {
            bestScore = score87;
            ans = d87;
        }

        score102 = (currDist - Math.sqrt(l102.distanceSquared(target))) / d102;
        if (score102 > bestScore) {
            bestScore = score102;
            ans = d102;
        }

        score117 = (currDist - Math.sqrt(l117.distanceSquared(target))) / d117;
        if (score117 > bestScore) {
            bestScore = score117;
            ans = d117;
        }

        score132 = (currDist - Math.sqrt(l132.distanceSquared(target))) / d132;
        if (score132 > bestScore) {
            bestScore = score132;
            ans = d132;
        }

        score147 = (currDist - Math.sqrt(l147.distanceSquared(target))) / d147;
        if (score147 > bestScore) {
            bestScore = score147;
            ans = d147;
        }

        return direction(ans);
    }

    public boolean existsPathTo(Location target) {
        if (!hasCalced) {
            return bestDir(target) != null;
        }

        return dirTo(target) != null;
    }
}
