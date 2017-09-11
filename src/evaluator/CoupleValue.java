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
}
