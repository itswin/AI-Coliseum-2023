package MPFlourish;

import aic2023.user.*;

public class UnitPlayer {

    Robot robot;

    public void run(UnitController uc) {
        reset(uc);

        while (true) {
            try {
                robot.initTurn();
                robot.takeTurn();
                robot.endTurn();
            } catch (Exception e) {
                uc.println(uc.getType() + " Exception. Round: " + uc.getRound() + ". ID: " + uc.getInfo().getID());
                e.printStackTrace();
                reset(uc);
            }

            uc.yield();
        }
    }

    // Last resort if a robot errors out in deployed code
    public void reset(UnitController uc) {
        UnitType unitType = uc.getType();
        if (unitType == UnitType.PITCHER) {
            robot = new Pitcher(uc);
        } else if (unitType == UnitType.BATTER) {
            robot = new Batter(uc);
        } else if (unitType == UnitType.CATCHER) {
            robot = new Catcher(uc);
        } else if (unitType == UnitType.HQ) {
            robot = new Hq(uc);
        }
    }

}
