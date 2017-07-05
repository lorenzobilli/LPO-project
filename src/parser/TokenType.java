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
    FIRST,
    SECOND,
    PUSH,
    POP,
    TOP,
    PLUS,                   // +
    MINUS,                  // -
    TIMES,                  // *
    FRACTION,               // /
    ASSIGN,                 // =
    CONCATENATE,            // @
    NOT,                    // !
    OPPOSITE,               // -
    PARENTHESIS_OPEN,       // (
    PARENTHESIS_CLOSED,     // )
    BRACKETS_OPEN,          // [
    BRACKETS_CLOSED,        // ]
    BRACES_OPEN,            // {
    BRACES_CLOSED,          // }
    COMMA,                  // ,
    STATEMENT_SEP,          // ;
    NUM,
    BOOL,
    IDENT,
    SKIP,
    EOF
}
