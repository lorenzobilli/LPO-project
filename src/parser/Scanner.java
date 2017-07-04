package parser;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Scanner class
 */
public class Scanner {
    private final Matcher matcher;
    private final BufferedReader bufferedReader;
    private MatchResult result;
    private String line;

    public Scanner(String regex, Reader reader) {
        matcher = Pattern.compile(regex).matcher("");
        result = Pattern.compile("").matcher("").toMatchResult();
        bufferedReader = new BufferedReader(reader);
    }
}
