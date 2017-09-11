/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.Visitor.java
 *
 */

package common;

import common.ast.*;

/**
 * Visitor interface
 */
public interface Visitor<T> {
    T visitProgram(StatementSequence statementSequence);
    T visitMoreStatement(Statement first, StatementSequence rest);
    T visitSingleStatement(Statement statement);
    T visitMoreExpression(Expression first, ExpressionSequence rest);
    T visitSingleExpression(Expression expression);
    T visitOr(Expression left, Expression right);
    T visitAnd(Expression left, Expression right);
    T visitEqual(Expression left, Expression right);
    T visitLessThan(Expression left, Expression right);
    T visitConcatenate(Expression left, Expression right);
    T visitAdd(Expression left, Expression right);
    T visitSubtract(Expression left, Expression right);
    T visitMultiply(Expression left, Expression right);
    T visitDivide(Expression left, Expression right);
    T visitIntegerLiteral(int value);
    T visitBooleanLiteral(boolean value);
    T visitIdentity(String name);
    T visitNot(Expression expression);
    T visitSign(Expression expression);
    T visitTop(Expression expression);
    T visitPop(Expression expression);
    T visitPush(Expression left, Expression right);
    T visitLength(Expression expression);
    T visitPair(Expression left, Expression right);
    T visitFst(Expression expression);
    T visitSnd(Expression expression);
    T visitListLiteral(ExpressionSequence expressionSequence);
    T visitPrintStatement(Expression expression);
    T visitVarStatement(Identity identity, Expression expression);
    T visitAssignStatement(Identity identity, Expression expression);
    T visitForEachStatement(Identity identity, Expression expression, StatementSequence block);
    T visitIfStatement(Expression expression, StatementSequence ifBlock, StatementSequence elseBlock);
    T visitWhileStatement(Expression expression, StatementSequence block);
}
