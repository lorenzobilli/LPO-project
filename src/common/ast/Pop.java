package common.ast;

import common.Visitor;

/**
 * Pop class
 */
public class Pop extends UnaryOperator {

    public Pop(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitPop(expression);
    }
}
