package evaluator;

import common.Environment;
import common.Visitor;
import common.ast.*;

/**
 * Evaluator class
 */
public class Evaluator implements Visitor<Value> {

    private final Environment<Value> dynamicEnvironment = new Environment<>();

    @Override
    public Value visitMoreStatement(Statement first, StatementSequence rest) {
        first.accept(this);
        rest.accept(this);
        return null;
    }

    @Override
    public Value visitSingleStatement(Statement statement) {
        statement.accept(this);
        return null;
    }

    @Override
    public Value visitMoreExpression(Expression first, ExpressionSequence rest) {
        return new LinkedListValue(first.accept(this), rest.accept(this).asList());
    }

    @Override
    public Value visitSingleExpression(Expression expression) {
        return new LinkedListValue(expression.accept(this), new LinkedListValue());
    }

    @Override
    public Value visitOr(Expression left, Expression right) {
        return new BoolValue(left.accept(this).asBoolean() || right.accept(this).asBoolean());
    }

    @Override
    public Value visitAnd(Expression left, Expression right) {
        return new BoolValue(left.accept(this).asBoolean() && right.accept(this).asBoolean());
    }

    @Override
    public Value visitEqual(Expression left, Expression right) {
        return new BoolValue(left.accept(this).equals(right.accept(this)));
    }

    @Override
    public Value visitLessThan(Expression left, Expression right) {
        return new BoolValue(left.accept(this).asInt() < right.accept(this).asInt());
    }

    @Override
    public Value visitAdd(Expression left, Expression right) {
        return new IntValue(left.accept(this).asInt() + right.accept(this).asInt());
    }

    @Override
    public Value visitMultiply(Expression left, Expression right) {
        return new IntValue(left.accept(this).asInt() * right.accept(this).asInt());
    }

    @Override
    public Value visitIntegerLiteral(int value) {
        return new IntValue(value);
    }

    @Override
    public Value visitBooleanLiteral(boolean value) {
        return new BoolValue(value);
    }

    @Override
    public Value visitIdentity(String name) {
        return dynamicEnvironment.lookup(new SimpleIdentity(name));
    }

    @Override
    public Value visitNot(Expression expression) {
        return new BoolValue(!expression.accept(this).asBoolean());
    }

    @Override
    public Value visitSign(Expression expression) {
        return new IntValue(-expression.accept(this).asInt());
    }

    @Override
    public Value visitTop(Expression expression) {
        return expression.accept(this).asList().top();
    }

    @Override
    public Value visitPop(Expression expression) {
        return expression.accept(this).asList().pop();
    }

    @Override
    public Value visitListLiteral(ExpressionSequence expressionSequence) {
        return expressionSequence.accept(this);
    }

    @Override
    public Value visitPrintStatement(Expression expression) {
        System.out.println(expression.accept(this));
        return null;
    }

    @Override
    public Value visitVarStatement(Identity identity, Expression expression) {
        dynamicEnvironment.newFresh(identity, expression.accept(this));
        return null;
    }

    @Override
    public Value visitAssignStatement(Identity identity, Expression expression) {
        dynamicEnvironment.update(identity, expression.accept(this));
        return null;
    }

    @Override
    public Value visitForEachStatement(Identity identity, Expression expression, StatementSequence block) {
        ListValue list = expression.accept(this).asList();
        for (Value value : list) {
            dynamicEnvironment.enterScope();
            dynamicEnvironment.newFresh(identity, value);
            block.accept(this);
            dynamicEnvironment.exitScope();
        }
        return null;
    }
}
