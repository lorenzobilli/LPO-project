/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Single.java
 *
 */

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
