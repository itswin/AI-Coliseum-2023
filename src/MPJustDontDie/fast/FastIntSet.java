package MPJustDontDie.fast;

public class FastIntSet {
    final int INT_BITS = 32;
    public int[] set;

    public FastIntSet(int max) {
        set = new int[1 + max / INT_BITS];
    }

    public void add(int x) {
        int arrayPos = x / INT_BITS;
        int bitPos = x % INT_BITS;
        set[arrayPos] |= (1 << bitPos);
    }

    public boolean check(int x) {
        int arrayPos = x / INT_BITS;
        int bitPos = x % INT_BITS;
        return (set[arrayPos] & (1 << bitPos)) != 0;
    }

    public void remove(int x) {
        int arrayPos = x / INT_BITS;
        int bitPos = x % INT_BITS;
        set[arrayPos] &= ~(1 << bitPos);
    }

    public void clear() {
        set = new int[set.length];
    }
}
