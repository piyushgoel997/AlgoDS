package RegEx.Applications;

/**
 * Created by piyus on 23-07-2017 at 12:40.
 * Used to check the validity of the string(for ex.- email address, url, etc.) using the given Regex.
 * Uses the matches() function available in the Java implementation of String.
 */
public class Validate {
    public static void main(String[] args) {
        String regex = args[0];
        String text = args[1];
        System.out.println(text.matches(regex));
    }
}
