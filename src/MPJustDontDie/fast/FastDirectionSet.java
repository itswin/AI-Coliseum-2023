package MPJustDontDie.fast;

import aic2023.user.*;

public class FastDirectionSet {
    public final int NUM_DIRECTIONS = 9;
    public boolean[] set;

    public FastDirectionSet() {
        set = new boolean[NUM_DIRECTIONS];
    }

    public void add(Direction dir) {
        set[dir.ordinal()] = true;
    }

    public void remove(Direction dir) {
        set[dir.ordinal()] = false;
    }

    public boolean contains(Direction dir) {
        return set[dir.ordinal()];
    }

    public void reset() {
        set = new boolean[NUM_DIRECTIONS];
    }
}
