package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.PropertyResolver;

/**
 * A root interface for the configuration of a {@link main.java.leiDina.tec.core.VApplication} thru configuration resources.
 *
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironment {

    /**
     * Loads and returns the system properties for the specified class.
     *
     * @param type The class of which the system properties will be loaded.
     * @return a {@link SystemProperty}
     */
    <T> SystemProperty<T> loadSystemPropertiesFor(Class<?> type, PropertyResolver<?> resolver);

    /**
     * Loads and returns the system properties for the specified key.
     *
     * @param key The key of which the system properties will be loaded.
     * @param resolver The {@link PropertyResolver} of the {@link SystemProperty}.
     * @return a {@link SystemProperty}
     */
    <T> SystemProperty<T> loadSystemPropertiesFor(String key, PropertyResolver<?> resolver);

}
