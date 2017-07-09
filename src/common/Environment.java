package common;

import common.ast.Identity;
import typechecker.TypeCheckerException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * Environment class
 */
public class Environment<T> {

    private List<Map<Identity, T>> localEnvironments = new LinkedList<>();

    public Environment() {
        enterScope();
    }

    public void enterScope() {
        localEnvironments.add(0, new HashMap<>());
    }

    public void exitScope() {
        localEnvironments.remove(0);
    }

    public T lookup(Identity identity) throws TypeCheckerException {
        return resolve(identity).get(identity);
    }

    public T update(Identity identity, T value) throws TypeCheckerException {
        return put(resolve(identity), identity, value);
    }

    public T newFresh(Identity identity, T value) {
        Map<Identity, T> localEnvironment = localEnvironments.get(0);
        return put(localEnvironment, identity, value);
    }

    private static <T> T put(Map<Identity, T> map, Identity identity, T value) {
        return map.put(requireNonNull(identity), requireNonNull(value));
    }

    protected Map<Identity, T> resolve(Identity identity) throws TypeCheckerException {
        for (Map<Identity, T> localEnvironment : localEnvironments) {
            if (localEnvironment.containsKey(identity)) {
                return localEnvironment;
            }
        }
        throw new TypeCheckerException("Undeclared variable " + identity.getName());
    }

}
