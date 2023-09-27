package MPDontCrowd.fast;

import aic2023.user.*;

public class FastSort {

    public UnitController rc;

    public void init(UnitController r) {
        rc = r;
    }

    // public void main(String[] args) {
    // int[] a = new int[]{4, 2, 3, 5, 0, 15, 20};
    // sort(a);
    // for(int i = 0; i < FastSort.size; i++) {
    // uc.println("" + i + ": " + a[FastSort.indices[i]] + "\n");
    // }
    // }

    public void distSort(UnitInfo[] robots) {
        Location currLoc = rc.getLocation();
        int[] distances = new int[Math.min(robots.length, 16)];

        if (robots.length == 0) {
            size = 0;
            return;
        }

        for (int i = robots.length; --i >= 0;) {
            distances[i] = currLoc.distanceSquared(robots[15].getLocation());
        }
        sort(distances);
    }

    // Sorts array in increasing order, placing the indices of the sorted array in
    // indices
    // See the commented out main method for an example of how to use this class
    // WARNING: Only sorts arrays of nonnegative integers
    // WARNING: If the array has more than 16 elements, only the first 16 will be
    // sorted.
    public void sort(int[] array) {
        int len = array.length;
        if (len == 0) {
            size = 0;
        } else if (len == 1) {
            size = 1;
            indices[0] = 0;
        } else if (len == 2) {
            sort2(array);
        } else if (len == 3) {
            sort3(array);
        } else if (len == 4) {
            sort4(array);
        } else if (len == 5) {
            sort5(array);
        } else if (len == 6) {
            sort6(array);
        } else if (len == 7) {
            sort7(array);
        } else if (len == 8) {
            sort8(array);
        } else if (len == 9) {
            sort9(array);
        } else if (len == 10) {
            sort10(array);
        } else if (len == 11) {
            sort11(array);
        } else if (len == 12) {
            sort12(array);
        } else if (len == 13) {
            sort13(array);
        } else if (len == 14) {
            sort14(array);
        } else if (len == 15) {
            sort15(array);
        } else {
            sort16(array);
        }
    }

    public int[] indices = new int[16];

    public int size;
    private long tmp;
    private long a0;
    private long a1;
    private long a2;
    private long a3;
    private long a4;
    private long a5;
    private long a6;
    private long a7;
    private long a8;
    private long a9;
    private long a10;
    private long a11;
    private long a12;
    private long a13;
    private long a14;
    private long a15;

    private void sort2(int[] array) {
        size = 2;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
    }

    private void sort3(int[] array) {
        size = 3;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
    }

    private void sort4(int[] array) {
        size = 4;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
    }

