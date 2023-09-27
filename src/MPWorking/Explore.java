package MPWorking;

import aic2023.user.*;

public class Explore {
    UnitController uc;
    Robot robot;

    Direction[] dirPath;

    final int MAX_MAP_SIZE = 60;
    final int MAX_MAP_SIZE_SQ = MAX_MAP_SIZE * MAX_MAP_SIZE;
    final int MAX_MAP_SIZE2 = 2 * MAX_MAP_SIZE;

    int visionRadius;

    Location exploreTarget;
    Direction exploreDir = Direction.ZERO;
    final double EXPLORE_DIST = 10;
    Location explore3Target;

    int turnSetExploreTarget = -1;
    int EXPLORE_TARGET_TIMEOUT = 100;

    public Explore(UnitController u, Robot r) {
        uc = u;
        robot = r;
        visionRadius = (int) uc.getType().getStat(UnitStat.VISION_RANGE);
        initExploreDir();
    }

    // Don't continue in this explore dir if it will bring you too close to a wall.
    public boolean isValidExploreDir(Direction dir) {
        if (robot.util.isCardinalDirection(dir)) {
            return robot.util.onTheMap(uc.getLocation().add(dir.dx * 4, dir.dy * 4));
        } else if (robot.util.isDiagonalDirection(dir)) {
            return robot.util.onTheMap(uc.getLocation().add(dir.dx * 3, dir.dy * 3));
        }

        // Should not happen
        return false;
    }

    void initExploreDir() {
        if (uc.getType() == UnitType.HQ)
            return;
        assignExplore3Dir(robot.util.directions[robot.util.randomInt(robot.util.directions.length)]);
    }

    // Finds a new target anywhere on the map outside of the vision radius
    // Only used when visited hasn't been initialized yet
    void emergencyTarget(int tries) {
        Location myLoc = uc.getLocation();
        if (exploreTarget != null && myLoc.distanceSquared(exploreTarget) > visionRadius)
            return;
        int X = uc.getLocation().x;
        int Y = uc.getLocation().y;
        for (int i = tries; i-- > 0;) {
            int dx = (int) (robot.util.randomInt(MAX_MAP_SIZE2) - MAX_MAP_SIZE);
            int dy = (int) (robot.util.randomInt(MAX_MAP_SIZE2) - MAX_MAP_SIZE);
            exploreTarget = new Location(X + dx, Y + dy);
            if (myLoc.distanceSquared(exploreTarget) > visionRadius)
                return;
        }
        exploreTarget = null;
    }

    Location getExploreTarget() {
        if (!robot.mapTracker.initialized)
            emergencyTarget(10);
        else
            getNewTarget(10);
        return exploreTarget;
    }

    Location getExplore3Target() {
        checkDirection();
        return explore3Target;
    }

    void assignExplore3Dir(Direction dir) {
        exploreDir = dir;
        double tempAngle = Math.atan2(exploreDir.dy, exploreDir.dx);
        int tries = 10;
        double x = uc.getLocation().x, y = uc.getLocation().y;
        Location currLoc = uc.getLocation();
        for (int i = tries; i-- > 0;) {
            // Try for more variance in the direction?
            double angle = tempAngle + (uc.getRandomDouble() * 45 - 22.5) / 180 * Math.PI;
            x += Math.cos(angle) * EXPLORE_DIST;
            y += Math.sin(angle) * EXPLORE_DIST;
            explore3Target = new Location((int) x, (int) y);
            explore3Target = robot.util.clipToKnownBounds(explore3Target);
            if (!robot.mapTracker.hasVisited(explore3Target)) {
                turnSetExploreTarget = uc.getRound();
                // Estimate the amount of time you expect to take to get to the target
                EXPLORE_TARGET_TIMEOUT = (int) (2 * Math.sqrt(currLoc.distanceSquared(explore3Target)) *
                        uc.getType().getStat(UnitStat.MOVEMENT_COOLDOWN));
                return;
            }
        }
        // robot.debug.println("ERROR: Could not find a new explore3 target!");
    }

