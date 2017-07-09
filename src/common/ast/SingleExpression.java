package common.ast;

import common.Visitor;

/**
 * SingleExpression class
 */
public class SingleExpression extends Single<Expression> implements ExpressionSequence {

    public SingleExpression(Expression single) {
        super(single);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSingleExpression(single);
    }
}
