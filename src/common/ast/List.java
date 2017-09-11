/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.List.java
 *
 */

package common.ast;

import common.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * List class
 */
public class List implements Expression {

    private final ExpressionSequence expressionSequence;

    public List(ExpressionSequence expressionSequence) {
        this.expressionSequence = requireNonNull(expressionSequence);
    }

    public ExpressionSequence getExpressionSequence() {
        return expressionSequence;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + expressionSequence + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitListLiteral(expressionSequence);
    }
}
