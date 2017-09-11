/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Not.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Not class
 */
public class Not extends UnaryOperator {

    public Not(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitNot(expression);
    }
}
