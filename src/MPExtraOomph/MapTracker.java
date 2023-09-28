package MPExtraOomph;

import java.util.function.BiPredicate;

import MPExtraOomph.fast.*;

import aic2023.user.*;

public class MapTracker {
    UnitController uc;
    Robot robot;
    Location hq;

    Direction[] dirPath;

    public class TileTypeEnum {
        public int UNKNOWN = 0;
        public int WATER = 1;
        public int GRASS = 2;
        public int FRIENDLY_HQ = 3;
        public int ENEMY_HQ = 4;
        public int BASE = 5;
        public int STADIUM = 6;
        public int OUT_OF_BOUNDS = 7;
    }

    final TileTypeEnum TileType = new TileTypeEnum();

    final int MAX_MAP_SIZE = 60;
    final int MAX_MAP_SIZE_SQ = MAX_MAP_SIZE * MAX_MAP_SIZE;
    final int MAX_MAP_SIZE2 = 2 * MAX_MAP_SIZE;

    int[][] tileType = new int[MAX_MAP_SIZE2 + 1][];

    int visionRadius;
    boolean initialized;
    int initRow;
    final int INIT_BC_LEFT = 1000;
    final int BFS_BC_LEFT = 3000;
    final int VISITED_BC_LEFT = 1000;

    int id = 12316;

    public MapTracker(UnitController u, Robot r) {
        uc = u;
        robot = r;
        hq = r.hq;
        visionRadius = (int) uc.getType().getStat(UnitStat.VISION_RANGE);
        initialized = false;
        initRow = 0;
        fillDirPath();
    }

    void visit(Location loc) {
        int[] indices = robot.util.getMapIndices(loc);
        UnitInfo info = uc.senseUnitAtLocation(loc);
        if (info != null && info.getType() == UnitType.HQ) {
            tileType[indices[0]][indices[1]] = info.getTeam() == uc.getTeam() ? TileType.FRIENDLY_HQ
                    : TileType.ENEMY_HQ;
            return;
        }

        MapObject mapObj = uc.senseObjectAtLocation(loc, false);
        if (mapObj == MapObject.GRASS) {
            tileType[indices[0]][indices[1]] = TileType.BASE;
        } else if (mapObj == MapObject.WATER) {
            tileType[indices[0]][indices[1]] = TileType.WATER;
        } else if (mapObj == MapObject.BASE) {
            tileType[indices[0]][indices[1]] = TileType.BASE;
        } else if (mapObj == MapObject.STADIUM) {
            tileType[indices[0]][indices[1]] = TileType.STADIUM;
        } else {
            robot.debug.println("Error: Found invalid map object: " + mapObj);
        }
    }

    void invalidateSymmetries(Location loc) {
        if (!robot.util.mapBoundsInitialized)
            return;

        int vertSym = robot.comms.readSymmetryVertical();
        int horizSym = robot.comms.readSymmetryHorizontal();
        int rotSym = robot.comms.readSymmetryRotational();

        // Only one symmetry left, do not invalidate
        if (vertSym + horizSym + rotSym == 1)
            return;

        BiPredicate<Integer, Integer> isReflectedTile = (tile1, tile2) -> {
            if (tile2 == TileType.UNKNOWN) {
                return true;
            } else if (tile1 == TileType.ENEMY_HQ) {
                return tile2 == TileType.FRIENDLY_HQ;
            } else if (tile1 == TileType.FRIENDLY_HQ) {
                return tile2 == TileType.ENEMY_HQ;
            }

            return tile1 == tile2;
        };

        int[] indices = robot.util.getMapIndices(loc);
        Location reflectedLoc;
        int tile1 = tileType[indices[0]][indices[1]];
        int tile2;
        int[] reflectedIndices;
        if (vertSym == 1) {
            reflectedLoc = robot.util.reflectVertical(loc);
            reflectedIndices = robot.util.getMapIndices(reflectedLoc);
            tile2 = tileType[reflectedIndices[0]][reflectedIndices[1]];
            if (!isReflectedTile.test(tile1, tile2)) {
                robot.comms.writeSymmetryVertical(0);
                robot.debug.println("Invalidated vertical symmetry");
            }
        }

        if (horizSym == 1) {
            reflectedLoc = robot.util.reflectHorizontal(loc);
            reflectedIndices = robot.util.getMapIndices(reflectedLoc);
            tile2 = tileType[reflectedIndices[0]][reflectedIndices[1]];
            if (!isReflectedTile.test(tile1, tile2)) {
                robot.comms.writeSymmetryHorizontal(0);
                robot.debug.println("Invalidated horizontal symmetry");
            }
        }

        if (rotSym == 1) {
            reflectedLoc = robot.util.reflectRotational(loc);
            reflectedIndices = robot.util.getMapIndices(reflectedLoc);
            tile2 = tileType[reflectedIndices[0]][reflectedIndices[1]];
            if (!isReflectedTile.test(tile1, tile2)) {
                robot.comms.writeSymmetryRotational(0);
                robot.debug.println("Invalidated rotational symmetry");
            }
        }
    }

