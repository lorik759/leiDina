package main.java.leiDina.tec.core.env;

/**
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironmentProvider {

    ConfigurableApplicationEnvironment getEnvironmentFor(String environmentName);
}
