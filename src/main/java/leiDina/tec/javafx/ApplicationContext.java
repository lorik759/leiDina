package main.java.leiDina.tec.javafx;


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
     *
     * @param starterClass the {@link Class} the started the application.
     */
    void init(Class<?> starterClass);

    /**
     * The base controller factory of the application.
     *
     * @return the root {@link ControllerFactory} of the application.
     */
    ControllerFactory getControllerFactory();

}
