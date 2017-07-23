package RegEx.Applications;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by piyus on 23-07-2017 at 12:17.
 *
 * Makes use of java.util.regex.Pattern for making State machine for the pattern
 * and java.util.regex.Matcher for finding the matching strings in the text.
 */
public class Harvester {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String regEx = s.nextLine();
        String text = "";
        while (s.hasNextLine()) {
            String curr = s.nextLine();
            if (curr.equals("")) {
                break;
            }
            text += curr;
        }
        Pattern pattern = Pattern.compile(regEx);  // created NFA from the RegEx.
        Matcher matcher = pattern.matcher(text);  // creates an NFA Simulator.
        while (matcher.find()) {  // find() looks for the next match in the text.
            System.out.println(matcher.group());  // returns the most recent substring match found by find().
        }
    }

}
