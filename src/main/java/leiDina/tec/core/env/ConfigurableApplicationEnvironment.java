package main.java.leiDina.tec.core.env;

import java.util.List;

/**
 * This root interface represents the environment in which a {@link main.java.leiDina.tec.core.ApplicationContext} is created on. This environment
 * tells where the application needs to look at for the {@link main.java.leiDina.tec.core.beans.model.BeanDefinition} and creates a {@link
 * ApplicationProperty} for each individual location. For a better understanding and for the default working of a ConfigurableApplicationEnvironment
 * look at {@link DefaultConfigurableApplicationEnvironment}.
 *
 * @author vitor.alves
 */
public interface ConfigurableApplicationEnvironment {

    /**
     * Loads each individual module/resource that contais mapped beans, and creates a {@link ApplicationProperty} for each.
     *
     * @return a list of {@link ApplicationProperty}.
     */
    List<ApplicationProperty> loadApplicationProperties();

}
