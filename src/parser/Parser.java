package parser;

import java.io.IOException;

/**
 * Parser class
 */
public class Parser {

    private final Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    private void match(TokenType expectedToken) throws ParserException {
        final TokenType foundToken = tokenizer.getTokenType();
        if (foundToken != expectedToken) {
            throw new ParserException(
                    "Expecting " + expectedToken + ", found " + foundToken + "('" + tokenizer.getTokenString() + "')"
            );
        }
    }

    private void consume(TokenType expectedToken) throws ParserException, IOException, ScannerException {
        match(expectedToken);
        tokenizer.next();
    }
}
