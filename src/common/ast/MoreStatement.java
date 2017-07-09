package common.ast;

import common.Visitor;

/**
 * MoreStatement class
 */
public class MoreStatement extends More<Statement, StatementSequence> implements StatementSequence {

    public MoreStatement(Statement first, StatementSequence rest) {
        super(first, rest);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitMoreStatement(first, rest);
    }
}
