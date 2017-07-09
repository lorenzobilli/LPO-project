package common.ast;

import common.Visitor;

/**
 * IntegerLiteral class
 */
public class IntegerLiteral extends PrimaryLiteral<Integer> {

    public IntegerLiteral(Integer number) {
        super(number);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIntegerLiteral(value);
    }
}
