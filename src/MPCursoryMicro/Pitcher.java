package MPCursoryMicro;

import java.util.function.Predicate;

import aic2023.user.*;

public class Pitcher extends Robot {
    MicroPitcher microPitcher;

    public Pitcher(UnitController u) {
        super(u);
        microPitcher = new MicroPitcher(uc, this);
    }

    public void initTurn() {
        super.initTurn();
        comms.incrementPitchers();
        didMicro = false;
    }

    // Pitchers don't rotate through enemy HQs
    @Override
    public void rotateTargetType() {
        if (targetType == TargetType.BASE) {
            targetType = TargetType.STADIUM;
            commsStadiumIndex = 0;
        } else if (targetType == TargetType.STADIUM) {
            targetType = TargetType.EXPLORE;
            justStartedExploring = true;
        } else if (targetType == TargetType.EXPLORE) {
            targetType = TargetType.BASE;
            commsBaseIndex = 0;
        }
    }

    @Override
    public void takeTurn() {
        if (roundSeenEnemyBatter == uc.getRound()) {
            if (shouldAttack()) {
                didMicro = microPitcher.doMicro(false);
                attack();
            }
            if (microPitcher.flee())
                didMicro = true;
        }

        Predicate<Location> availablePred = (loc) -> {
            UnitInfo unit = uc.senseUnitAtLocation(loc);
            return unit == null ||
                    unit.getTeam() != uc.getTeam() ||
                    uc.getType() != UnitType.PITCHER ||
                    unit.getID() == uc.getInfo().getID();
        };
        Location visibleTarget = null;
        if ((visibleTarget = getClosestMapObj(MapObject.BASE, availablePred)) != null) {
            comms.logBase(visibleTarget);
        } else if ((visibleTarget = getClosestMapObj(MapObject.STADIUM, availablePred)) != null) {
            comms.logStadium(visibleTarget);
        }

        if (visibleTarget != null) {
            target = visibleTarget;
        }

        nav.move(target);
    }

    public boolean shouldAttack() {
        return false;
    }

    // TODO: If holding a baseball and you can place it in front of a batter
    // such that they can hit an enemy, do so.
    public void attack() {
    }
}
