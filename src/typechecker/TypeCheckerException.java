package typechecker;

/**
 * TypeCheckerException class
 */
public class TypeCheckerException extends RuntimeException {

    public TypeCheckerException() {
        super("TypeChecker exception");
    }

    public TypeCheckerException(String message) {
        super("TypeChecker exception: " + message);
    }

    public TypeCheckerException(String found, String expected) {
        super("TypeChecker exception: Found " + found + ", expected " + expected);
    }

}
