/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.SingleExpression.java
 *
 */

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
