package common.ast;

import common.Visitor;

/**
 * And class
 */
public class And extends BinaryOperator {

    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAnd(left, right);
    }
}
