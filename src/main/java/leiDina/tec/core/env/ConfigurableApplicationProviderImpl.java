package main.java.leiDina.tec.core.env;

/**
 * @author vitor.alves
 */
public class ConfigurableApplicationProviderImpl implements ConfigurableApplicationProvider {

    @Override
    public ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName) {
        return new ConfigurableApplicationEnvironmentImpl(environmentName);
    }

    @Override
    public SystemLoader getSysetmLoaderFor(String systemProperties) {
        return new SystemLoaderImpl(systemProperties);
    }
}
