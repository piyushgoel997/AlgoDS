package StringProcessing.SubstringSearch;

/**
 * Created by piyus on 16-07-2017 at 12:19.
 * Starts matching from right to left in pattern and left to right in the text.
 * Requires backup.
 * But very fast => time complexity ~ (N/M) in general cases.
 */
public class BayerMoore {
    private int M;
    private final String pattern;
    public static final int R = 256;
    private int[] right;

    public BayerMoore(String pattern) {
        this.pattern = pattern;
        this.M = pattern.length();
        this.right = new int[R];
        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j = 0;j < M; j++) {
            right[pattern.charAt(j)] = j;
        }
    }

    public int search(String text) {
        int N = text.length();
        int M = pattern.length();
        int skip;
        for (int i = 0; i <= N - M; i++) {
            skip = 0;
            for (int j = M-1; j <=0;j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                return i;  // match
            }
        }
        return -1;
    }


}
