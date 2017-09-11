/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.VarStatement.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * VarStatement
 */
public class VarStatement extends VarAssignStatement {

    public VarStatement(Identity identity, Expression expression) {
        super(identity, expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitVarStatement(identity, expression);
    }
}
