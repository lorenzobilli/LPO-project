package parser;

import static parser.TokenType.*;

import common.ast.*;

import java.io.IOException;

/**
 * Parser class
 */
public class Parser {

    private final Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public Prog parseProgram() throws IOException, ScannerException, ParserException {
        tokenizer.next();
        Prog program = new Program(parseStatementSequence());
        match(EOF);
        return program;
    }

    private StatementSequence parseStatementSequence() throws IOException, ScannerException, ParserException {
        Statement statement = parseStatement();
        if (tokenizer.getTokenType() == STATEMENT_SEP) {
            tokenizer.next();
            return new MoreStatement(statement, parseStatementSequence());      //TODO: implement MoreStatement
        }
        return new SingleStatement(statement);      //TODO: implement SingleStatement
    }

    private Statement parseStatement() throws ParserException, IOException, ScannerException {
        switch (tokenizer.getTokenType()) {     //TODO: handle additional statement types (all other keywords)
            default:
                unexpectedTokenError();
            case PRINT:
                return parsePrintStatement();
            case VAR:
                return parseVarStatement();
            case IDENT:
                return parseAssignStatement();
            case FOR:
                return parseForEachStatement();
        }
    }

    private PrintStatement parsePrintStatement() throws ScannerException, ParserException, IOException {
        consume(PRINT);
        return new PrintStatement(parseExpression());
    }

    private VarStatement parseVarStatement() throws ScannerException, ParserException, IOException {
        consume(VAR);
        Identity identity = parseIdentity();
        consume(ASSIGN);
        return new VarStatement(identity, parseExpression());
    }

    private AssignStatement parseAssignStatement() throws ScannerException, ParserException, IOException {
        Identity identity = parseIdentity();
        consume(ASSIGN);
        return new AssignStatement(identity, parseExpression());
    }

    private ForEachStatement parseForEachStatement() throws ScannerException, ParserException, IOException {
        consume(FOR);
        Identity identity = parseIdentity();
        consume(IN);
        Expression expression = parseExpression();
        consume(BRACES_OPEN);   //TODO: Consider renaming
        StatementSequence statementSequence = parseStatementSequence();
        consume(BRACES_CLOSED);
        return new ForEachStatement(identity, expression, statementSequence);
    }

    private ExpressionSequence parseExpressionSequence() throws IOException, ScannerException, ParserException {
        Expression expression = parseExpression();
        if (tokenizer.getTokenType() == COMMA) {    //TODO: consider renaming COMMA to EXPRESSION_SEP
            tokenizer.next();
            return new MoreExpression(expression, parseExpressionSequence());
        }
        return new SingleExpression(expression);
    }

    private Expression parseExpression() throws IOException, ScannerException, ParserException {
        Expression expression = parseAnd();
        while (tokenizer.getTokenType() == OR) {
            tokenizer.next();
            expression = new Or(expression, parseAnd());
        }
        return expression;
    }

    private Expression parseAnd() throws IOException, ScannerException, ParserException {
        Expression expression = parseEqual();
        while (tokenizer.getTokenType() == AND) {
            tokenizer.next();
            expression = new And(expression, parseEqual());
        }
        return expression;
    }

    private Expression parseEqual() throws IOException, ScannerException, ParserException {
        Expression expression = parseLessThan();
        while (tokenizer.getTokenType() == EQUAL) {
            tokenizer.next();
            expression = new Equal(expression, parseLessThan());
        }
        return expression;
    }

    private Expression parseLessThan() throws IOException, ScannerException, ParserException {
        Expression expression = parseAdd();
        while (tokenizer.getTokenType() == LESSTHAN) {
            tokenizer.next();
            expression = new LessThan(expression, parseAdd());
        }
        return expression;
    }

    private Expression parseAdd() throws IOException, ScannerException, ParserException {
        Expression expression = parseTimes();
        while (tokenizer.getTokenType() == PLUS) {
            tokenizer.next();
            expression = new Add(expression, parseTimes());
        }
        return expression;
    }

    private Expression parseTimes() throws IOException, ScannerException, ParserException {
        Expression expression = parseAtom();
        while (tokenizer.getTokenType() == TIMES) {
            tokenizer.next();
            expression = new Multiply(expression, parseAtom());
        }
        return expression;
    }

    private Expression parseAtom() throws ParserException, IOException, ScannerException {
        switch (tokenizer.getTokenType()) {
            default:
                unexpectedTokenError();
            case NUM:
                return parseNumber();
            case BOOL:
                return parseBoolean();
            case IDENT:
                return parseIdentity();
            case NOT:
                return parseNot();
            case MINUS:
                return parseMinus();
            case POP:
                return parsePop();
            case TOP:
                return parseTop();
            case PUSH:
                return parsePush();
            case BRACKETS_OPEN:
                return parseList();
            case PARENTHESIS_OPEN:
                tokenizer.next();
                Expression expression = parseExpression();
                consume(PARENTHESIS_CLOSED);
                return expression;
        }
    }

    private IntegerLiteral parseNumber() throws ScannerException, ParserException, IOException {
        int value = tokenizer.getIntValue();
        consume(NUM);
        return new IntegerLiteral(value);
    }

    private PrimaryLiteral<Boolean> parseBoolean() throws ScannerException, ParserException, IOException {
        boolean value = tokenizer.getBoolValue();
        consume(BOOL);
        return new BooleanLiteral(value);
    }

    private Identity parseIdentity() throws ScannerException, ParserException, IOException {
        String name = tokenizer.getTokenString();
        consume(IDENT);
        return new SimpleIdentity(name);
    }

    private Not parseNot() throws ScannerException, ParserException, IOException {
        consume(NOT);
        return new Not(parseAtom());
    }

    private Sign parseMinus() throws ScannerException, ParserException, IOException {
        consume(MINUS);
        return new Sign(parseAtom());
    }

    private Top parseTop() throws ScannerException, ParserException, IOException {
        consume(TOP);
        Expression expression = parseAtom();
        return new Top(expression);
    }

    private Pop parsePop() throws ScannerException, ParserException, IOException {
        consume(POP);
        Expression expression = parseAtom();
        return new Pop(expression);
    }

    private Push parsePush() throws ScannerException, ParserException, IOException {
        consume(PUSH);
        consume(PARENTHESIS_OPEN);
        Expression left = parseExpression();
        consume(COMMA);     //TODO: Consider renaming
        Expression right = parseExpression();
        consume(PARENTHESIS_CLOSED);
        return new Push(left, right);
    }

    private List parseList() throws ScannerException, ParserException, IOException {
        consume(BRACKETS_OPEN);     //TODO: Consider renaming
        ExpressionSequence expressionSequence = parseExpressionSequence();
        consume(BRACKETS_CLOSED);      //TODO: Consider renaming
        return new List(expressionSequence);
    }

    private void unexpectedTokenError() throws ParserException {
        throw new ParserException(
                "Unexpected token " + tokenizer.getTokenType() + "('" + tokenizer.getTokenString() + "')"
        );
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
