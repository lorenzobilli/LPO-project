/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.BooleanLiteral.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * BooleanLiteral class
 */
public class BooleanLiteral extends PrimaryLiteral<Boolean> {

    public BooleanLiteral(Boolean bool) {
        super(bool);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitBooleanLiteral(value);
    }
}
