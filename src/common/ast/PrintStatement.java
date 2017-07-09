package common.ast;

import common.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * PrintStatement class
 */
public class PrintStatement implements Statement {

    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = requireNonNull(expression);
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + expression + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitPrintStatement(expression);
    }
}
