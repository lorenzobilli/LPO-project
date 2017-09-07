package common.ast;

import common.Visitor;

public class Snd extends UnaryOperator {

    public Snd(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSnd(expression);
    }
}
