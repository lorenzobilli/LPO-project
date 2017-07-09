package common.ast;

import static java.util.Objects.requireNonNull;

/**
 * VarAssignStatement class
 */
public abstract class VarAssignStatement implements Statement {

    protected final Identity identity;
    protected final Expression expression;

    protected VarAssignStatement(Identity identity, Expression expression) {
        this.identity = requireNonNull(identity);
        this.expression = requireNonNull(expression);
    }

    public Identity getIdentity() {
        return identity;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + identity + ", " + expression + ")";
    }
}
