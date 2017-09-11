/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.And.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * And class
 */
public class And extends BinaryOperator {

    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAnd(left, right);
    }
}
