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
        return null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + statementSequence + ")";
    }
}
