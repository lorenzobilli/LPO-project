package common.ast;

import static java.util.Objects.requireNonNull;

/**
 * UnaryOperator class
 */
public abstract class UnaryOperator implements Expression {

    protected final Expression expression;

    protected UnaryOperator(Expression expression) {
        this.expression = requireNonNull(expression);
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + expression + ")";
    }

}
