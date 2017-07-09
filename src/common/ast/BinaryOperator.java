package common.ast;

import static java.util.Objects.requireNonNull;

/**
 * BinaryOperator class
 */
public abstract class BinaryOperator implements Expression {

    protected final Expression left;
    protected final Expression right;

    protected BinaryOperator(Expression left, Expression right) {
        this.left = requireNonNull(left);
        this.right = requireNonNull(right);
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + left + ", " + right + ")";
    }
}
