/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.IntValue.java
 *
 */

package evaluator;

/**
 * IntValue class
 */
public class IntValue extends PrimitiveValue<Integer> {

    public IntValue(Integer value) {
        super(value);
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof IntValue)) {
            return false;
        }
        return value.equals(((IntValue) object).value);
    }

    @Override
    public int asInt() {
        return value;
    }
}
