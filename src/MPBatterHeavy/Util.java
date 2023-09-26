package MPBatterHeavy;

import java.util.function.Predicate;

import aic2023.user.*;

public class Util {
    private UnitController uc;
    private Robot robot;
    private Location hq;

    final int FORCE_EXPLORE_ROUNDS = 100;

    public boolean mapBoundsInitialized;

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
        mapBoundsInitialized = false;
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

    Location[] getValidSymmetryLocs(Location loc) {
        return getValidSymmetryLocs(loc,
                robot.comms.readSymmetryVertical() == 1,
                robot.comms.readSymmetryHorizontal() == 1,
                robot.comms.readSymmetryRotational() == 1);
    }

    Location[] getValidSymmetryLocs(Location loc, boolean v, boolean h, boolean r) {
        int mapXMin = robot.comms.readMapXMin();
        int mapXMax = robot.comms.readMapXMax();
        int mapYMin = robot.comms.readMapYMin();
        int mapYMax = robot.comms.readMapYMax();

        // Don't know map dimensions yet.
        if (mapXMin == -1 || mapXMax == -1 || mapYMin == -1 || mapYMax == -1) {
            return null;
        }

        Location verticalFlip = new Location(loc.x, mapYMax - (loc.y - mapYMin));
        Location horizontalFlip = new Location(mapXMax - (loc.x - mapXMin), loc.y);
        Location rotation = new Location(mapXMax - (loc.x - mapXMin), mapYMax - (loc.y - mapYMin));
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

    Location clipToHqBoundedLoc(Location loc) {
        return new Location(
                clip(loc.x, robot.hq.x - GameConstants.MAX_MAP_SIZE, robot.hq.x + GameConstants.MAX_MAP_SIZE),
                clip(loc.y, robot.hq.y - GameConstants.MAX_MAP_SIZE, robot.hq.y + GameConstants.MAX_MAP_SIZE));
    }

    public void updateMapBounds(Location loc) {
        if (mapBoundsInitialized)
            return;

        int xMin = robot.comms.readMapXMin();
        int xMax = robot.comms.readMapXMax();
        int yMin = robot.comms.readMapYMin();
        int yMax = robot.comms.readMapYMax();
        if (xMin != -1 && xMax != -1 && yMin != -1 && yMax != -1) {
            mapBoundsInitialized = true;
            return;
        }

        Location newLoc = loc.add(Direction.NORTH);
        if (uc.canSenseLocation(newLoc) && yMin == -1) {
            robot.comms.writeMapYMin(newLoc.y);
            robot.debug.println("YMin: " + newLoc.y);
            if (yMax != -1) {
                robot.comms.writeMapHeight(yMax - newLoc.y + 1);
            }
        }
        newLoc = loc.add(Direction.SOUTH);
        if (uc.canSenseLocation(newLoc) && yMax == -1) {
            robot.comms.writeMapYMax(newLoc.y);
            robot.debug.println("YMax: " + newLoc.y);
            if ((yMin = robot.comms.readMapYMin()) != -1) {
                robot.comms.writeMapHeight(newLoc.y - yMin + 1);
            }
        }
        newLoc = loc.add(Direction.EAST);
        if (uc.canSenseLocation(newLoc) && xMin == -1) {
            robot.comms.writeMapXMin(newLoc.x);
            robot.debug.println("XMin: " + newLoc.x);
            if (xMax != -1) {
                robot.comms.writeMapWidth(xMax - newLoc.x + 1);
            }
        }
        newLoc = loc.add(Direction.WEST);
        if (uc.canSenseLocation(newLoc) && xMax == -1) {
            robot.comms.writeMapXMax(newLoc.x);
            robot.debug.println("XMax: " + newLoc.x);
            if ((xMin = robot.comms.readMapXMin()) != -1) {
                robot.comms.writeMapWidth(newLoc.x - xMin + 1);
            }
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

    public int dirToFlag(Direction dir) {
        if (dir == Direction.NORTH) {
            return robot.comms.HqFlags.EXPLORE_NORTH;
        } else if (dir == Direction.EAST) {
            return robot.comms.HqFlags.EXPLORE_EAST;
        } else if (dir == Direction.SOUTH) {
            return robot.comms.HqFlags.EXPLORE_SOUTH;
        } else if (dir == Direction.WEST) {
            return robot.comms.HqFlags.EXPLORE_WEST;
        } else if (dir == Direction.NORTHWEST) {
            return robot.comms.HqFlags.EXPLORE_NORTHWEST;
        } else if (dir == Direction.NORTHEAST) {
            return robot.comms.HqFlags.EXPLORE_NORTHEAST;
        } else if (dir == Direction.SOUTHEAST) {
            return robot.comms.HqFlags.EXPLORE_SOUTHEAST;
        } else if (dir == Direction.SOUTHWEST) {
            return robot.comms.HqFlags.EXPLORE_SOUTHWEST;
        } else {
            return robot.comms.HqFlags.UNKNOWN_FLAG;
        }
    }

    public Direction flagToDir(int flag) {
        if (flag == robot.comms.HqFlags.EXPLORE_NORTH) {
            return Direction.NORTH;
        } else if (flag == robot.comms.HqFlags.EXPLORE_EAST) {
            return Direction.EAST;
        } else if (flag == robot.comms.HqFlags.EXPLORE_SOUTH) {
            return Direction.SOUTH;
        } else if (flag == robot.comms.HqFlags.EXPLORE_WEST) {
            return Direction.WEST;
        } else if (flag == robot.comms.HqFlags.EXPLORE_NORTHWEST) {
            return Direction.NORTHWEST;
        } else if (flag == robot.comms.HqFlags.EXPLORE_NORTHEAST) {
            return Direction.NORTHEAST;
        } else if (flag == robot.comms.HqFlags.EXPLORE_SOUTHEAST) {
            return Direction.SOUTHEAST;
        } else if (flag == robot.comms.HqFlags.EXPLORE_SOUTHWEST) {
            return Direction.SOUTHWEST;
        } else {
            return Direction.ZERO;
        }
    }

    public Location getClosestLocation(Location[] locs) {
        int idx;
        Location loc = null;
        Location currLoc = uc.getLocation();
        int bestDist = Integer.MAX_VALUE;
        Location bestLoc = null;
        int dist;
        for (idx = locs.length; --idx >= 0;) {
            loc = locs[idx];
            dist = currLoc.distanceSquared(loc);
            if (dist < bestDist) {
                bestDist = dist;
                bestLoc = loc;
            }
        }
        return bestLoc;
    }

    Location getClosestLoc(Location[] locs, Predicate<Location> pred) {
        int idx;
        Location loc = null;
        Location currLoc = uc.getLocation();
        int bestDist = Integer.MAX_VALUE;
        Location bestLoc = null;
        int dist;
        for (idx = locs.length; --idx >= 0;) {
            loc = locs[idx];
            dist = currLoc.distanceSquared(loc);
            if (pred.test(loc)) {
                if (dist < bestDist) {
                    bestDist = dist;
                    bestLoc = loc;
                }
            }
        }
        return bestLoc;
    }

    // Note: Does not include loc itself
    Location[] getAdjLocs(Location loc) {
        Location[] adjLocs = new Location[8];
        int idx = 0;
        int dirIdx = directions.length;
        Location adjLoc;
        Direction dir;
        for (; --dirIdx >= 0;) {
            dir = directions[dirIdx];
            adjLoc = loc.add(dir);
            if (uc.canSenseLocation(adjLoc)) {
                adjLocs[idx++] = adjLoc;
            }
        }

        Location[] sensableAdjLocs = new Location[idx];
        for (; --idx >= 0;) {
            sensableAdjLocs[idx] = adjLocs[idx];
        }
        return sensableAdjLocs;
    }
}
