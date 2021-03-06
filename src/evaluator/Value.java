/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.Value.java
 *
 */

package evaluator;

/**
 * Value interface
 */
public interface Value {

    default int asInt() {
        throw new ClassCastException("Expecting an integer value");
    }

    default boolean asBoolean() {
        throw new ClassCastException("Expecting a boolean value");
    }

    default String asString() {
        throw new ClassCastException("Expecting a string value");
    }

    default ListValue asList() {
        throw new ClassCastException("Expecting a list value");
    }

    default CoupleValue asCouple() {
        throw new ClassCastException("Expecting a couple value");
    }
}
