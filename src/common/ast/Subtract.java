package common.ast;

import common.Visitor;

/**
 * Subtract class
 */
public class Subtract extends BinaryOperator {

    public Subtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSubtract(left, right);
    }
}
