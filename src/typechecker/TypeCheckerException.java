/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: typechecker.TypeCheckerException.java
 *
 */

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
