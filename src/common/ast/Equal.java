package common.ast;

import common.Visitor;

/**
 * Equal class
 */
public class Equal extends BinaryOperator {

    public Equal(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitEqual(left, right);
    }
}
