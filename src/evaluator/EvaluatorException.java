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
