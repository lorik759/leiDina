package main.java.leiDina.tec.javafx.factory;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.javafx.factory.builder.ControllerBuilder;

/**
 * The root interface for a controller factory. This interface is implemented by an object that can identify and create a controller for a javafx scene.
 *
 * @author vitor.alves
 */
public interface ControllerFactory {

    /**
     * Returns a specific controller for a javafx scene, of a specific class.
     *
     * @param controllerClass a class of a specific controller.
     * @return a instance of a the specified class.
     */
    Object getController(Class<?> controllerClass);

    <T> void addControllerBuilder(ControllerBuilder<T> builder);
}
