package common.ast;

import common.Visitor;

/**
 * BooleanLiteral class
 */
public class BooleanLiteral extends PrimaryLiteral<Boolean> {

    public BooleanLiteral(Boolean bool) {
        super(bool);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitBooleanLiteral(value);
    }
}
