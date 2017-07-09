package common.ast;

import common.Visitor;

/**
 * LessThan class
 */
public class LessThan extends BinaryOperator {

    public LessThan(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitLessThan(left, right);
    }
}
