package common.ast;

import common.Visitor;

/**
 * Add class
 */
public class Add extends BinaryOperator {

    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAdd(left, right);
    }
}
