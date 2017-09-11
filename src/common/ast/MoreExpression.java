/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.MoreExpression.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * MoreExpression class
 */
public class MoreExpression extends More<Expression, ExpressionSequence> implements ExpressionSequence {

    public MoreExpression(Expression first, ExpressionSequence rest) {
        super(first, rest);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitMoreExpression(first, rest);
    }
}
