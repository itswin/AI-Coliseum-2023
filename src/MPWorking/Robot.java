package MPWorking;

import aic2023.user.*;

import java.util.function.Predicate;

import MPWorking.fast.*;

public class Robot {
    public UnitController uc;

    public Location hq;

    public FastMath fastMath;
    public Debug debug;
    public Util util;
    public MapTracker mapTracker;
    public Explore explore;
    public Comms comms;
    public Pathfinding pathfinding;
    public Nav nav;

    public Team team;
    public Team opponent;
    public UnitInfo[] enemies;

    public Location closestEnemyBatter;
    public int roundSeenEnemyBatter;

    public float REP_COST;
    public float VISION_RANGE;
    public float ACTION_RANGE;
    public float MOVEMENT_RANGE;
    public float ACTION_COOLDOWN;
    public float MOVEMENT_COOLDOWN;

    public int commsBaseIndex;
    public int commsStadiumIndex;

    final class TargetTypeEnum {
        public final int BASE = 0;
        public final int STADIUM = 1;
        public final int ENEMY_HQ = 2;
        public final int EXPLORE = 3;
    }

    public TargetTypeEnum TargetType = new TargetTypeEnum();

    public int targetType;
    public Location target;
    public boolean justStartedExploring;
    public int enemyHqIndex;

    public boolean didMicro;

    public Robot(UnitController u) {
        uc = u;

        hq = null;
        if (uc.getType() == UnitType.HQ) {
            hq = uc.getLocation();
        } else {
            for (UnitInfo loc : uc.senseUnits(2, uc.getTeam())) {
                if (loc.getType() == UnitType.HQ) {
                    hq = loc.getLocation();
                    break;
                }
            }

            if (hq == null) {
                uc.println("ERROR: Did not find HQ!");
                hq = uc.getLocation();
            }
        }

        fastMath = new FastMath(u);
        debug = new Debug(u, this);
        comms = new Comms(u, this);
        util = new Util(u, this);
        mapTracker = new MapTracker(u, this);
        explore = new Explore(u, this);
        pathfinding = new Pathfinding(u, this);
        nav = new Nav(u, this);

        team = uc.getTeam();
        opponent = team.getOpponent();
        enemies = null;

        closestEnemyBatter = null;
        roundSeenEnemyBatter = -1;

        int hqFlag = comms.readHqFlag();
        if (comms.isExploreDirFlag(hqFlag)) {
            explore.assignExplore3Dir(util.flagToDir(hqFlag));
        }

        REP_COST = uc.getType().getStat(UnitStat.REP_COST);
        VISION_RANGE = uc.getType().getStat(UnitStat.VISION_RANGE);
        ACTION_RANGE = uc.getType().getStat(UnitStat.ACTION_RANGE);
        MOVEMENT_RANGE = uc.getType().getStat(UnitStat.MOVEMENT_RANGE);
        ACTION_COOLDOWN = uc.getType().getStat(UnitStat.ACTION_COOLDOWN);
        MOVEMENT_COOLDOWN = uc.getType().getStat(UnitStat.MOVEMENT_COOLDOWN);

        targetType = TargetType.BASE;
        commsBaseIndex = 0;
        commsStadiumIndex = 0;
        targetType = TargetType.BASE;
        target = null;
        justStartedExploring = false;
        enemyHqIndex = 0;

        didMicro = false;
    }

    public void initTurn() {
        pathfinding.initTurn();

        if (shouldLoadNextTarget()) {
            loadNextTarget();
        }

        enemies = uc.senseUnits(VISION_RANGE, opponent);
        computeClosestEnemyBatter();
    }

    public void takeTurn() {
    }

    public void endTurn() {
        mapTracker.initialize();
        mapTracker.markSeen();
    }

    public Location getClosestMapObj(MapObject mapObj, Predicate<Location> pred) {
        return util.getClosestLoc(uc.senseObjects(mapObj, VISION_RANGE), pred);
    }

    public void rotateTargetType() {
        if (targetType == TargetType.BASE) {
            targetType = TargetType.STADIUM;
            commsStadiumIndex = 0;
        } else if (targetType == TargetType.STADIUM) {
            targetType = TargetType.ENEMY_HQ;
            enemyHqIndex = 0;
        } else if (targetType == TargetType.ENEMY_HQ) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            targetType = TargetType.BASE;
            commsBaseIndex = 0;
        }
    }

    public boolean shouldLoadNextTarget() {
        // Always load next target if exploring
        return target == null ||
                uc.getLocation().distanceSquared(target) <= VISION_RANGE ||
                targetType == TargetType.EXPLORE;
    }

    public void loadNextTarget() {
        if (targetType == TargetType.BASE) {
            if (commsBaseIndex >= comms.BASE_SLOTS ||
                    (target = comms.readBase(commsBaseIndex++)).x == -1) {
                rotateTargetType();
                loadNextTarget();
            }
        } else if (targetType == TargetType.STADIUM) {
            if (commsStadiumIndex >= comms.STADIUM_SLOTS ||
                    (target = comms.readStadium(commsStadiumIndex++)).x == -1) {
                rotateTargetType();
                loadNextTarget();
            }
        } else if (targetType == TargetType.ENEMY_HQ) {
            if (!util.mapBoundsInitialized) {
                rotateTargetType();
                loadNextTarget();
            } else {
                Location[] enemyHqLocations = util.getValidSymmetryLocs(hq);
                if (enemyHqIndex >= enemyHqLocations.length) {
                    rotateTargetType();
                    loadNextTarget();
                } else {
                    target = enemyHqLocations[enemyHqIndex++];
                }
            }
        } else if (targetType == TargetType.EXPLORE) {
            if (justStartedExploring) {
                target = explore.getExplore3Target();
                justStartedExploring = false;
            } else {
                Location newTarget = explore.getExplore3Target();
                // If we reset the explore target, rotate target types
                if (!target.equals(newTarget)) {
                    rotateTargetType();
                    loadNextTarget();
                } else {
                    target = newTarget;
                }
            }
        }
    }

    void move(Direction dir) {
        if (dir == Direction.ZERO)
            return;
        uc.move(dir);
        enemies = uc.senseUnits(VISION_RANGE, opponent);
        // (Re)Compute closest enemy?
    }

    void computeClosestEnemyBatter() {
        boolean first = true;
        UnitInfo unitInfo;
        for (int i = enemies.length; --i >= 0;) {
            unitInfo = enemies[i];
            if (unitInfo.getType() != UnitType.BATTER)
                continue;
            if (first || closestEnemyBatter.distanceSquared(uc.getLocation()) > unitInfo.getLocation()
                    .distanceSquared(uc.getLocation())) {
                closestEnemyBatter = uc.getLocation();
                roundSeenEnemyBatter = uc.getRound();
            }
            first = false;
        }
    }
}
