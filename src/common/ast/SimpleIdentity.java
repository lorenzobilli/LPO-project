/*
 *  LPO 2016/2017 - Final Project
 *  Author: Lorenzo Billi (S3930391)
 *
 *  File: common.ast.SimpleIdentity.java
 *
 */

package common.ast;

import common.Visitor;

import static java.util.Objects.requireNonNull;

/**
 * SimpleIdentity class
 */
public class SimpleIdentity implements Identity {

    private final String name;

    public SimpleIdentity(String name) {
        this.name = requireNonNull(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public final boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Identity)) {
            return false;
        }
        return name.equals(((Identity) object).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + name + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitIdentity(name);
    }
}