    void markSeen() {
        if (!initialized)
            return;

        Location loc = uc.getLocation();
        int[] indices;
        for (int i = dirPath.length; i-- > 0;) {
            if (uc.getEnergyLeft() < VISITED_BC_LEFT)
                break;
            loc = loc.add(dirPath[i]);
            indices = robot.util.getMapIndices(loc);
            if (tileType[indices[0]][indices[1]] == TileType.UNKNOWN) {
                if (uc.canSenseLocation(loc)) {
                    visit(loc);
                    invalidateSymmetries(loc);
                } else if (uc.isOutOfMap(loc)) {
                    robot.util.updateMapBounds(loc);
                    tileType[indices[0]][indices[1]] = TileType.OUT_OF_BOUNDS;
                }
            }
        }
    }

    boolean hasVisited(Location loc) {
        if (!initialized)
            return false;
        int[] indices = robot.util.getMapIndices(loc);
        return tileType[indices[0]][indices[1]] != TileType.UNKNOWN;
    }

    void initialize() {
        if (initialized)
            return;

        while (initRow < tileType.length) {
            if (uc.getEnergyLeft() < INIT_BC_LEFT)
                return;
            tileType[initRow] = new int[MAX_MAP_SIZE2 + 1];
            initRow++;
        }
        initialized = true;
    }

    // Spiral pattern for markSeen
    void fillDirPath() {
        if (visionRadius == 20) {
            dirPath = new Direction[] { Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTH, Direction.NORTH,
                    Direction.NORTH, Direction.NORTH, Direction.NORTHEAST, Direction.NORTHEAST, Direction.EAST,
                    Direction.EAST, Direction.EAST, Direction.EAST, Direction.SOUTHEAST, Direction.SOUTHEAST,
                    Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTHWEST,
                    Direction.SOUTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.NORTHWEST,
                    Direction.NORTHWEST, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH,
                    Direction.NORTHEAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST,
                    Direction.SOUTHEAST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
                    Direction.SOUTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.NORTHWEST,
                    Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.EAST,
                    Direction.EAST, Direction.EAST, Direction.EAST, Direction.SOUTH, Direction.SOUTH,
                    Direction.SOUTH, Direction.SOUTH, Direction.WEST, Direction.WEST, Direction.WEST,
                    Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.EAST,
                    Direction.SOUTH, Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.ZERO };
        } else {
            dirPath = new Direction[] { Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTHWEST,
                    Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTHEAST,
                    Direction.NORTHEAST, Direction.NORTHEAST, Direction.EAST, Direction.EAST, Direction.EAST,
                    Direction.EAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTH,
                    Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTHWEST, Direction.SOUTHWEST,
                    Direction.SOUTHWEST, Direction.WEST, Direction.WEST, Direction.WEST, Direction.NORTHWEST,
                    Direction.NORTHWEST, Direction.NORTHWEST, Direction.NORTH, Direction.NORTH, Direction.NORTH,
                    Direction.NORTH, Direction.NORTHEAST, Direction.NORTHEAST, Direction.EAST, Direction.EAST,
                    Direction.EAST, Direction.EAST, Direction.SOUTHEAST, Direction.SOUTHEAST, Direction.SOUTH,
                    Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTHWEST, Direction.SOUTHWEST,
                    Direction.WEST, Direction.WEST, Direction.WEST, Direction.NORTHWEST, Direction.NORTHWEST,
                    Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.NORTHEAST,
                    Direction.EAST, Direction.EAST, Direction.EAST, Direction.EAST, Direction.SOUTHEAST,
                    Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH, Direction.SOUTHWEST,
                    Direction.WEST, Direction.WEST, Direction.WEST, Direction.NORTHWEST, Direction.NORTH,
                    Direction.NORTH, Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.EAST,
                    Direction.EAST, Direction.EAST, Direction.SOUTH, Direction.SOUTH, Direction.SOUTH,
                    Direction.SOUTH, Direction.WEST, Direction.WEST, Direction.WEST, Direction.NORTH,
                    Direction.NORTH, Direction.NORTH, Direction.EAST, Direction.EAST, Direction.SOUTH,
                    Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.ZERO };
        }
    }
}