package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.SystemProperty;

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
    SystemProperty<Class<?>> loadSystemPropertiesFor(Class<?> type);

}
