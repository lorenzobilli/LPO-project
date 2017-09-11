/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.AssignStatement.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * AssignStatement class
 */
public class AssignStatement extends VarAssignStatement {

    public AssignStatement(Identity identity, Expression expression) {
        super(identity, expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitAssignStatement(identity, expression);
    }
}
