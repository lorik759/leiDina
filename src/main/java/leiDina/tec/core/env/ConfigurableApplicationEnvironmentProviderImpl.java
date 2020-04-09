package main.java.leiDina.tec.core.env;

/**
 * @author vitor.alves
 */
public class ConfigurableApplicationEnvironmentProviderImpl implements ConfigurableApplicationEnvironmentProvider {

    @Override
    public ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName) {
        return new ConfigurableApplicationEnvironmentImpl(environmentName);
    }
}
