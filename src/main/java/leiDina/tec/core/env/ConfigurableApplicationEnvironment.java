package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.ClassSystemProperties;

/**
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironment {

    ClassSystemProperties loadSystemPropertiesFor(Class<?> type);

}
