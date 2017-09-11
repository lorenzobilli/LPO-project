/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.CoupleType.java
 *
 */

package evaluator;

/**
 * CoupleValue class
 */
public class CoupleValue implements Value {

    private Value firstValue;
    private Value secondValue;

    public CoupleValue(Value firstValue, Value secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Value getFirstValue() {
        return firstValue;
    }

    public Value getSecondValue() {
        return secondValue;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CoupleValue)) {
            return false;
        }
        CoupleValue couple = (CoupleValue) object;
        return firstValue.equals(couple.getFirstValue()) && secondValue.equals(couple.getSecondValue());
    }

    @Override
    public String toString() {
        return "(" + firstValue.toString() + ", " + secondValue.toString() + ")";
    }

    @Override
    public CoupleValue asCouple() {
        return this;
    }
}
