/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Multiply.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Multiply class
 */
public class Multiply extends BinaryOperator {

    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitMultiply(left, right);
    }
}
