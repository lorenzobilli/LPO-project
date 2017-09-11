/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: parser.TokenType.java
 *
 */

package parser;

/**
 * TokenType enum
 */
public enum TokenType {
    VAR,
    IF,
    ELSE,
    WHILE,
    FOR,
    IN,
    PRINT,
    LENGTH,
    PAIR,
    FST,
    SND,
    PUSH,
    POP,
    TOP,
    PLUS,                   // +
    MINUS,                  // -
    TIMES,                  // *
    FRACTION,               // /
    ASSIGN,                 // =
    CONCATENATE,            // @
    AND,                    // &&
    OR,                     // ||
    NOT,                    // !
    EQUAL,                  // ==
    LESSTHAN,               // <
    OPEN_PAR,               // (
    CLOSED_PAR,             // )
    START_LIST,             // [
    END_LIST,               // ]
    START_BLOCK,            // {
    END_BLOCK,              // }
    EXPRESSION_SEP,         // ,
    STATEMENT_SEP,          // ;
    NUM,
    BOOL,
    IDENT,
    SKIP,
    EOF
}
