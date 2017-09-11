/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.SingleStatement.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * SingleStatement class
 */
public class SingleStatement extends Single<Statement> implements StatementSequence {

    public SingleStatement(Statement single) {
        super(single);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSingleStatement(single);
    }
}
