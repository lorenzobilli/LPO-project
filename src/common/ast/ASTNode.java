/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.ASTNode.java
 *
 */

package common.ast;

import common.Visitor;

/**
 * ASTNode interface
 */
public interface ASTNode {
    <T> T accept(Visitor<T> visitor);
}
