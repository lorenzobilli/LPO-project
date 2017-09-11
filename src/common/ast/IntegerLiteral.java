/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.IntegerLiteral.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * IntegerLiteral class
 */
public class IntegerLiteral extends PrimaryLiteral<Integer> {

    public IntegerLiteral(Integer number) {
        super(number);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIntegerLiteral(value);
    }
}
