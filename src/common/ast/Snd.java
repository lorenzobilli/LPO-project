/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.Snd.java
 *
 */

package common.ast;

import common.Visitor;

public class Snd extends UnaryOperator {

    public Snd(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitSnd(expression);
    }
}
