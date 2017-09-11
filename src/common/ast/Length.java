/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Length.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Length class
 */
public class Length extends UnaryOperator {

    public Length(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitLength(expression);
    }
}
