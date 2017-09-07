package common.ast;

import common.Visitor;

public class Fst extends UnaryOperator {

    public Fst(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitFst(expression);
    }
}
