package typechecker;

import common.Environment;
import common.Visitor;
import common.ast.*;

import static typechecker.PrimitiveType.BOOL;
import static typechecker.PrimitiveType.INT;

/**
 * TypeChecker class
 */
public class TypeChecker implements Visitor<Type> {

    private final Environment<Type> staticEnvironment = new Environment<>();

    @Override
    public Type visitProgram(StatementSequence statementSequence) {
        statementSequence.accept(this);
        return null;
    }

    @Override
    public Type visitMoreStatement(Statement first, StatementSequence rest) {
        first.accept(this);
        rest.accept(this);
        return null;
    }

    @Override
    public Type visitSingleStatement(Statement statement) {
        statement.accept(this);
        return null;
    }

    @Override
    public Type visitMoreExpression(Expression first, ExpressionSequence rest) {
        Type expected = first.accept(this);
        return rest.accept(this).checkEqual(expected);
    }

    @Override
    public Type visitSingleExpression(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public Type visitOr(Expression left, Expression right) {
        checkBinaryOperator(left, right, BOOL);
        return BOOL;
    }

    @Override
    public Type visitAnd(Expression left, Expression right) {
        checkBinaryOperator(left, right, BOOL);
        return BOOL;
    }

    @Override
    public Type visitEqual(Expression left, Expression right) {
        Type expected = left.accept(this);
        right.accept(this).checkEqual(expected);
        return BOOL;
    }

    @Override
    public Type visitLessThan(Expression left, Expression right) {
        checkBinaryOperator(left, right, INT);
        return BOOL;
    }

    @Override
    public Type visitConcatenate(Expression left, Expression right) {
        return left.accept(this).checkEqual(right.accept(this));
    }

    @Override
    public Type visitAdd(Expression left, Expression right) {
        checkBinaryOperator(left, right, INT);
        return INT;
    }

    @Override
    public Type visitSubtract(Expression left, Expression right) {
        checkBinaryOperator(left, right, INT);
        return INT;
    }

    @Override
    public Type visitMultiply(Expression left, Expression right) {
        checkBinaryOperator(left, right, INT);
        return INT;
    }

    @Override
    public Type visitDivide(Expression left, Expression right) {
        checkBinaryOperator(left, right, INT);
        return INT;
    }

    @Override
    public Type visitIntegerLiteral(int value) {
        return INT;
    }

    @Override
    public Type visitBooleanLiteral(boolean value) {
        return BOOL;
    }

    @Override
    public Type visitIdentity(String name) {
        return staticEnvironment.lookup(new SimpleIdentity(name));
    }

    @Override
    public Type visitNot(Expression expression) {
        return expression.accept(this).checkEqual(BOOL);
    }

    @Override
    public Type visitSign(Expression expression) {
        return expression.accept(this).checkEqual(INT);
    }

    @Override
    public Type visitTop(Expression expression) {
        return expression.accept(this).checkList();
    }

    @Override
    public Type visitPop(Expression expression) {
        Type type = expression.accept(this);
        type.checkList();
        return type;
    }

    @Override
    public Type visitPush(Expression left, Expression right) {
        Type type = left.accept(this);
        return right.accept(this).checkEqual(new ListType(type));
    }

    @Override
    public Type visitLength(Expression expression) {
        Type type = expression.accept(this);
        type.checkList();
        return INT;
    }

    @Override
    public Type visitPair(Expression left, Expression right) {
        return new CoupleType(left.accept(this), right.accept(this));
    }

    @Override
    public Type visitFst(Expression expression) {
        return expression.accept(this).checkCouple();
    }

    @Override
    public Type visitSnd(Expression expression) {
        return expression.accept(this).checkCouple();
    }

    @Override
    public Type visitListLiteral(ExpressionSequence expressionSequence) {
        return new ListType(expressionSequence.accept(this));
    }

    @Override
    public Type visitPrintStatement(Expression expression) {
        expression.accept(this);
        return null;
    }

    @Override
    public Type visitVarStatement(Identity identity, Expression expression) {
        staticEnvironment.newFresh(identity, expression.accept(this));
        return null;
    }

    @Override
    public Type visitAssignStatement(Identity identity, Expression expression) {
        Type expected = staticEnvironment.lookup(identity);
        expression.accept(this).checkEqual(expected);
        return null;
    }

    @Override
    public Type visitForEachStatement(Identity identity, Expression expression, StatementSequence block) {
        Type type = expression.accept(this).checkList();
        staticEnvironment.enterScope();
        staticEnvironment.newFresh(identity, type);
        block.accept(this);
        staticEnvironment.exitScope();
        return null;
    }

    @Override
    public Type visitIfStatement(Expression expression, StatementSequence ifBlock, StatementSequence elseBlock) {
        staticEnvironment.enterScope();
        ifBlock.accept(this);
        staticEnvironment.exitScope();
        staticEnvironment.enterScope();
        elseBlock.accept(this);
        staticEnvironment.exitScope();
        return null;
    }

    @Override
    public Type visitWhileStatement(Expression expression, StatementSequence block) {
        staticEnvironment.enterScope();
        block.accept(this);
        staticEnvironment.exitScope();
        return null;
    }

    private void checkBinaryOperator(Expression left, Expression right, Type type) {
        left.accept(this).checkEqual(type);
        right.accept(this).checkEqual(type);
    }
}
