package MPWorking;

import aic2023.user.*;

public class Util {
    private UnitController uc;
    private Robot robot;
    private Location hq;

    /** Array containing all the possible movement directions. */
    final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

    final Direction[] X_DIRECTIONS = {
            Direction.ZERO,
            Direction.NORTHEAST,
            Direction.SOUTHEAST,
            Direction.SOUTHWEST,
            Direction.NORTHWEST,
    };

    final Direction[] CARDINAL_DIRECTIONS = {
            Direction.NORTH,
            Direction.EAST,
            Direction.SOUTH,
            Direction.WEST,
    };

    /** Array containing all the possible movement directions. */
    final Direction[] DIRS_ZERO = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
            Direction.ZERO,
    };

    public Util(UnitController u, Robot r) {
        uc = u;
        robot = r;
        hq = r.hq;
    }

    int distance(Location A, Location B) {
        return Math.max(Math.abs(A.x - B.x), Math.abs(A.y - B.y));
    }

    int manhattan(Location A, Location B) {
        return Math.abs(A.x - B.x) + Math.abs(A.y - B.y);
    }

    int[] getMapIndices(Location loc) {
        return new int[] { loc.x - hq.x + GameConstants.MAX_MAP_SIZE,
                loc.y - hq.y + GameConstants.MAX_MAP_SIZE };
    }

    Location reflectHorizontal(Location loc) {
        int mapXMin = robot.comms.readMapXMin();
        int mapXMax = robot.comms.readMapXMax();

        if (mapXMin == -1 || mapXMax == -1) {
            return null;
        }

        return new Location(mapXMax - (loc.x - mapXMin), loc.y);
    }

    Location reflectVertical(Location loc) {
        int mapYMin = robot.comms.readMapYMin();
        int mapYMax = robot.comms.readMapYMax();

        if (mapYMin == -1 || mapYMax == -1) {
            return null;
        }

        return new Location(loc.x, mapYMax - (loc.y - mapYMin));
    }

    Location reflectRotational(Location loc) {
        int mapXMin = robot.comms.readMapXMin();
        int mapXMax = robot.comms.readMapXMax();
        int mapYMin = robot.comms.readMapYMin();
        int mapYMax = robot.comms.readMapYMax();

        if (mapXMin == -1 || mapXMax == -1 || mapYMin == -1 || mapYMax == -1) {
            return null;
        }

        return new Location(mapXMax - (loc.x - mapXMin), mapYMax - (loc.y - mapYMin));
    }

    Location[] getValidSymmetryLocs(Location hqLoc, boolean v, boolean h, boolean r) {
        int mapHeight = robot.comms.readMapWidth();
        int mapWidth = robot.comms.readMapHeight();

        // Don't know map dimensions yet.
        if (mapWidth == -1 || mapHeight == -1) {
            return new Location[] {};
        }

        Location verticalFlip = new Location(hqLoc.x, mapHeight - hqLoc.y - 1);
        Location horizontalFlip = new Location(mapWidth - hqLoc.x - 1, hqLoc.y);
        Location rotation = new Location(mapWidth - hqLoc.x - 1, mapHeight - hqLoc.y - 1);
        if (v) {
            if (h) {
                if (r) {
                    return new Location[] { verticalFlip, horizontalFlip, rotation };
                } else {
                    return new Location[] { verticalFlip, horizontalFlip };
                }
            } else if (r) {
                return new Location[] { verticalFlip, rotation };
            } else {
                return new Location[] { verticalFlip };
            }
        } else if (h) {
            if (r) {
                return new Location[] { horizontalFlip, rotation };
            } else {
                return new Location[] { horizontalFlip };
            }
        } else if (r) {
            return new Location[] { rotation };
        } else {
            // This should not happen.
            robot.debug.println("ERROR: No valid symmetries");
            return new Location[] { verticalFlip, horizontalFlip, rotation };
        }
    }

    boolean isCardinalDirection(Direction dir) {
        return dir != Direction.ZERO && dir.length() <= 1;
    }

    boolean isDiagonalDirection(Direction dir) {
        return dir.length() > 1;
    }

    Location getRandomAttackLoc(Direction dir) {
        Location currLoc = uc.getLocation();
        Location[] locs = null;
        Location mainLoc;

        if (isCardinalDirection(dir)) {
            mainLoc = currLoc.add(dir.dx * 4, dir.dy * 4);
            locs = new Location[] {
                    mainLoc, mainLoc.add(dir.opposite().rotateLeft()), mainLoc.add(dir.opposite().rotateRight())
            };
        } else if (isDiagonalDirection(dir)) {
            mainLoc = currLoc.add(dir.dx * 2, dir.dy * 2);
            locs = new Location[] {
                    mainLoc, mainLoc.add(dir.rotateLeft()), mainLoc.add(dir.rotateRight())
            };
        } else {
            locs = new Location[] {
                    currLoc.add(Direction.NORTH)
            };
        }

        return locs[robot.fastMath.nextInt(locs.length)];
    }

    Location[] getGroupedInitLocs(Direction dir) {
        Location currLoc = uc.getLocation();
        Location mainLoc1 = currLoc.add(dir);
        return new Location[] { mainLoc1, mainLoc1.add(dir.rotateLeft()),
                mainLoc1.add(dir.rotateRight()) };
    }

    // TODO: Move to HQ
    // Location findInitLocation(UnitType type, Direction dir) {
    // Location[] possibleLocations;
    // Location newLoc;

    // Direction left = dir.rotateLeft();
    // Direction right = dir.rotateRight();
    // Location leftLoc = uc.getLocation().add(left).add(left);
    // Location rightLoc = uc.getLocation().add(right).add(right);
    // Location center = new Location(MAP_WIDTH / 2, MAP_HEIGHT / 2);
    // boolean rotateRight = rightLoc.distanceSquared(center) <
    // leftLoc.distanceSquared(center);
    // Direction[] dirs = getInOrderDirections(dir, rotateRight);

    // for (int i = 0; i < dirs.length; i++) {
    // dir = dirs[i];
    // if (Headquarters.currentState == Headquarters.State.INIT &&
    // type == UnitType.LAUNCHER &&
    // Headquarters.isOptimalExploreDir(dir)) {
    // possibleLocations = getLauncherInitLocs(dir);
    // } else {
    // possibleLocations = getGroupedInitLocs(dir);
    // }
    // for (int x = 0; x < possibleLocations.length; x++) {
    // newLoc = possibleLocations[x];
    // if (uc.canBuildRobot(type, newLoc) &&
    // // Don't mix up carriers reading the wrong HQ's flag
    // (type != UnitType.CARRIER ||
    // !Headquarters.nearFriendlyHQ ||
    // Headquarters.friendlyHQLoc.distanceSquared(newLoc) > Robot.home
    // .distanceSquared(newLoc))) {
    // return newLoc;
    // }
    // }
    // }
    // return null;
    // }

    Location moveTowardsMe(Location loc) {
        Direction dirToMe = loc.directionTo(uc.getLocation());
        return loc.add(dirToMe);
    }

    int clip(int n, int lo, int hi) {
        return Math.min(Math.max(n, lo), hi);
    }

    double clip(double n, double lo, double hi) {
        return Math.min(Math.max(n, lo), hi);
    }

    public void updateMapBounds(Location loc) {
        int xMin = robot.comms.readMapXMin();
        int xMax = robot.comms.readMapXMax();
        int yMin = robot.comms.readMapYMin();
        int yMax = robot.comms.readMapYMax();
        if (xMin != -1 && xMax != -1 && yMin != -1 && yMax != -1) {
            return;
        }

        Location newLoc = loc.add(Direction.NORTH);
        if (uc.canSenseLocation(newLoc) && yMin == -1) {
            robot.comms.writeMapYMin(newLoc.y);
            robot.debug.println("YMin: " + newLoc.y);
        }
        newLoc = loc.add(Direction.SOUTH);
        if (uc.canSenseLocation(newLoc) && yMax == -1) {
            robot.comms.writeMapYMax(newLoc.y);
            robot.debug.println("YMax: " + newLoc.y);
        }
        newLoc = loc.add(Direction.EAST);
        if (uc.canSenseLocation(newLoc) && xMin == -1) {
            robot.comms.writeMapXMin(newLoc.x);
            robot.debug.println("XMin: " + newLoc.x);
        }
        newLoc = loc.add(Direction.WEST);
        if (uc.canSenseLocation(newLoc) && xMax == -1) {
            robot.comms.writeMapXMax(newLoc.x);
            robot.debug.println("XMax: " + newLoc.x);
        }
    }

    public boolean onTheMap(Location location) {
        int xMin = robot.comms.readMapXMin();
        int xMax = robot.comms.readMapXMax();
        int yMin = robot.comms.readMapYMin();
        int yMax = robot.comms.readMapYMax();

        if (xMin != -1 && location.x < xMin) {
            return false;
        }
        if (xMax != -1 && location.x > xMax) {
            return false;
        }
        if (yMin != -1 && location.y < yMin) {
            return false;
        }
        if (yMax != -1 && location.y > yMax) {
            return false;
        }
        return true;
    }

    int randomInt(int n) {
        return (int) (uc.getRandomDouble() * n);
    }

    Direction randomDirection() {
        return directions[randomInt(directions.length)];
    }

    Direction randomDirection(Direction[] newDirections) {
        return newDirections[randomInt(newDirections.length)];
    }

    Direction[] getInOrderDirections(Direction target_dir) {
        return new Direction[] { target_dir, target_dir.rotateRight(), target_dir.rotateLeft(),
                target_dir.rotateRight().rotateRight(), target_dir.rotateLeft().rotateLeft() };
    }

    Direction[] getInOrderDirections(Direction target_dir, boolean rotateRightFirst) {
        if (rotateRightFirst) {
            return new Direction[] { target_dir, target_dir.rotateRight(), target_dir.rotateLeft(),
                    target_dir.rotateRight().rotateRight(), target_dir.rotateLeft().rotateLeft() };
        } else {
            return new Direction[] { target_dir, target_dir.rotateLeft(), target_dir.rotateRight(),
                    target_dir.rotateLeft().rotateLeft(), target_dir.rotateRight().rotateRight() };
        }
    }

    Direction getFirstValidInOrderDirection(Direction dir) {
        for (Direction newDir : getInOrderDirections(dir)) {
            if (uc.canMove(newDir)) {
                return newDir;
            }
        }
        return Direction.ZERO;
    }

    Direction getFirstMoveableDir(Direction[] dirs) {
        for (Direction dir : dirs) {
            if (uc.canMove(dir)) {
                return dir;
            }
        }
        return Direction.ZERO;
    }

    boolean seesObstacleInWay(Location target) {
        Location loc = uc.getLocation();
        Direction dir = loc.directionTo(target);
        Location currLoc = loc;
        while (currLoc.distanceSquared(target) > 2) {
            currLoc = currLoc.add(dir);
            if (!uc.canSenseLocation(currLoc) || (uc.senseObjectAtLocation(currLoc, false) == MapObject.WATER))
                return true;
        }
        return false;
    }
}