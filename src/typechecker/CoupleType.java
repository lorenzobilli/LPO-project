/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: typechecker.CoupleType.java
 *
 */

package typechecker;

/**
 * CoupleType class
 */
public class CoupleType implements Type {

    public static final String COUPLE = "COUPLE";
    private final Type firstType;
    private final Type secondType;

    public CoupleType(Type firstType, Type secondType) {
        this.firstType = firstType;
        this.secondType = secondType;
    }

    public Type getFirstType() {
        return firstType;
    }

    public Type getSecondType() {
        return secondType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CoupleType)) {
            return false;
        }
        CoupleType couple = (CoupleType) object;
        return (firstType.equals(couple.firstType) && secondType.equals(couple.secondType));
    }

    @Override
    public String toString() {
        return "(" + firstType.toString() + "*" + secondType.toString() + ")";
    }

}
