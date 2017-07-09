package common.ast;

import static java.util.Objects.requireNonNull;

/**
 * More class
 */
public class More<F, R> {

    protected final F first;
    protected final R rest;

    public More(F first, R rest) {
        this.first = requireNonNull(first);
        this.rest = requireNonNull(rest);
    }

    public F getFirst() {
        return first;
    }

    public R getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + first + ", " + rest + ")";
    }
}
