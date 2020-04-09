package main.java.leiDina.tec.core.env;

/**
 * @author vitor.alves
 */
public interface ConfigurableApplicationProvider {

    ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName);

    SystemLoader getSysetmLoaderFor(String systemProperties);
}
