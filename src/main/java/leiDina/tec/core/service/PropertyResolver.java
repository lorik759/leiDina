package main.java.leiDina.tec.core.service;

import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * A base interface for a resolver of a {@link SystemProperty}. An object that implements this interface knows how to resolve values from a system
 * environment, and create a system property.
 *
 * @author vitor.alves
 */
@Deprecated
public interface PropertyResolver<T extends SystemProperty> {

    /**
     * Resolves the properties of the environment, and creates a {@link SystemProperty} form the {@param values}.
     *
     * @param key the key of the property.
     * @param values the values of the property.
     * @return a {@link SystemProperty}
     */
    T resolve(String key, String values);
}
