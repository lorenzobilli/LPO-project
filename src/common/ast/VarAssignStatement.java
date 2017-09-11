/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.VarAssignStatement.java
 *
 */

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
