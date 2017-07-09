package evaluator;

/**
 * ListValue interface
 */
public interface ListValue extends Value, Iterable<Value> {

    ListValue push(Value value);

    Value top();

    ListValue pop();

    @Override
    default ListValue asList() {
        return this;
    }
}
