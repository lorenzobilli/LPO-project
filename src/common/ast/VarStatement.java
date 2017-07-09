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
