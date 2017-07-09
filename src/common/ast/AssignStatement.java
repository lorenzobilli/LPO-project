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
