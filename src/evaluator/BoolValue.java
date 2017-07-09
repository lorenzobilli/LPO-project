package evaluator;

/**
 * BoolValue class
 */
public class BoolValue extends PrimitiveValue<Boolean> {

    public BoolValue(Boolean value) {
        super(value);
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BoolValue)) {
            return false;
        }
        return value.equals(((BoolValue) object).value);
    }

    @Override
    public boolean asBoolean() {
        return value;
    }
}