    private void sort5(int[] array) {
        size = 5;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        if (a0 > a3) {
            tmp = a0;
            a0 = a3;
            a3 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
    }

    private void sort6(int[] array) {
        size = 6;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        if (a0 > a5) {
            tmp = a0;
            a0 = a5;
            a5 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a0 > a3) {
            tmp = a0;
            a0 = a3;
            a3 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
    }

    private void sort7(int[] array) {
        size = 7;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        if (a0 > a6) {
            tmp = a0;
            a0 = a6;
            a6 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
    }

    private void sort8(int[] array) {
        size = 8;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a0 > a4) {
            tmp = a0;
            a0 = a4;
            a4 = tmp;
        }
        if (a1 > a5) {
            tmp = a1;
            a1 = a5;
            a5 = tmp;
        }
        if (a2 > a6) {
            tmp = a2;
            a2 = a6;
            a6 = tmp;
        }
        if (a3 > a7) {
            tmp = a3;
            a3 = a7;
            a7 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
    }

    private void sort9(int[] array) {
        size = 9;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        if (a0 > a3) {
            tmp = a0;
            a0 = a3;
            a3 = tmp;
        }
        if (a1 > a7) {
            tmp = a1;
            a1 = a7;
            a7 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a4 > a8) {
            tmp = a4;
            a4 = a8;
            a8 = tmp;
        }
        if (a0 > a7) {
            tmp = a0;
            a0 = a7;
            a7 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a8) {
            tmp = a3;
            a3 = a8;
            a8 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
    }

    private void sort10(int[] array) {
        size = 10;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        if (a0 > a8) {
            tmp = a0;
            a0 = a8;
            a8 = tmp;
        }
        if (a1 > a9) {
            tmp = a1;
            a1 = a9;
            a9 = tmp;
        }
        if (a2 > a7) {
            tmp = a2;
            a2 = a7;
            a7 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a5 > a8) {
            tmp = a5;
            a5 = a8;
            a8 = tmp;
        }
        if (a7 > a9) {
            tmp = a7;
            a7 = a9;
            a9 = tmp;
        }
        if (a0 > a3) {
            tmp = a0;
            a0 = a3;
            a3 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a6 > a9) {
            tmp = a6;
            a6 = a9;
            a9 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a1 > a5) {
            tmp = a1;
            a1 = a5;
            a5 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a8) {
            tmp = a4;
            a4 = a8;
            a8 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
    }

    private void sort11(int[] array) {
        size = 11;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        a10 = (array[10] << 5) | 10;
        if (a0 > a9) {
            tmp = a0;
            a0 = a9;
            a9 = tmp;
        }
        if (a1 > a6) {
            tmp = a1;
            a1 = a6;
            a6 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a7) {
            tmp = a3;
            a3 = a7;
            a7 = tmp;
        }
        if (a5 > a8) {
            tmp = a5;
            a5 = a8;
            a8 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a4 > a10) {
            tmp = a4;
            a4 = a10;
            a10 = tmp;
        }
        if (a6 > a9) {
            tmp = a6;
            a6 = a9;
            a9 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a4 > a7) {
            tmp = a4;
            a4 = a7;
            a7 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a0 > a4) {
            tmp = a0;
            a0 = a4;
            a4 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a7) {
            tmp = a3;
            a3 = a7;
            a7 = tmp;
        }
        if (a5 > a9) {
            tmp = a5;
            a5 = a9;
            a9 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a6) {
            tmp = a2;
            a2 = a6;
            a6 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
        indices[10] = (int) (a10 & 31);
    }

    private void sort12(int[] array) {
        size = 12;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        a10 = (array[10] << 5) | 10;
        a11 = (array[11] << 5) | 11;
        if (a0 > a8) {
            tmp = a0;
            a0 = a8;
            a8 = tmp;
        }
        if (a1 > a7) {
            tmp = a1;
            a1 = a7;
            a7 = tmp;
        }
        if (a2 > a6) {
            tmp = a2;
            a2 = a6;
            a6 = tmp;
        }
        if (a3 > a11) {
            tmp = a3;
            a3 = a11;
            a11 = tmp;
        }
        if (a4 > a10) {
            tmp = a4;
            a4 = a10;
            a10 = tmp;
        }
        if (a5 > a9) {
            tmp = a5;
            a5 = a9;
            a9 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a6 > a9) {
            tmp = a6;
            a6 = a9;
            a9 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a10 > a11) {
            tmp = a10;
            a10 = a11;
            a11 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a6) {
            tmp = a1;
            a1 = a6;
            a6 = tmp;
        }
        if (a5 > a10) {
            tmp = a5;
            a5 = a10;
            a10 = tmp;
        }
        if (a9 > a11) {
            tmp = a9;
            a9 = a11;
            a11 = tmp;
        }
        if (a0 > a3) {
            tmp = a0;
            a0 = a3;
            a3 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a8 > a11) {
            tmp = a8;
            a8 = a11;
            a11 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a7 > a10) {
            tmp = a7;
            a7 = a10;
            a10 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a6 > a9) {
            tmp = a6;
            a6 = a9;
            a9 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
        indices[10] = (int) (a10 & 31);
        indices[11] = (int) (a11 & 31);
    }

    private void sort13(int[] array) {
        size = 13;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        a10 = (array[10] << 5) | 10;
        a11 = (array[11] << 5) | 11;
        a12 = (array[12] << 5) | 12;
        if (a0 > a12) {
            tmp = a0;
            a0 = a12;
            a12 = tmp;
        }
        if (a1 > a10) {
            tmp = a1;
            a1 = a10;
            a10 = tmp;
        }
        if (a2 > a9) {
            tmp = a2;
            a2 = a9;
            a9 = tmp;
        }
        if (a3 > a7) {
            tmp = a3;
            a3 = a7;
            a7 = tmp;
        }
        if (a5 > a11) {
            tmp = a5;
            a5 = a11;
            a11 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a1 > a6) {
            tmp = a1;
            a1 = a6;
            a6 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a11) {
            tmp = a4;
            a4 = a11;
            a11 = tmp;
        }
        if (a7 > a9) {
            tmp = a7;
            a7 = a9;
            a9 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a0 > a4) {
            tmp = a0;
            a0 = a4;
            a4 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a11 > a12) {
            tmp = a11;
            a11 = a12;
            a12 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a5 > a9) {
            tmp = a5;
            a5 = a9;
            a9 = tmp;
        }
        if (a8 > a11) {
            tmp = a8;
            a8 = a11;
            a11 = tmp;
        }
        if (a10 > a12) {
            tmp = a10;
            a10 = a12;
            a12 = tmp;
        }
        if (a0 > a5) {
            tmp = a0;
            a0 = a5;
            a5 = tmp;
        }
        if (a3 > a8) {
            tmp = a3;
            a3 = a8;
            a8 = tmp;
        }
        if (a4 > a7) {
            tmp = a4;
            a4 = a7;
            a7 = tmp;
        }
        if (a6 > a11) {
            tmp = a6;
            a6 = a11;
            a11 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a5) {
            tmp = a2;
            a2 = a5;
            a5 = tmp;
        }
        if (a6 > a9) {
            tmp = a6;
            a6 = a9;
            a9 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a10 > a11) {
            tmp = a10;
            a10 = a11;
            a11 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
        indices[10] = (int) (a10 & 31);
        indices[11] = (int) (a11 & 31);
        indices[12] = (int) (a12 & 31);
    }

    private void sort14(int[] array) {
        size = 14;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        a10 = (array[10] << 5) | 10;
        a11 = (array[11] << 5) | 11;
        a12 = (array[12] << 5) | 12;
        a13 = (array[13] << 5) | 13;
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a10 > a11) {
            tmp = a10;
            a10 = a11;
            a11 = tmp;
        }
        if (a12 > a13) {
            tmp = a12;
            a12 = a13;
            a13 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a4 > a8) {
            tmp = a4;
            a4 = a8;
            a8 = tmp;
        }
        if (a5 > a9) {
            tmp = a5;
            a5 = a9;
            a9 = tmp;
        }
        if (a10 > a12) {
            tmp = a10;
            a10 = a12;
            a12 = tmp;
        }
        if (a11 > a13) {
            tmp = a11;
            a11 = a13;
            a13 = tmp;
        }
        if (a0 > a4) {
            tmp = a0;
            a0 = a4;
            a4 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a7) {
            tmp = a3;
            a3 = a7;
            a7 = tmp;
        }
        if (a5 > a8) {
            tmp = a5;
            a5 = a8;
            a8 = tmp;
        }
        if (a6 > a10) {
            tmp = a6;
            a6 = a10;
            a10 = tmp;
        }
        if (a9 > a13) {
            tmp = a9;
            a9 = a13;
            a13 = tmp;
        }
        if (a11 > a12) {
            tmp = a11;
            a11 = a12;
            a12 = tmp;
        }
        if (a0 > a6) {
            tmp = a0;
            a0 = a6;
            a6 = tmp;
        }
        if (a1 > a5) {
            tmp = a1;
            a1 = a5;
            a5 = tmp;
        }
        if (a3 > a9) {
            tmp = a3;
            a3 = a9;
            a9 = tmp;
        }
        if (a4 > a10) {
            tmp = a4;
            a4 = a10;
            a10 = tmp;
        }
        if (a7 > a13) {
            tmp = a7;
            a7 = a13;
            a13 = tmp;
        }
        if (a8 > a12) {
            tmp = a8;
            a8 = a12;
            a12 = tmp;
        }
        if (a2 > a10) {
            tmp = a2;
            a2 = a10;
            a10 = tmp;
        }
        if (a3 > a11) {
            tmp = a3;
            a3 = a11;
            a11 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a7 > a9) {
            tmp = a7;
            a7 = a9;
            a9 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a2 > a8) {
            tmp = a2;
            a2 = a8;
            a8 = tmp;
        }
        if (a5 > a11) {
            tmp = a5;
            a5 = a11;
            a11 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a10 > a12) {
            tmp = a10;
            a10 = a12;
            a12 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a2 > a6) {
            tmp = a2;
            a2 = a6;
            a6 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a7 > a11) {
            tmp = a7;
            a7 = a11;
            a11 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a9 > a12) {
            tmp = a9;
            a9 = a12;
            a12 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a5 > a8) {
            tmp = a5;
            a5 = a8;
            a8 = tmp;
        }
        if (a7 > a10) {
            tmp = a7;
            a7 = a10;
            a10 = tmp;
        }
        if (a9 > a11) {
            tmp = a9;
            a9 = a11;
            a11 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
        indices[10] = (int) (a10 & 31);
        indices[11] = (int) (a11 & 31);
        indices[12] = (int) (a12 & 31);
        indices[13] = (int) (a13 & 31);
    }

    private void sort15(int[] array) {
        size = 15;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        a10 = (array[10] << 5) | 10;
        a11 = (array[11] << 5) | 11;
        a12 = (array[12] << 5) | 12;
        a13 = (array[13] << 5) | 13;
        a14 = (array[14] << 5) | 14;
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a10) {
            tmp = a3;
            a3 = a10;
            a10 = tmp;
        }
        if (a4 > a14) {
            tmp = a4;
            a4 = a14;
            a14 = tmp;
        }
        if (a5 > a8) {
            tmp = a5;
            a5 = a8;
            a8 = tmp;
        }
        if (a6 > a13) {
            tmp = a6;
            a6 = a13;
            a13 = tmp;
        }
        if (a7 > a12) {
            tmp = a7;
            a7 = a12;
            a12 = tmp;
        }
        if (a9 > a11) {
            tmp = a9;
            a9 = a11;
            a11 = tmp;
        }
        if (a0 > a14) {
            tmp = a0;
            a0 = a14;
            a14 = tmp;
        }
        if (a1 > a5) {
            tmp = a1;
            a1 = a5;
            a5 = tmp;
        }
        if (a2 > a8) {
            tmp = a2;
            a2 = a8;
            a8 = tmp;
        }
        if (a3 > a7) {
            tmp = a3;
            a3 = a7;
            a7 = tmp;
        }
        if (a6 > a9) {
            tmp = a6;
            a6 = a9;
            a9 = tmp;
        }
        if (a10 > a12) {
            tmp = a10;
            a10 = a12;
            a12 = tmp;
        }
        if (a11 > a13) {
            tmp = a11;
            a11 = a13;
            a13 = tmp;
        }
        if (a0 > a7) {
            tmp = a0;
            a0 = a7;
            a7 = tmp;
        }
        if (a1 > a6) {
            tmp = a1;
            a1 = a6;
            a6 = tmp;
        }
        if (a2 > a9) {
            tmp = a2;
            a2 = a9;
            a9 = tmp;
        }
        if (a4 > a10) {
            tmp = a4;
            a4 = a10;
            a10 = tmp;
        }
        if (a5 > a11) {
            tmp = a5;
            a5 = a11;
            a11 = tmp;
        }
        if (a8 > a13) {
            tmp = a8;
            a8 = a13;
            a13 = tmp;
        }
        if (a12 > a14) {
            tmp = a12;
            a12 = a14;
            a14 = tmp;
        }
        if (a0 > a6) {
            tmp = a0;
            a0 = a6;
            a6 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a7 > a11) {
            tmp = a7;
            a7 = a11;
            a11 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a9 > a12) {
            tmp = a9;
            a9 = a12;
            a12 = tmp;
        }
        if (a13 > a14) {
            tmp = a13;
            a13 = a14;
            a14 = tmp;
        }
        if (a0 > a3) {
            tmp = a0;
            a0 = a3;
            a3 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a4 > a7) {
            tmp = a4;
            a4 = a7;
            a7 = tmp;
        }
        if (a5 > a9) {
            tmp = a5;
            a5 = a9;
            a9 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a10 > a11) {
            tmp = a10;
            a10 = a11;
            a11 = tmp;
        }
        if (a12 > a13) {
            tmp = a12;
            a12 = a13;
            a13 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a7 > a9) {
            tmp = a7;
            a7 = a9;
            a9 = tmp;
        }
        if (a10 > a12) {
            tmp = a10;
            a10 = a12;
            a12 = tmp;
        }
        if (a11 > a13) {
            tmp = a11;
            a11 = a13;
            a13 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a11 > a12) {
            tmp = a11;
            a11 = a12;
            a12 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a10 > a11) {
            tmp = a10;
            a10 = a11;
            a11 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
        indices[10] = (int) (a10 & 31);
        indices[11] = (int) (a11 & 31);
        indices[12] = (int) (a12 & 31);
        indices[13] = (int) (a13 & 31);
        indices[14] = (int) (a14 & 31);
    }

    private void sort16(int[] array) {
        size = 16;
        a0 = (array[0] << 5) | 0;
        a1 = (array[1] << 5) | 1;
        a2 = (array[2] << 5) | 2;
        a3 = (array[3] << 5) | 3;
        a4 = (array[4] << 5) | 4;
        a5 = (array[5] << 5) | 5;
        a6 = (array[6] << 5) | 6;
        a7 = (array[7] << 5) | 7;
        a8 = (array[8] << 5) | 8;
        a9 = (array[9] << 5) | 9;
        a10 = (array[10] << 5) | 10;
        a11 = (array[11] << 5) | 11;
        a12 = (array[12] << 5) | 12;
        a13 = (array[13] << 5) | 13;
        a14 = (array[14] << 5) | 14;
        a15 = (array[15] << 5) | 15;
        if (a0 > a13) {
            tmp = a0;
            a0 = a13;
            a13 = tmp;
        }
        if (a1 > a12) {
            tmp = a1;
            a1 = a12;
            a12 = tmp;
        }
        if (a2 > a15) {
            tmp = a2;
            a2 = a15;
            a15 = tmp;
        }
        if (a3 > a14) {
            tmp = a3;
            a3 = a14;
            a14 = tmp;
        }
        if (a4 > a8) {
            tmp = a4;
            a4 = a8;
            a8 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a11) {
            tmp = a7;
            a7 = a11;
            a11 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a0 > a5) {
            tmp = a0;
            a0 = a5;
            a5 = tmp;
        }
        if (a1 > a7) {
            tmp = a1;
            a1 = a7;
            a7 = tmp;
        }
        if (a2 > a9) {
            tmp = a2;
            a2 = a9;
            a9 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a6 > a13) {
            tmp = a6;
            a6 = a13;
            a13 = tmp;
        }
        if (a8 > a14) {
            tmp = a8;
            a8 = a14;
            a14 = tmp;
        }
        if (a10 > a15) {
            tmp = a10;
            a10 = a15;
            a15 = tmp;
        }
        if (a11 > a12) {
            tmp = a11;
            a11 = a12;
            a12 = tmp;
        }
        if (a0 > a1) {
            tmp = a0;
            a0 = a1;
            a1 = tmp;
        }
        if (a2 > a3) {
            tmp = a2;
            a2 = a3;
            a3 = tmp;
        }
        if (a4 > a5) {
            tmp = a4;
            a4 = a5;
            a5 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a7 > a9) {
            tmp = a7;
            a7 = a9;
            a9 = tmp;
        }
        if (a10 > a11) {
            tmp = a10;
            a10 = a11;
            a11 = tmp;
        }
        if (a12 > a13) {
            tmp = a12;
            a12 = a13;
            a13 = tmp;
        }
        if (a14 > a15) {
            tmp = a14;
            a14 = a15;
            a15 = tmp;
        }
        if (a0 > a2) {
            tmp = a0;
            a0 = a2;
            a2 = tmp;
        }
        if (a1 > a3) {
            tmp = a1;
            a1 = a3;
            a3 = tmp;
        }
        if (a4 > a10) {
            tmp = a4;
            a4 = a10;
            a10 = tmp;
        }
        if (a5 > a11) {
            tmp = a5;
            a5 = a11;
            a11 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        if (a12 > a14) {
            tmp = a12;
            a12 = a14;
            a14 = tmp;
        }
        if (a13 > a15) {
            tmp = a13;
            a13 = a15;
            a15 = tmp;
        }
        if (a1 > a2) {
            tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (a3 > a12) {
            tmp = a3;
            a3 = a12;
            a12 = tmp;
        }
        if (a4 > a6) {
            tmp = a4;
            a4 = a6;
            a6 = tmp;
        }
        if (a5 > a7) {
            tmp = a5;
            a5 = a7;
            a7 = tmp;
        }
        if (a8 > a10) {
            tmp = a8;
            a8 = a10;
            a10 = tmp;
        }
        if (a9 > a11) {
            tmp = a9;
            a9 = a11;
            a11 = tmp;
        }
        if (a13 > a14) {
            tmp = a13;
            a13 = a14;
            a14 = tmp;
        }
        if (a1 > a4) {
            tmp = a1;
            a1 = a4;
            a4 = tmp;
        }
        if (a2 > a6) {
            tmp = a2;
            a2 = a6;
            a6 = tmp;
        }
        if (a5 > a8) {
            tmp = a5;
            a5 = a8;
            a8 = tmp;
        }
        if (a7 > a10) {
            tmp = a7;
            a7 = a10;
            a10 = tmp;
        }
        if (a9 > a13) {
            tmp = a9;
            a9 = a13;
            a13 = tmp;
        }
        if (a11 > a14) {
            tmp = a11;
            a11 = a14;
            a14 = tmp;
        }
        if (a2 > a4) {
            tmp = a2;
            a2 = a4;
            a4 = tmp;
        }
        if (a3 > a6) {
            tmp = a3;
            a3 = a6;
            a6 = tmp;
        }
        if (a9 > a12) {
            tmp = a9;
            a9 = a12;
            a12 = tmp;
        }
        if (a11 > a13) {
            tmp = a11;
            a11 = a13;
            a13 = tmp;
        }
        if (a3 > a5) {
            tmp = a3;
            a3 = a5;
            a5 = tmp;
        }
        if (a6 > a8) {
            tmp = a6;
            a6 = a8;
            a8 = tmp;
        }
        if (a7 > a9) {
            tmp = a7;
            a7 = a9;
            a9 = tmp;
        }
        if (a10 > a12) {
            tmp = a10;
            a10 = a12;
            a12 = tmp;
        }
        if (a3 > a4) {
            tmp = a3;
            a3 = a4;
            a4 = tmp;
        }
        if (a5 > a6) {
            tmp = a5;
            a5 = a6;
            a6 = tmp;
        }
        if (a7 > a8) {
            tmp = a7;
            a7 = a8;
            a8 = tmp;
        }
        if (a9 > a10) {
            tmp = a9;
            a9 = a10;
            a10 = tmp;
        }
        if (a11 > a12) {
            tmp = a11;
            a11 = a12;
            a12 = tmp;
        }
        if (a6 > a7) {
            tmp = a6;
            a6 = a7;
            a7 = tmp;
        }
        if (a8 > a9) {
            tmp = a8;
            a8 = a9;
            a9 = tmp;
        }
        indices[0] = (int) (a0 & 31);
        indices[1] = (int) (a1 & 31);
        indices[2] = (int) (a2 & 31);
        indices[3] = (int) (a3 & 31);
        indices[4] = (int) (a4 & 31);
        indices[5] = (int) (a5 & 31);
        indices[6] = (int) (a6 & 31);
        indices[7] = (int) (a7 & 31);
        indices[8] = (int) (a8 & 31);
        indices[9] = (int) (a9 & 31);
        indices[10] = (int) (a10 & 31);
        indices[11] = (int) (a11 & 31);
        indices[12] = (int) (a12 & 31);
        indices[13] = (int) (a13 & 31);
        indices[14] = (int) (a14 & 31);
        indices[15] = (int) (a15 & 31);
    }

}
