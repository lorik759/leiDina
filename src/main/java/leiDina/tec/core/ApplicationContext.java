package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironmentProvider;
import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;

/**
 * A root interface of an application context. This interface contains all base information of an {@link VApplication}, with the root {@link
 * ControllerFactory}.
 *
 * @author vitor.alves
 */
public interface ApplicationContext {

    /**
     * A base method to initialize the application context with a reference to the starter class.
     */
    void init();

    /**
     * Set the {@link ConfigurableApplicationEnvironment} of the application. This method is to be used only on application startup.
     *
     * @param environment the {@link ConfigurableApplicationEnvironment} to be used by the application.
     */
    void setEnvironmentProvider(ConfigurableApplicationEnvironmentProvider environment);

    SystemService getService(SystemKey key);
}
