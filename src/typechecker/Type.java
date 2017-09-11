/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: typechecker.Type.java
 *
 */

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

    default Type checkCouple() throws TypeCheckerException {
        if (!(this instanceof CoupleType)) {
            throw new TypeCheckerException(toString(), CoupleType.COUPLE);
        }
        return this;
    }
}
