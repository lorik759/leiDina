package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.ApplicationDefinitions;

/**
 * An object that implements this interface, knows how to provide specific {@link ConfigurableApplicationEnvironment} and {@link SystemLoader}, for
 * specific name and application definition.
 *
 * @author vitor.alves
 */
public interface ConfigurableApplicationProvider {

    /**
     * Provides an {@link ConfigurableApplicationEnvironment} for the {@param environmentName} and {@link ApplicationDefinitions}.
     *
     * @param environmentName the name of the environment.
     * @param applicationDefinitions the {@link ApplicationDefinitions} of the application.
     * @return A {@link ConfigurableApplicationEnvironment}.
     */
    ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName, ApplicationDefinitions applicationDefinitions);

    SystemLoader getSystemLoaderFor(String systemName);
}
