/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.BoolValue.java
 *
 */

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
