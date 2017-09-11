/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Program.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * Program class
 */
public class Program implements Prog {

    private final StatementSequence statementSequence;

    public Program(StatementSequence statementSequence) {
        this.statementSequence = statementSequence;
    }

    public StatementSequence getStatementSequence() {
        return statementSequence;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitProgram(statementSequence);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + statementSequence + ")";
    }
}
