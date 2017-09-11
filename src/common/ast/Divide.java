/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Divide.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Divide class
 */
public class Divide extends BinaryOperator {

    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitDivide(left, right);
    }
}
