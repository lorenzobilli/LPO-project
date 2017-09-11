/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Top.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Top class
 */
public class Top extends UnaryOperator {

    public Top(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitTop(expression);
    }
}
