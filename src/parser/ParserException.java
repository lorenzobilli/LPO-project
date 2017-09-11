/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: parser.ParserException.java
 *
 */

package parser;

/**
 * ParserException class
 */
public class ParserException extends Exception {
    public ParserException() {
        super("Parsing error");
    }

    public ParserException(String message) {
        super("Parsing error: " + message);
    }
}
