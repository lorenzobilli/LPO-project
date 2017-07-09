package typechecker;

/**
 * Type interface
 */
public interface Type {

    default Type checkEqual(Type expected) throws TypeCheckerException {
        if (!equals(expected)) {
            throw new TypeCheckerException(toString(), expected.toString());
        }
        return this;
    }

    default Type checkList() throws TypeCheckerException {
        if (!(this instanceof ListType)) {
            throw new TypeCheckerException(toString(), ListType.LIST);
        }
        return ((ListType) this).getListType();
    }
}
