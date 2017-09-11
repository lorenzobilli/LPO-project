/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.PrimitiveValue.java
 *
 */

package evaluator;

import static java.util.Objects.requireNonNull;

/**
 * PrimitiveValue class
 */
public abstract class PrimitiveValue<T> implements Value {

    protected T value;

    protected PrimitiveValue(T value) {
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
