package evaluator;

/**
 * ListValue interface
 */
public interface ListValue extends Value, Iterable<Value> {

    int size();

    Value get(int index);

    ListValue push(Value value);

    Value top();

    ListValue pop();

    @Override
    default ListValue asList() {
        return this;
    }
}
