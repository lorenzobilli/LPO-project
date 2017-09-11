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
        return firstValue.equals(couple.firstValue) && secondValue.equals(couple.secondValue);
    }

    @Override
    public CoupleValue asCouple() {
        return this;
    }
}
