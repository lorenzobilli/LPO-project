/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Subtract.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Subtract class
 */
public class Subtract extends BinaryOperator {

    public Subtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSubtract(left, right);
    }
}
