package typechecker;

/**
 * TypecheckerException class
 */
public class TypecheckerException extends RuntimeException {

    public TypecheckerException() {
        super("Typechecker exception");
    }

    public TypecheckerException(String message) {
        super("Typechecker exception: " + message);
    }

    public TypecheckerException(String found, String expected) {
        super("Typechecker exception: Found " + found + ", expected " + expected);
    }

}
