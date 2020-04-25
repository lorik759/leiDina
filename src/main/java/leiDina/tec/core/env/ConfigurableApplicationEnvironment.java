package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * A root interface for the configuration of a {@link main.java.leiDina.tec.core.service.SystemService} thru a configuration resources.
 *
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironment {

    /**
     * Loads and returns the system properties for the specified key.
     *
     * @param key The key of which the system properties will be loaded.
     * @return a {@link SystemProperty}
     */
    <T> SystemProperty<T> loadSystemPropertiesFor(String key);

}
