package main.java.leiDina.tec.javafx.factory;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;

/**
 * The root interface for a controller factory. This interface is implemented by object that can identify and create controller for a javafx scene.
 *
 * @author vitor.alves
 */
public interface ControllerFactory {


    /**
     * The initializing method of a controller factory. This method, once called, will initialize and configure the controller factory.
     *
     * @param applicationEnvironment {@link ConfigurableApplicationEnvironment}.
     */
    void init(ConfigurableApplicationEnvironment applicationEnvironment);

    /**
     * Returns a specific controller for a javafx scene, of a specific class.
     *
     * @param controllerClass a class of a specific controller.
     * @return a instance of a the specified class.
     */
    Object getController(Class<?> controllerClass);
}
