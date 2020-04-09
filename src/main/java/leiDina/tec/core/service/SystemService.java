package main.java.leiDina.tec.core.service;

import java.util.List;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironmentProvider;
import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * @author vitor.alves
 */
public interface SystemService {

    void init(ConfigurableApplicationEnvironmentProvider applicationEnvironment);

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

}
