/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: parser.ScannerException.java
 *
 */

package parser;

/**
 * ScannerException class
 */
public class ScannerException extends Exception {

    private final String skipped;

    public ScannerException() {
        this(null, null);
    }

    public ScannerException(String message) {
        this(message, null);
    }

    public ScannerException(String message, String skipped) {
        super((message == null ? "Scanner error" : "Scanner error: ") + message);
        this.skipped = skipped;
    }

    public String getSkipped() {
        return skipped;
    }
}
