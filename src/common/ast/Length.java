package common.ast;

import common.Visitor;

public class Length extends UnaryOperator {

    public Length(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitLength(expression);
    }
}
