package Misc;

import java.util.Scanner;

/**
 * Created by piyus on 22-05-2017 at 12:30.
 * Needle in a Haystack
 */
public class strstr {
    private static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() < needle.length()) {
            // invalid cases
            return -2;
        }

        if (needle.length() == 0) {
            return 0;
        }

        int n = needle.length();
        int h = haystack.length();

        for (int i = 0; i < h - n + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int k = 1;
                while (k < n && haystack.charAt(i + k) == needle.charAt(k)) {
                    k++;
                }
                if (k == n) {
                    return i;
                }
            }
        }
        // not found
        return -1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String h = s.nextLine();
        String n = s.nextLine();
        System.out.println(strStr(h,n));
    }
}
