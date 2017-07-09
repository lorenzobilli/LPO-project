package common.ast;

import common.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * ForEachStatement class
 */
public class ForEachStatement implements Statement {

    private final Identity identity;
    private final Expression expression;
    private final StatementSequence block;

    public ForEachStatement(Identity identity, Expression expression, StatementSequence block) {
        this.identity = requireNonNull(identity);
        this.expression = requireNonNull(expression);
        this.block = requireNonNull(block);
    }

    public Identity getIdentity() {
        return identity;
    }

    public Expression getExpression() {
        return expression;
    }

    public StatementSequence getBlock() {
        return block;
    }

    @Override public String toString() {
        return getClass().getSimpleName() + "(" + identity + ", " + expression + ", " + block + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitForEachStatement(identity, expression, block);
    }
}
