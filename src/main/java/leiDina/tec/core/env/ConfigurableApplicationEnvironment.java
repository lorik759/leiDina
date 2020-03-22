package main.java.leiDina.tec.core.env;

import main.java.leiDina.tec.core.model.SystemProperties;

/**
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironment {

    SystemProperties<Class<?>> loadSystemPropertiesFor(Class<?> type);

}
