package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.ApplicationDefinitions;

/**
 * An implantation of the {@link ConfigurableApplicationProvider} that utilizes the implementation {@link PropertyConfigurableApplicationProvider} of a {@link
 * ConfigurableApplicationEnvironment} and a base implementation {@link SystemLoader}.
 *
 * @author vitor.alves
 */
@Deprecated
public class PropertyConfigurableApplicationProvider implements ConfigurableApplicationProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName, ApplicationDefinitions applicationDefinitions) {
        return new PropertyConfigurableApplicationEnvironment(environmentName, applicationDefinitions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemLoader getSystemLoaderFor(String systemName) {
        return new SystemLoaderImpl(systemName);
    }
}
