package common.ast;

import common.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * IfStatement class
 */
public class IfStatement implements Statement {

    private final Expression expression;
    private final StatementSequence ifBlock;
    private final StatementSequence elseBlock;

    public IfStatement(Expression expression, StatementSequence ifBlock, StatementSequence elseBlock) {
        this.expression = requireNonNull(expression);
        this.ifBlock = requireNonNull(ifBlock);
        this.elseBlock = requireNonNull(elseBlock);
    }

    public Expression getExpression() {
        return expression;
    }

    public StatementSequence getIfBlock() {
        return ifBlock;
    }

    public StatementSequence getElseBlock() {
        return elseBlock;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + expression + ", " + ifBlock + ", " + elseBlock + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIfStatement(expression, ifBlock, elseBlock);
    }
}
