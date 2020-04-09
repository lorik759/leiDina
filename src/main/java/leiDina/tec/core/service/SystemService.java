package main.java.leiDina.tec.core.service;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * @author vitor.alves
 */
public interface SystemService {

    void init(ConfigurableApplicationEnvironment environment);

    /**
     * @return {@link SystemKey} that represents the service.
     */
    SystemKey getKey();

    /**
     * @return {@link SystemProperty} by type.
     */
    <T extends SystemProperty<?>> T getPropertyByType(Class<?> type);

    /**
     * @return {@link SystemProperty} by name.
     */
    <T extends SystemProperty<?>> T getPropertyByName(String name);

    /**
     * Adds a property to the set of properties.
     *
     * @param property a property.
     */
    <T extends SystemProperty<?>> void addProperty(T property);

    String getEnvironmentName();

}
