/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Equal.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Equal class
 */
public class Equal extends BinaryOperator {

    public Equal(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitEqual(left, right);
    }
}
