/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: evaluator.EvaluatorException.java
 *
 */

package evaluator;

/**
 * EvaluatorException class
 */
public class EvaluatorException extends RuntimeException {

    public EvaluatorException() {
        super("Evaluator exception");
    }

    public EvaluatorException(String message) {
        super("Evaluator exception: " + message);
    }
}
