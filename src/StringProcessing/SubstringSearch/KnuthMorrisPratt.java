package StringProcessing.SubstringSearch;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by piyus on 11-07-2017 at 15:14.
 * Uses State Machine to search for the substring looking at one char at a time without any backing up.
 */
public class KnuthMorrisPratt {

    public static final int NOT_FOUND = -1;
    private final int M;

    private static int[][] dfa;
    private final String pattern;
    private static final int R = 256;

    public KnuthMorrisPratt(String pattern) {
        this.pattern = pattern;
        this.M = pattern.length();
        // dfa building
        dfa = new int[R][M];
        dfa[pattern.charAt(0)][0] = 1;
        int X = 0;
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < R; j++) {  // copying mismatch cases.
                dfa[j][i] = dfa[j][X];
            }
            dfa[pattern.charAt(i)][i] = i + 1;  // setting next state for matching cases
            X = dfa[pattern.charAt(i)][X];  // updating X.
        }
        System.out.println();
    }


    public int search(InputStream in) throws IOException {
        int j = 0;  // represents the current state
        int i = 0;  // idx in the text at which the pattern is found
        int r;
        while (j<M &&(r = in.read())!=-1) {
            j = dfa[(char) r][j];  // the next state to go to if currently in j'th state and the next text char is r
            i++;
        }
        in.close();
        if (j == M) return i - M;
        else return NOT_FOUND;
    }

    public static void main(String[] args) {
        KnuthMorrisPratt kmp = new KnuthMorrisPratt("ABABAC");
        InputStream is = new ByteArrayInputStream("AABABABABAC".getBytes(StandardCharsets.US_ASCII));
        try {
            System.out.println(kmp.search(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
