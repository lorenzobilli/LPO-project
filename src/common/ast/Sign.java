package common.ast;

import common.Visitor;

/**
 * Sign class
 */
public class Sign extends UnaryOperator {

    public Sign(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSign(expression);
    }
}
