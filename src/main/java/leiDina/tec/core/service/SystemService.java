package main.java.leiDina.tec.core.service;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.model.SystemProperty;

/**
 * The base interface of a system service. The object that implements this service, loads and initializes the module that it represents.
 *
 * @author vitor.alves
 */
public interface SystemService {

    /**
     * A base method to initialize the system service with the {@link ConfigurableApplicationEnvironment} of the service.
     *
     * @param environment a {@link ConfigurableApplicationEnvironment}.
     */
    void init(ConfigurableApplicationEnvironment environment);

    /**
     * @return the name of the system service.
     */
    String getServiceName();

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

    /**
     * @return the name of the environment. The name indicates the name of the .xml file that contains the properties of the service.
     */
    String getEnvironmentName();

}
