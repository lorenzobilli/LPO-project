/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.LinkedListValue.java
 *
 */

package evaluator;

import java.util.Iterator;
import java.util.LinkedList;

import static java.util.Objects.requireNonNull;

/**
 * LinkedListValue class
 */
public class LinkedListValue implements ListValue {

    private final LinkedList<Value> list = new LinkedList<>();

    public LinkedListValue() {
    }

    public LinkedListValue(ListValue list) {
        for (Value value : list) {
            this.list.add(value);
        }
    }

    public LinkedListValue(ListValue left, ListValue right) {
        this(left);
        for (Value value : right) {
            this.list.add(value);
        }
    }

    public LinkedListValue(Value value, ListValue tail) {
        this(tail);
        list.addFirst(requireNonNull(value));
    }

    private void checkIsEmpty() {
        if (list.size() == 0) {
            throw new EvaluatorException("Undefined operation on the empty list");
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Value get(int index) {
        return list.get(index);
    }

    @Override
    public ListValue push(Value value) {
        LinkedListValue result = new LinkedListValue(this);
        result.list.addLast(requireNonNull(value));
        return result;
    }

    @Override
    public Value top() {
        checkIsEmpty();
        return list.getLast();
    }

    @Override
    public ListValue pop() {
        checkIsEmpty();
        LinkedListValue result = new LinkedListValue(this);
        result.list.removeLast();
        return result;
    }

    @Override
    public Iterator<Value> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return list.equals(((LinkedListValue) object).list);
    }
}
