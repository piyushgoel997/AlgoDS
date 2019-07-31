package RegEx.Applications;

import RegEx.NFA;

import java.util.Scanner;

/**
 * Created by piyus on 23-07-2017 at 12:14.
 * RegEx searching algorithm used in unix based systems.
 * Makes the use of regex matching function in the Java String class matches().
 */
public class GREP {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String regex = "(.*" + s.nextLine() + ".*)";
        NFA nfa = new NFA(regex);  // using the self-made NFA class.
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (nfa.recognizes(line)) {
                System.out.println(line);
            }
        }
    }
}
