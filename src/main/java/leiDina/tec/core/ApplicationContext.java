package main.java.leiDina.tec.core;


import main.java.leiDina.tec.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactory;

/**
 * A root interface of a application context. This interface contains all base information of an VFXApplication, with the root {@link
 * ControllerFactory}
 *
 * @author vitor.alves
 */
public interface ApplicationContext {

    /**
     * A base method to initilize the application context with a reference to the starter class.
     */
    void init();

    /**
     * The base controller factory of the application.
     *
     * @return the root {@link ControllerFactory} of the application.
     */
    ControllerFactory getControllerFactory();

    BeanFactory getBeanFactory();

    void setEnvironment(ConfigurableApplicationEnvironment environment);

}
