package main.java.leiDina.tec.core.service;

import main.java.leiDina.tec.core.ApplicationContext;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;

/**
 * The base interface of an integration system service. The object that implements this interface knows how to integrate two our more modules
 * together. Making it so that they can communicate thru the {@link main.java.leiDina.tec.core.model.SystemProperty} of each module. An {@link
 * IntegrationSystemService} is not an interactive system service, its only function is to integrate the two modules together.
 *
 * @author vitor.alves
 */
public interface IntegrationSystemService {

    /**
     * Initializes the integration module, which will integrate too our more modules together.
     *
     * @param environment The {@link ConfigurableApplicationEnvironment} of the IntegrationSystemService if it uses.
     * @param applicationContext the current {@link ApplicationContext}.
     */
    void init(ConfigurableApplicationEnvironment environment, ApplicationContext applicationContext);

    /**
     * @return the name of the environment. The name indicates the name of the .xml file that contains the properties of the service.
     */
    String getEnvironmentName();

}
