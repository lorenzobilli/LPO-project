package evaluator;

import static java.util.Objects.requireNonNull;

/**
 * PrimaryValue class
 */
public abstract class PrimaryValue<T> implements Value {

    protected T value;

    protected PrimaryValue(T value) {
        this.value = requireNonNull(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
