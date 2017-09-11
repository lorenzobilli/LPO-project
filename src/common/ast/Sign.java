/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Sign.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Sign class
 */
public class Sign extends UnaryOperator {

    public Sign(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSign(expression);
    }
}
