package main.java.leiDina.tec.javafx.factory;

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

    /**
     * Adds a {@link ControllerBuilder} to the controller factory.
     *
     * @param builder a {@link ControllerBuilder}.
     * @param <T> the type of the controller that the builder will create.
     */
    @Deprecated
    <T> void addControllerBuilder(ControllerBuilder<T> builder);
}