    void checkDirection() {
        // Give up if this target has taken too long
        if (explore3Target != null && robot.util.onTheMap(explore3Target)
                && !robot.mapTracker.hasVisited(explore3Target)
                && turnSetExploreTarget + EXPLORE_TARGET_TIMEOUT < uc.getRound()) {
            assignExplore3Dir(exploreDir);
            return;
        }

        if (isValidExploreDir(exploreDir) && robot.util.onTheMap(explore3Target)) {
            if (explore3Target.distanceSquared(uc.getLocation()) <= visionRadius) {
                assignExplore3Dir(exploreDir);
            }
            return;
        }

        if (!robot.util.onTheMap(explore3Target)) {
            assignExplore3Dir(exploreDir);
            return;
        }

        // System.err.println("Checking new direction!");
        if (robot.util.isDiagonalDirection(exploreDir)) {
            getClosestExplore3Direction();
        } else if (exploreDir == Direction.NORTH || exploreDir == Direction.SOUTH) {
            if (eastCloser()) {
                assignExplore3Dir(Direction.WEST);
            } else {
                assignExplore3Dir(Direction.EAST);
            }
        } else {
            if (northCloser()) {
                assignExplore3Dir(Direction.SOUTH);
            } else {
                assignExplore3Dir(Direction.NORTH);
            }
        }
    }

    boolean eastCloser() {
        int xMin = robot.comms.readMapXMin();
        int xMax = robot.comms.readMapXMax();
        if (xMin == -1 && xMax == -1) {
            return robot.util.randomInt(2) < 1;
        }

        // If one of the bounds is unknown, go in that direction
        if (xMin == -1 || xMax == -1) {
            return xMin == -1;
        }

        return xMax - uc.getLocation().x <= uc.getLocation().x - xMin;
    }

    boolean northCloser() {
        int yMin = robot.comms.readMapYMin();
        int yMax = robot.comms.readMapYMax();
        if (yMin == -1 && yMax == -1) {
            return robot.util.randomInt(2) < 1;
        }

        // If one of the bounds is unknown, go in that direction
        if (yMin == -1 || yMax == -1) {
            return yMin == -1;
        }

        return yMax - uc.getLocation().y <= uc.getLocation().y - yMin;
    }

    void getClosestExplore3Direction() {
        Direction dirl = exploreDir.rotateLeft();
        if (!isValidExploreDir(dirl)) {
            assignExplore3Dir(dirl);
            return;
        }
        Direction dirr = exploreDir.rotateRight();
        if (!isValidExploreDir(dirr)) {
            assignExplore3Dir(dirr);
            return;
        }
        Direction dirll = dirl.rotateLeft();
        if (!isValidExploreDir(dirll)) {
            assignExplore3Dir(dirll);
            return;
        }
        Direction dirrr = dirr.rotateRight();
        if (!isValidExploreDir(dirrr)) {
            assignExplore3Dir(dirrr);
            return;
        }
        Direction dirlll = dirll.rotateLeft();
        if (!isValidExploreDir(dirlll)) {
            assignExplore3Dir(dirlll);
            return;
        }
        Direction dirrrr = dirrr.rotateRight();
        if (!isValidExploreDir(dirrrr)) {
            assignExplore3Dir(dirrrr);
            return;
        }
        Direction dirllll = dirlll.rotateLeft();
        if (!isValidExploreDir(dirllll)) {
            assignExplore3Dir(dirllll);
            return;
        }
    }

    void getNewTarget(int tries) {
        if (exploreTarget != null && robot.util.onTheMap(exploreTarget) && !robot.mapTracker.hasVisited(exploreTarget)
                && turnSetExploreTarget + EXPLORE_TARGET_TIMEOUT > uc.getRound())
            return;
        Location currLoc = uc.getLocation();
        for (int i = tries; i-- > 0;) {
            int dx = 4 * (int) (robot.util.randomInt(16) - 8);
            int dy = 4 * (int) (robot.util.randomInt(16) - 8);
            exploreTarget = new Location(currLoc.x + dx, currLoc.y + dy);
            if (!robot.mapTracker.hasVisited(exploreTarget)) {
                turnSetExploreTarget = uc.getRound();
                // Estimate the amount of time you expect to take to get to the target
                EXPLORE_TARGET_TIMEOUT = (int) (2 * Math.sqrt(currLoc.distanceSquared(exploreTarget)) *
                        uc.getType().getStat(UnitStat.MOVEMENT_COOLDOWN));
                return;
            }
        }
    }

    Direction[] getOptimalExploreOrder() {
        return new Direction[] {
                Direction.NORTH,
                Direction.SOUTHWEST,
                Direction.SOUTHEAST,
                Direction.NORTHWEST,
                Direction.EAST,
                Direction.WEST,
                Direction.NORTHEAST,
                Direction.SOUTH,
        };
    }
}
