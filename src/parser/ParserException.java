package parser;

/**
 * ParserException class
 */
public class ParserException extends Exception {
    public ParserException() {
        super("Parsing error");
    }

    public ParserException(String message) {
        super("Parsing error: " + message);
    }
}
