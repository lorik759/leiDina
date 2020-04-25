package main.java.leiDina.tec.vinjection.env;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.env.SystemLoader;

/**
 * @author vitor.alves
 */
public class XmlConfigurableApplicationProvider implements ConfigurableApplicationProvider {

    @Override
    public ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName) {
        return new XmlConfigurableApplicationEnvironment(environmentName);
    }

    @Override
    public SystemLoader getSystemLoaderFor(String systemName) {
        return null; // this will die one day
    }
}
