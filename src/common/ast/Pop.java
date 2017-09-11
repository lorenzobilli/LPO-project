/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Pop.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Pop class
 */
public class Pop extends UnaryOperator {

    public Pop(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitPop(expression);
    }
}
