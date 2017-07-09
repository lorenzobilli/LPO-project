package common.ast;

import common.Visitor;

/**
 * Multiply class
 */
public class Multiply extends BinaryOperator {

    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitMultiply(left, right);
    }
}
