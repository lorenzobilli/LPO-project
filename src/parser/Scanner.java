package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Scanner class
 */
public class Scanner implements AutoCloseable {
    private final Matcher matcher;
    private final BufferedReader bufferedReader;
    private MatchResult result;
    private String line;

    public Scanner(String regex, Reader reader) {
        matcher = Pattern.compile(regex).matcher("");
        result = Pattern.compile("").matcher("").toMatchResult();
        bufferedReader = new BufferedReader(reader);
    }

    public void next() throws IOException, ScannerException {
        if (!hasNext()) {
            throw new ScannerException("Unexpected end of input stream");
        }
        boolean matched = matcher.lookingAt();
        result = matcher.toMatchResult();
        if (!matched) {
            throw new ScannerException("Unrecognised string ", skip());
        } else {
            matcher.region(matcher.end(), matcher.regionEnd());
        }
    }

    private boolean hasNext() throws IOException {
        if (matcher.regionStart() == matcher.regionEnd()) {
            line = bufferedReader.readLine();
            if (line == null) {
                matcher.reset("");
                return false;
            }
            matcher.reset(line + " ");
        }
        return true;
    }

    private String skip() {
        String skipped;
        if (matcher.find()) {
            skipped = line.substring(matcher.regionStart(), matcher.start());
            matcher.region(matcher.start(), matcher.regionEnd());
        } else {
            skipped = line.substring(matcher.regionStart(), matcher.regionEnd());
            matcher.region(matcher.regionEnd(), matcher.regionEnd());
        }
        return skipped;
    }

    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
