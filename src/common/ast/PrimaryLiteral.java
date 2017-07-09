package common.ast;

/**
 * PrimaryLiteral class
 */
public abstract class PrimaryLiteral<T> implements Expression {

    protected final T value;

    public PrimaryLiteral(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + value + ")";
    }
}
