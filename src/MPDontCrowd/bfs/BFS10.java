package MPDontCrowd.bfs;

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
