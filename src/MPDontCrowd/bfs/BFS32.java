package MPDontCrowd.bfs;

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
        
        return direction(0);
    }
}
