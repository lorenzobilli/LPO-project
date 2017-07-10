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
    AND,                    // &&
    OR,                     // ||
    NOT,                    // !
    EQUAL,                  // ==
    LESSTHAN,               // <
    SIGN,                   // -
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
