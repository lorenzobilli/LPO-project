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
            return new MoreStatement(statement, parseStatementSequence());
        }
        return new SingleStatement(statement);
    }

    private ExpressionSequence parseExpressionSequence() throws IOException, ScannerException, ParserException {
        Expression expression = parseExpression();
        if (tokenizer.getTokenType() == EXPRESSION_SEP) {
            tokenizer.next();
            return new MoreExpression(expression, parseExpressionSequence());
        }
        return new SingleExpression(expression);
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
            case IF:
                return parseIfStatement();
            case WHILE:
                return parseWhileStatement();
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
        consume(START_BLOCK);
        StatementSequence statementSequence = parseStatementSequence();
        consume(END_BLOCK);
        return new ForEachStatement(identity, expression, statementSequence);
    }

    private IfStatement parseIfStatement() throws ScannerException, ParserException, IOException {
        consume(IF);
        consume(OPEN_PAR);
        Expression expression = parseExpression();
        consume(CLOSED_PAR);
        consume(START_BLOCK);
        StatementSequence ifStatementSequence = parseStatementSequence();
        consume(END_BLOCK);
        consume(ELSE);
        consume(START_BLOCK);
        StatementSequence elseStatementSequence = parseStatementSequence();
        consume(END_BLOCK);
        return new IfStatement(expression, ifStatementSequence, elseStatementSequence);
    }

    private WhileStatement parseWhileStatement() throws ScannerException, ParserException, IOException {
        consume(WHILE);
        consume(OPEN_PAR);
        Expression expression = parseExpression();
        consume(CLOSED_PAR);
        consume(START_BLOCK);
        StatementSequence statementSequence = parseStatementSequence();
        consume(END_BLOCK);
        return new WhileStatement(expression, statementSequence);
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
        Expression expression = parseConcatenate();
        while (tokenizer.getTokenType() == LESSTHAN) {
            tokenizer.next();
            expression = new LessThan(expression, parseConcatenate());
        }
        return expression;
    }

    private Expression parseConcatenate() throws ScannerException, ParserException, IOException {
        Expression expression = parseAddAndSubtract();
        while (tokenizer.getTokenType() == CONCATENATE) {
            tokenizer.next();
            expression = new Concatenate(expression, parseAddAndSubtract());
        }
        return expression;
    }

    private Expression parseAddAndSubtract() throws IOException, ScannerException, ParserException {
        Expression expression = parseTimesAndFraction();
        while (tokenizer.getTokenType() == PLUS || tokenizer.getTokenType() == MINUS) {     //TODO: unary op conflict?
            switch (tokenizer.getTokenType()) {
                case PLUS:
                    expression = new Add(expression, parseTimesAndFraction());
                    break;
                case MINUS:
                    expression = new Subtract(expression, parseTimesAndFraction());
                    break;
            }
            tokenizer.next();
        }
        return expression;
    }

    private Expression parseTimesAndFraction() throws IOException, ScannerException, ParserException {
        Expression expression = parseAtom();
        while (tokenizer.getTokenType() == TIMES || tokenizer.getTokenType() == FRACTION) {
            switch (tokenizer.getTokenType()) {
                case TIMES:
                    expression = new Multiply(expression, parseAtom());
                    break;
                case FRACTION:
                    expression = new Divide(expression, parseAtom());
                    break;
            }
            tokenizer.next();
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
            case SIGN:
                return parseSign();
            case POP:
                return parsePop();
            case TOP:
                return parseTop();
            case PUSH:
                return parsePush();
            case START_LIST:
                return parseList();
            case OPEN_PAR:
                tokenizer.next();
                Expression expression = parseExpression();
                consume(CLOSED_PAR);
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

    private Sign parseSign() throws ScannerException, ParserException, IOException {
        consume(SIGN);
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
        consume(OPEN_PAR);
        Expression left = parseExpression();
        consume(EXPRESSION_SEP);
        Expression right = parseExpression();
        consume(CLOSED_PAR);
        return new Push(left, right);
    }

    private List parseList() throws ScannerException, ParserException, IOException {
        consume(START_LIST);
        ExpressionSequence expressionSequence = parseExpressionSequence();
        consume(END_LIST);
        return new List(expressionSequence);
    }

    private void unexpectedTokenError() throws ParserException {
        throw new ParserException(
                "Unexpected token " + tokenizer.getTokenType() + " ('" + tokenizer.getTokenString() + "')"
        );
    }

    private void match(TokenType expectedToken) throws ParserException {
        final TokenType foundToken = tokenizer.getTokenType();
        if (foundToken != expectedToken) {
            throw new ParserException(
                    "Expecting " + expectedToken + ", found " + foundToken + " ('" + tokenizer.getTokenString() + "')"
            );
        }
    }

    private void consume(TokenType expectedToken) throws ParserException, IOException, ScannerException {
        match(expectedToken);
        tokenizer.next();
    }
}
