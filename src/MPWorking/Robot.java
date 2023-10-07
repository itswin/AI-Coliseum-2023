package MPWorking;

import aic2023.user.*;

import java.util.function.ToDoubleFunction;

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
    public UnitInfo[] allies;

    public Location closestEnemyBatter;
    public int roundSeenEnemyBatter;

    public float REP_COST;
    public float VISION_RANGE;
    public float ACTION_RANGE;
    public float MOVEMENT_RANGE;
    public float ACTION_COOLDOWN;
    public float MOVEMENT_COOLDOWN;
    public int ID;

    public int turnSetTarget;
    public boolean[] baseVisited;
    public boolean[] stadiumVisited;

    final class TargetTypeEnum {
        public final int STADIUM = 0;
        public final int BASE = 1;
        public final int ENEMY_HQ = 2;
        public final int EXPLORE = 3;
    }

    public TargetTypeEnum TargetType = new TargetTypeEnum();

    public int targetType;
    public Location target;
    public boolean justStartedExploring;
    public int enemyHqIndex;
    public int turnShouldHaveSeenTarget;

    boolean isExploring;
    boolean isTargetingBase;
    boolean isTargetingStadium;

    public boolean didMicro;

    public boolean hasTeamSeenEnemy;

    public Robot(UnitController u) {
        uc = u;

        fastMath = new FastMath(u);
        debug = new Debug(u, this);
        comms = new Comms(u, this);

        hq = null;
        if (uc.getType() == UnitType.HQ) {
            hq = uc.getLocation();
        } else {
            hq = comms.readHqLocation();
        }

        comms.loadMapOffset();

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
        ID = uc.getInfo().getID();

        targetType = TargetType.BASE;
        baseVisited = new boolean[comms.BASE_SLOTS];
        stadiumVisited = new boolean[comms.STADIUM_SLOTS];

        target = null;
        justStartedExploring = false;
        enemyHqIndex = 0;

        didMicro = false;

        hasTeamSeenEnemy = false;
    }

    public void initTurn() {
        pathfinding.initTurn();
        nav.initTurn();
        explore.initTurn();
        if (!util.mapBoundsInitialized) {
            util.mapBoundsInitialized = comms.readMapBoundsIntialized() == 1;
        }

        enemies = uc.senseUnits(VISION_RANGE, opponent);
        allies = uc.senseUnits(VISION_RANGE, team);
        computeClosestEnemyBatter();

        if (!hasTeamSeenEnemy) {
            if (comms.readSeenEnemy()) {
                hasTeamSeenEnemy = true;
            } else if (enemies.length > 0) {
                hasTeamSeenEnemy = true;
                comms.writeSeenEnemy(true);
            }
        }
    }

    public void takeTurn() {
    }

    public void endTurn() {
        mapTracker.initialize();
        mapTracker.markSeen();
    }

    public Location getBestMapObj(MapObject mapObj, ToDoubleFunction<Location> pred) {
        return util.getBestLoc(uc.senseObjects(mapObj, VISION_RANGE), pred);
    }

    public boolean shouldLoadNextTarget() {
        // Always load next target if exploring
        if (target == null ||
        // uc.getLocation().distanceSquared(target) <= 2 ||
                targetType == TargetType.EXPLORE ||
                uc.getRound() > turnShouldHaveSeenTarget)
            return true;

        // If your slot has become occupied, rotate to the next target.
        if (targetType == TargetType.BASE || isTargetingBase) {
            if (uc.getLocation().equals(target))
                return false;
            int baseSlot = util.getBaseSlot(target);
            return baseSlot == -1 || isBaseOccupied(baseSlot);
        } else if (targetType == TargetType.STADIUM || isTargetingStadium) {
            if (uc.getLocation().equals(target))
                return false;
            int stadiumSlot = util.getStadiumSlot(target);
            return stadiumSlot == -1 || isStadiumOccupied(stadiumSlot);
        }

        return false;
    }

    void move(Direction dir) {
        if (dir == Direction.ZERO)
            return;
        uc.move(dir);
        enemies = uc.senseUnits(VISION_RANGE, opponent);
        allies = uc.senseUnits(VISION_RANGE, team);
        // (Re)Compute closest enemy?
    }

    void computeClosestEnemyBatter() {
        closestEnemyBatter = null;
        float minDist = Float.MAX_VALUE;
        float dist;
        UnitInfo unitInfo;
        Location unitLoc;
        Location currLoc = uc.getLocation();
        for (int i = enemies.length; --i >= 0;) {
            unitInfo = enemies[i];
            if (unitInfo.getType() != UnitType.BATTER)
                continue;
            unitLoc = unitInfo.getLocation();
            if (util.seesObstacleInWay(unitLoc))
                continue;
            dist = currLoc.distanceSquared(unitInfo.getLocation());
            if (dist < minDist) {
                minDist = dist;
                closestEnemyBatter = unitLoc;
                roundSeenEnemyBatter = uc.getRound();
            }
        }
    }

    public void checkKillSwitch() {
        if (comms.readHqKillSwitch() == 0)
            return;

        target = comms.readEnemyHq();
    }

    // Units should override this.
    public boolean isBaseOccupied(int slot) {
        return false;
    }

    public boolean isStadiumOccupied(int slot) {
        return false;
    }

    public boolean loadNextBase() {
        Location loc;
        Location closestLoc = null;
        int closestLocIndex = -1;
        double closestDist = Double.MAX_VALUE;
        double dist;
        for (int i = 0; i < comms.BASE_SLOTS; i++) {
            loc = comms.readBase(i);
            if (loc.x == -1)
                break;
            if (baseVisited[i] || isBaseOccupied(i))
                continue;
            dist = uc.getLocation().distanceSquared(loc);
            if (dist < closestDist) {
                closestDist = dist;
                closestLoc = loc;
                closestLocIndex = i;
            }
        }

        if (closestLoc != null) {
            baseVisited[closestLocIndex] = true;
            setTarget(closestLoc);
            return true;
        }

        return false;
    }

    public boolean loadNextStadium() {
        Location loc;
        Location closestLoc = null;
        int closestLocIndex = -1;
        double closestDist = Double.MAX_VALUE;
        double dist;
        for (int i = 0; i < comms.STADIUM_SLOTS; i++) {
            loc = comms.readStadium(i);
            if (loc.x == -1)
                break;
            if (stadiumVisited[i] || isStadiumOccupied(i))
                continue;
            dist = uc.getLocation().distanceSquared(loc);
            if (dist < closestDist) {
                closestDist = dist;
                closestLoc = loc;
                closestLocIndex = i;
            }
        }

        if (closestLoc != null) {
            stadiumVisited[closestLocIndex] = true;
            setTarget(closestLoc);
            return true;
        }

        return false;
    }

    public void setTarget(Location newTarget) {
        target = newTarget;
        turnShouldHaveSeenTarget = uc.getRound()
                + 8 * (int) Math.sqrt(uc.getLocation().distanceSquared(target));
    }
}
