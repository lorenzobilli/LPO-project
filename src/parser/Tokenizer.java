package parser;

import static parser.TokenType.*;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Tokenizer class
 */
public class Tokenizer implements AutoCloseable {

    private static final Pattern regEx;
    private static final Map<String, TokenType> keywords = new HashMap<>();
    private static final Map<String, TokenType> symbols = new HashMap<>();

    private final Scanner scanner;
    private boolean hasNext = true;
    private TokenType tokenType;
    private String tokenString;
    private boolean boolValue;
    private int intValue;

    static {
        final Pattern identRegEx = Pattern.compile("([a-zA-Z][a-zA-Z0-9]*)");
        final Pattern numRegEx = Pattern.compile("(0|[1-9][0-9]*|0[1-7][0-7]*)");
        final Pattern skipRegEx = Pattern.compile("(\\s+|//.*)");
        final Pattern symbolRegEx = Pattern.compile("\\+|-|\\*|/|=|@|\\(|\\)|\\[|]|\\{|}|,|;");
        regEx = Pattern.compile(identRegEx + "|" + numRegEx + "|" + skipRegEx + "|" + symbolRegEx);
    }

    static {
        keywords.put("var", VAR);
        keywords.put("if", IF);
        keywords.put("else", ELSE);
        keywords.put("while", WHILE);
        keywords.put("for", FOR);
        keywords.put("in", IN);
        keywords.put("true", BOOL);
        keywords.put("false", BOOL);
        keywords.put("print", PRINT);
        keywords.put("length", LENGTH);
        keywords.put("pair", PAIR);
        keywords.put("fst", FIRST);
        keywords.put("snd", SECOND);
        keywords.put("push", PUSH);
        keywords.put("pop", POP);
        keywords.put("top", TOP);
    }

    static {
        symbols.put("+", PLUS);
        symbols.put("-", MINUS);
        symbols.put("*", TIMES);
        symbols.put("/", FRACTION);
        symbols.put("=", ASSIGN);
        symbols.put("@", CONCATENATE);
        symbols.put("!", NOT);
        symbols.put("(", PARENTHESIS_OPEN);
        symbols.put(")", PARENTHESIS_CLOSED);
        symbols.put("[", BRACKETS_OPEN);
        symbols.put("]", BRACKETS_CLOSED);
        symbols.put("{", BRACES_OPEN);
        symbols.put("}", BRACES_CLOSED);
        symbols.put(",", COMMA);
        symbols.put(";", STATEMENT_SEP);
    }

    public Tokenizer(Reader reader) {
        scanner = new Scanner(regEx, reader);
    }

    private void checkType() {
        tokenString = scanner.group();
        // Searching for IDENT, BOOL or keywords
        if (scanner.group(IDENT.ordinal()) != null) {
            tokenType = keywords.get(tokenString);
            if (tokenType == null) {
                tokenType = IDENT;
            }
            if (tokenType == BOOL) {
                boolValue = Boolean.parseBoolean(tokenString);
            }
            return;
        }
        // Searching for NUM
        if (scanner.group(NUM.ordinal()) != null) {
            tokenType = NUM;
            intValue = Integer.parseInt(tokenString);
            return;
        }
        // SKIP token
        if (scanner.group(SKIP.ordinal()) != null) {
            tokenType = SKIP;
            return;
        }
        // Only symbols are left...
        tokenType = symbols.get(tokenString);
        if (tokenType == null) {
            throw new AssertionError("Unrecognised token");     //TODO: Consider a TokenizerException
        }
    }

    public TokenType next() throws IOException, ScannerException {
        do {
            tokenType = null;
            tokenString = "";
            if (hasNext && !scanner.hasNext()) {
                hasNext = false;
                return tokenType = EOF;
            }
            scanner.next();
            checkType();
        } while (tokenType == SKIP);
        return tokenType;
    }

    private void checkValidToken() {
        if (tokenType == null) {
            throw new IllegalStateException();
        }
    }

    private void checkValidToken(TokenType tokenType) {
        if (this.tokenType != tokenType) {
            throw new IllegalStateException();
        }
    }

    public String getTokenString() {
        checkValidToken();
        return tokenString;
    }

    public int getIntValue() {
        checkValidToken(NUM);
        return intValue;
    }

    public boolean getBoolValue() {
        checkValidToken(BOOL);
        return boolValue;
    }

    public TokenType getTokenType() {
        checkValidToken();
        return tokenType;
    }

    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
