package StringProcessing;

/**
 * Created by piyus on 01-07-2017 at 14:21.
 */
public class Sorts {

    private static int R = 256;  // assuming 8-bit ASCII


    // LSD string (radix) sort - (Least Significant Digit first)
    public static void LSD(String[] a, int W) {
        int N = a.length;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            // sort the dth chars of the string (using key indexed counting)

            int[] count = new int[R + 1];
            // setting counts
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            // computing the frequency cumulates
            for (int i = 0; i < R; i++) {
                count[i + 1] += count[i];
            }
            // storing the sorted values in the aux array
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            // copying the elements from the aux[] to the original array
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }


    // MSD string (radix) sort - (Most Significant Digit first)
    public static void MSD(String[] a) {
        String[] aux = new String[a.length];
        msd(a, aux, 0, a.length, 0);
    }

    /**
     * @param a : the array to be sorted
     * @param aux : auxiliary array. This needs to be passed because it needs to be the same of each iteration/recursion.
     * @param low : the starting idx of the sub array
     * @param high : the stopping idx of the sub array
     * @param d : the position of the character being currently sorted.
     */
    private static void msd(String[] a, String[] aux, int low, int high, int d) {
        if (high <= low) return;
        int[] count = new int[R + 2]; // here we take (R+2) instead of (R+1) to account for the -1 in the end of the strings.

        // sort the dth chars of the string (using key indexed counting)

        for (int i = low; i < high; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int i = 0; i < count.length - 1; i++) {
            count[i + 1] += count[i];
        }
        for (int i = low; i < high; i++) {
            aux[count[a[i].charAt(d) + 1]++] = a[i];
        }
        for (int i = low; i < high; i++) {
            a[i] = aux[i];
        }

        // sort the R sub arrays
        for (int i = 0; i < R; i++) {
            msd(a, aux, low + count[i], low + count[i + 1], d + 1);
        }
    }

    private static int charAt(String s, int d) {
        if (d >= s.length()) {
            return -1;
        }
        return s.charAt(d);
    }


    // 3 way Radix Quick Sort (combines the benefits of both MSD and quick sort)
    public static void radixQuick(String[] a) {
        radixQuick(a, 0, a.length - 1, 0);
    }

    private static void radixQuick(String[] a, int low, int high, int d) {
        if (high <= low) return;
        int l = low; // stop of the arr with smaller elements
        int h = high; // start of the array with greater elements
        int i = low + 1; // current moving pointer
        int p = charAt(a[l], d); // pivot element
        while (i <= h) {
            int curr = charAt(a[i], d);
            if (curr < p) exch(a, i++, l++);
            else if (curr > p) exch(a, i, h--); //Since, we haven't yet checked the 'h--' element. So,  we don't do i++ here so it might be checked in the next iteration.
            else i++;
        }
        radixQuick(a, low, l - 1, d);
        if (p >= 0)radixQuick(a, l, h, d + 1); // no need to sort further if p = -1
        radixQuick(a, h + 1, high, d);
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
