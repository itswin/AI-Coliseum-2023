package MPWorking;

import aic2023.user.*;

import MPWorking.bfs.*;

public class Nav {
    UnitController uc;
    Robot robot;

    Location lastCurrLoc;
    Location currentTarget;
    int closestDistanceToDest;
    int turnsSinceClosestDistanceDecreased;
    int turnsGreedy;

    final int BYTECODE_REMAINING = 1500;
    final int BYTECODE_REMAINING_AMPLIFIER = 2000;

    final int GREEDY_TURNS = 4;

    final int DIST_TO_AVOID_CURRENTS = 8;
    final int DIST_FOR_EXACT_CURRENT = 20;

    final int id = 13175;

    public final int BFS32_COST = 6000;
    public final int BFS20_COST = 4000;
    public final int BFS10_COST = 2500;

    public BFS10 Bfs10;
    public BFS20 Bfs20;
    public BFS32 Bfs32;

    public class VisitedTracker {
        final int MAX_MAP_SIZE = 64;
        final int INT_BITS = 32;
        final int ARRAY_SIZE = 128;

        int[] visitedLocations;

        public VisitedTracker() {
            reset();
        }

        void reset() {
            visitedLocations = new int[ARRAY_SIZE];
        }

        void add(Location loc) {
            int arrayPos = (loc.x % MAX_MAP_SIZE) * (1 + (loc.y % MAX_MAP_SIZE) / INT_BITS);
            int bitPos = (loc.y % MAX_MAP_SIZE) % INT_BITS;
            visitedLocations[arrayPos] |= (1 << bitPos);
        }

        boolean check(Location loc) {
            int arrayPos = (loc.x % MAX_MAP_SIZE) * (1 + (loc.y % MAX_MAP_SIZE) / INT_BITS);
            int bitPos = (loc.y % MAX_MAP_SIZE) % INT_BITS;
            return ((visitedLocations[arrayPos] & (1 << bitPos)) > 0);
        }

    }

    public VisitedTracker visitedTracker;

    public Nav(UnitController u, Robot r) {
        uc = u;
        robot = r;
        turnsGreedy = 0;
        closestDistanceToDest = Integer.MAX_VALUE;
        turnsSinceClosestDistanceDecreased = 0;
        visitedTracker = new VisitedTracker();

        Bfs10 = new BFS10(uc);
        Bfs20 = new BFS20(uc);
        Bfs32 = new BFS32(uc);
    }

    public void initTurn() {
        Bfs10.init();
        Bfs20.init();
        Bfs32.init();
    }

    public Direction getBestDir(Location dest) {
        return getBestDir(dest, 0);
    }

    public Direction getBestDir(Location dest, int bytecodeCushion) {
        int bcLeft = uc.getEnergyLeft();
        Direction dir = null;
        if (bcLeft >= BFS32_COST + bytecodeCushion && uc.getType().getStat(UnitStat.VISION_RANGE) >= 29) {
            dir = Bfs32.bestDir(dest);
        } else if (bcLeft >= BFS20_COST + bytecodeCushion) {
            dir = Bfs20.bestDir(dest);
        } else if (bcLeft >= BFS10_COST + bytecodeCushion) {
            dir = Bfs10.bestDir(dest);
        }

        if (dir == null) {
            if (bytecodeCushion == 9999) {
                bytecodeCushion = BYTECODE_REMAINING;
            }
            dir = robot.util.getFirstValidInOrderDirection(uc.getLocation().directionTo(dest));
        }

        return dir;
    }

    void reset() {
        turnsGreedy = 0;
        visitedTracker.reset();
    }

    void activateGreedy() {
        turnsGreedy = GREEDY_TURNS;
    }

    void update(Location target) {
        if (currentTarget == null || target.distanceSquared(currentTarget) > 0) {
            closestDistanceToDest = uc.getLocation().distanceSquared(target);
            turnsSinceClosestDistanceDecreased = 0;
            currentTarget = target;
            reset();
            return;
        }

        currentTarget = target;
        visitedTracker.add(uc.getLocation());
        turnsGreedy--;

        int dist = uc.getLocation().distanceSquared(target);
        if (dist < closestDistanceToDest) {
            closestDistanceToDest = dist;
            turnsSinceClosestDistanceDecreased = 0;
        } else {
            turnsSinceClosestDistanceDecreased++;
        }
    }

    void tryMoveSafely(Location target) {
        Location currLoc = uc.getLocation();
        Direction[] importantDirs = robot.util.getInOrderDirections(currLoc.directionTo(target));

        for (Direction dir : importantDirs) {
            if (uc.canMove(dir)) {
                robot.move(dir);
                return;
            }
        }
    }

    void move(Location target) {
        move(target, false, BYTECODE_REMAINING);
    }

    void move(Location target, int bytecodeCushion) {
        move(target, false, bytecodeCushion);
    }

    void move(Location target, boolean greedy) {
        move(target, greedy, BYTECODE_REMAINING);
    }

    void move(Location target, boolean greedy, int bytecodeCushion) {
        if (target == null)
            return;
        robot.debug.setIndicatorLine(uc.getLocation(), target, 255, 0, 200);
        if (!uc.canMove())
            return;
        robot.debug.setIndicatorLine(uc.getLocation(), target, 0, 0, 200);
        if (uc.getLocation().distanceSquared(target) == 0)
            return;

        Location currLoc = uc.getLocation();

        update(target);

        boolean canBFS = turnsSinceClosestDistanceDecreased < 2 && turnsGreedy <= 0;
        if (!greedy && canBFS) {
            Direction dir = getBestDir(target, bytecodeCushion);
            if (dir != null && uc.canMove(dir)) {
                if (!visitedTracker.check(currLoc.add(dir))) {
                    robot.move(dir);
                    return;
                } else {
                    activateGreedy();
                }
            }
        }

        if (uc.getEnergyLeft() >= BYTECODE_REMAINING_AMPLIFIER) {
            robot.debug.println(robot.debug.PATHFINDING,
                    "getBestDir failed to get closer in 2 turns: Falling back to bugNav");
            robot.pathfinding.move(target);
        } else {
            robot.debug.setIndicatorDot(true, uc.getLocation(), 255, 255, 255);
            // robot.debug.println("Didn't have enough BC");
        }
    }
}
