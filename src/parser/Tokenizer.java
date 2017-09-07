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
        // Group number 1: regular expression for identities
        final Pattern identRegEx = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]*");
        // Group number 2: regular expression for numbers
        final Pattern numRegEx = Pattern.compile("0|[1-9][0-9]*|0[1-7][0-7]*");
        // Group number 3: regular expression for skipped characters
        final Pattern skipRegEx = Pattern.compile("\\s+|//.*");
        // Group number 4: regular expression for symbols
        final Pattern symbolRegEx = Pattern.compile("\\|\\||&&|==|\\+|-|\\*|=|@|\\(|\\)|;|,|\\{|}|<|-[0-9]+|!|\\[|]");

        //TODO: Add remaining symbols to symbolRegEx
        /*
        Original (wrong) regex: "\\+|-|\\*|/|=|@|&&|\\|\\||!|==|<|\\(|\\)|\\[|]|\\{|}|,|;"
        */

        regEx = Pattern.compile("(" + identRegEx + ")|(" + numRegEx + ")|(" + skipRegEx + ")|(" + symbolRegEx + ")");
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
        keywords.put("fst", FST);
        keywords.put("snd", SND);
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
        symbols.put("&&", AND);
        symbols.put("||", OR);
        symbols.put("!", NOT);
        symbols.put("-", SIGN);
        symbols.put("==", EQUAL);
        symbols.put("<", LESSTHAN);
        symbols.put("(", OPEN_PAR);
        symbols.put(")", CLOSED_PAR);
        symbols.put("[", START_LIST);
        symbols.put("]", END_LIST);
        symbols.put("{", START_BLOCK);
        symbols.put("}", END_BLOCK);
        symbols.put(",", EXPRESSION_SEP);
        symbols.put(";", STATEMENT_SEP);
    }

    public Tokenizer(Reader reader) {
        scanner = new Scanner(regEx, reader);
    }

    private void checkType() throws TokenizerException {
        tokenString = scanner.group();
        // Searching for IDENT, BOOL or keywords inside group 2
        if (scanner.group(1) != null) {
            tokenType = keywords.get(tokenString);
            if (tokenType == null) {
                tokenType = IDENT;
            }
            if (tokenType == BOOL) {
                boolValue = Boolean.parseBoolean(tokenString);
            }
            return;
        }
        // Searching for NUM tokens inside group 2
        if (scanner.group(2) != null) {
            tokenType = NUM;
            intValue = Integer.parseInt(tokenString);
            return;
        }
        // Searching for SKIP tokens inside group 3
        if (scanner.group(3) != null) {
            tokenType = SKIP;
            return;
        }
        // Only symbols are left (group 4), so token should be a symbol...
        tokenType = symbols.get(tokenString);
        if (tokenType == null) {
            throw new TokenizerException("Unrecognised token");
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
            try {
                checkType();
            } catch (TokenizerException e) {
                e.getMessage();
                e.getCause();
                e.printStackTrace();
            }
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

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
