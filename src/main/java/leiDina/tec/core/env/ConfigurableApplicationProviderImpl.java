package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.ApplicationDefinitions;

/**
 * @author vitor.alves
 */
public class ConfigurableApplicationProviderImpl implements ConfigurableApplicationProvider {

    @Override
    public ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName, ApplicationDefinitions applicationDefinitions) {
        return new ConfigurableApplicationEnvironmentImpl(environmentName, applicationDefinitions);
    }

    @Override
    public SystemLoader getSystemLoaderFor(String systemName) {
        return new SystemLoaderImpl(systemName);
    }
}
