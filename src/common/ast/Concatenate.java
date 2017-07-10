package common.ast;

import common.Visitor;

/**
 * Concatenate class
 */
public class Concatenate extends BinaryOperator {

    public Concatenate(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitConcatenate(left, right);
    }
}
