package common.ast;

import common.Visitor;

/**
 * ASTNode interface
 */
public interface ASTNode {
    <T> T accept(Visitor<T> visitor);
}
