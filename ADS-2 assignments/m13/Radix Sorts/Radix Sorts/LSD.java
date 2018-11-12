/**
 * Class for lsd.
 */
public class LSD {
    /**
     * { var_description }.
     */
    private static final int BITS_PER_BYTE = 8;

    /**
     * Constructs the object.
     */
    protected LSD() {
        //empty constructor.
    }


    /**
      * Rearranges the array of W-character strings in ascending order.
      *
      * @param a the array to be sorted
      * @param w the number of characters per string
      */
    public static void sort(final String[] a, final int w) {
        final int value = 256;
        int n = a.length;
        int radix = value;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[radix + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < radix; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
      * Rearranges the array of 32-bit integers in ascending order.
      * This is about 2-3x faster than Arrays.sort().
      *
      * @param a the array to be sorted
      */
    public static void sort(final int[] a) {
        final int bits = 32;                 // each int is 32 bits
        final int radix = 1 << BITS_PER_BYTE; // each bytes is between 0 and 255
        final int mask = radix - 1;              // 0xFF
        final int w = bits / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[radix + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < radix; r++) {
                count[r + 1] += count[r];
            }

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[radix] - count[radix / 2];
                int shift2 = count[radix / 2];
                for (int r = 0; r < radix / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = radix / 2; r < radix; r++) {
                    count[r] -= shift2;
                }
            }

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
