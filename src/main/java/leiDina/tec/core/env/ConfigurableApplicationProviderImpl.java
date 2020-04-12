package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.ApplicationDefinitions;

/**
 * A base implantation of the {@link ConfigurableApplicationProvider} that utilizes the base implementation of a {@link
 * ConfigurableApplicationEnvironment} and {@link SystemLoader}.
 *
 * @author vitor.alves
 */
public class ConfigurableApplicationProviderImpl implements ConfigurableApplicationProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName, ApplicationDefinitions applicationDefinitions) {
        return new ConfigurableApplicationEnvironmentImpl(environmentName, applicationDefinitions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemLoader getSystemLoaderFor(String systemName) {
        return new SystemLoaderImpl(systemName);
    }
}
