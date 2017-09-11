/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Pair.java
 *
 */

package common.ast;

import common.Visitor;

public class Pair implements Expression {

    private final Expression left;
    private final Expression right;

    public Pair(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + left + ", " + right + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitPair(left, right);
    }
}
