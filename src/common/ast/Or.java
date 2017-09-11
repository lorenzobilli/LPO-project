/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Or.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Or class
 */
public class Or extends BinaryOperator {

    public Or(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitOr(left, right);
    }
}
