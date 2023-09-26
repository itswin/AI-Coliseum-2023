package MPWorking;

import java.util.function.Predicate;

import aic2023.user.*;

public class Batter extends Robot {
    MicroBatter microBatter;

    public Batter(UnitController u) {
        super(u);
        microBatter = new MicroBatter(uc, this);
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementBatters();
    }

    @Override
    public void takeTurn() {
        if (shouldAttackEarly()) {
            attack();
        }
        if (!microBatter.doMicro()) {
            moveToTarget();
        }
        attack();
    }

    public void moveToTarget() {
        // None of our batters around the location
        Predicate<Location> availablePred = (loc) -> {
            Location[] adjLocs = util.getAdjLocs(loc);
            for (Location adjLoc : adjLocs) {
                UnitInfo unit = uc.senseUnitAtLocation(adjLoc);
                if (unit != null &&
                        unit.getTeam() == uc.getTeam() &&
                        unit.getType() == UnitType.BATTER &&
                        unit.getID() != uc.getInfo().getID()) {
                    return false;
                }
            }
            return true;
        };
        Location visibleTarget = null;
        boolean isExploring = false;
        if ((visibleTarget = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(visibleTarget);
        } else if ((visibleTarget = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(visibleTarget);
        }

        if (visibleTarget != null) {
            target = visibleTarget;
        }

        // If we're targeting a base/stadium and not all of the squares around
        // it are passable, then don't move if we're adjacent.
        if (isExploring) {
            nav.move(target);
        } else {
            boolean shouldNav = true;
            boolean greedy = false;
            if (uc.getLocation().distanceSquared(target) <= 2) {
                // Make the target impassable if it's a base/stadium
                boolean[] imp = new boolean[9];
                Direction dir = uc.getLocation().directionTo(target);
                imp[dir.ordinal()] = true;
                pathfinding.setImpassable(imp);
                greedy = true;

                Location[] adjLocs = util.getAdjLocs(target);
                int idx = adjLocs.length;
                MapObject mapObj;
                Location adjLoc;
                for (; --idx >= 0;) {
                    adjLoc = adjLocs[idx];
                    mapObj = uc.senseObjectAtLocation(adjLoc, false);
                    if (mapObj == MapObject.WATER) {
                        shouldNav = false;
                        break;
                    }
                }
            }

            if (shouldNav) {
                nav.move(target, greedy);
            }
        }
    }

    // TODO: If there is a baseball nearby that you can walk to and
    // bat into an enemy, do not attack first.
    // Otherwise, bat any enemy that is adjacent to you.
    public boolean shouldAttackEarly() {
        return true;
    }

    // Bat any enemies near you, or
    // TODO: Any baseballs nearby that will hit an enemy.
    public void attack() {
        if (!uc.canAct())
            return;

        // Bat away any adjacent enemies
        // TODO: Pick a "best" enemy
        UnitInfo[] adjEnemies = uc.senseUnits(ACTION_RANGE, uc.getTeam().getOpponent());
        if (adjEnemies.length > 0) {
            Direction dir = uc.getLocation().directionTo(adjEnemies[0].getLocation());
            for (int strength = GameConstants.MAX_STRENGTH + 1; --strength >= 0;) {
                if (uc.canBat(dir, strength)) {
                    uc.bat(dir, strength);
                    // debug.println("Batting: " + dir + " " + strength);
                    break;
                }
            }
        }

        if ((allies.length >= enemies.length + 5) && (enemies.length > 0)) {
            boolean[] dirs = getBattingDirsKillingEnemy();
            for (int i = 8; --i >= 0;) {
                if (dirs[i]) {
                    uc.bat(util.directions[i], 3);
                    break;
                }
            }
        }
    }

    public boolean[] getBattingDirsKillingEnemy() {
        boolean[] dirs = new boolean[8];

        Direction dir;
        int i;
        for (i = 8; --i >= 0;) {
            dir = util.directions[i];
            if (uc.canBat(dir, 3)) {
                dirs[i] = killsEnemyInBattingDir(dir);
            }
        }

        return dirs;
    }

    public boolean killsEnemyInBattingDir(Direction dir) {
        Location loc = uc.getLocation().add(dir);

        UnitInfo unitInfo;
        for (int i = 0; i < 3; i++) {
            loc = loc.add(dir);
            if (!uc.canSenseLocation(loc))
                return false;
            if (uc.senseObjectAtLocation(loc, false) == MapObject.WATER)
                return false;
            unitInfo = uc.senseUnitAtLocation(loc);
            if (unitInfo != null) {
                return isKillableEnemy(unitInfo);
            }
        }

        return false;
    }

    public boolean isKillableEnemy(UnitInfo info) {
        return info.getTeam() == opponent &&
                info.getType() != UnitType.CATCHER &&
                info.getType() != UnitType.HQ;
    }
}
