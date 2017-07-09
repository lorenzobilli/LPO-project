package common.ast;

import common.Visitor;

/**
 * Push class
 */
public class Push implements Expression {

    private final Expression left;
    private final Expression right;

    public Push(Expression left, Expression right) {
        this.left = left;
        this.right = right;
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

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return null;
    }
}
