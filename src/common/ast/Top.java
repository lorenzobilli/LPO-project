package common.ast;

import common.Visitor;

/**
 * Top class
 */
public class Top extends UnaryOperator {

    public Top(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitTop(expression);
    }
}
