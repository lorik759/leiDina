package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;

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
     * The base controller factory of the application.
     *
     * @return the root {@link ControllerFactory} of the application.
     */
    ControllerFactory getControllerFactory();

    /**
     * Returns the environment of the application.
     *
     * @return the {@link ConfigurableApplicationEnvironment}
     */
    ConfigurableApplicationEnvironment getConfigurableApplicationEnvironment();

    /**
     * Set the {@link ConfigurableApplicationEnvironment} of the application. This method is to be used only on application startup.
     *
     * @param environment the {@link ConfigurableApplicationEnvironment} to be used by the application.
     */
    void setEnvironment(ConfigurableApplicationEnvironment environment);

    /**
     * Returns the wire for a model and a scene
     *
     * @return {@link ModelSceneWire}
     */
    ModelSceneWire getModelWire();
}
