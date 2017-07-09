package common.ast;

import static java.util.Objects.requireNonNull;

/**
 * Single class
 */
public class Single<T> {

    protected final T single;

    public Single(T single) {
        this.single = requireNonNull(single);
    }

    public T getSingle() {
        return single;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + single + ")";
    }
}
