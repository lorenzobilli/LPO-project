/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.WhileStatement.java
 *
 */

package common.ast;

import common.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * WhileStatement class
 */
public class WhileStatement implements Statement {

    private final Expression expression;
    private final StatementSequence block;

    public WhileStatement(Expression expression, StatementSequence block) {
        this.expression = requireNonNull(expression);
        this.block = requireNonNull(block);
    }

    public Expression getExpression() {
        return expression;
    }

    public StatementSequence getBlock() {
        return block;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + expression + ", " + block + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitWhileStatement(expression, block);
    }
}
