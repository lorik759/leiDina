package main.java.leiDina.tec.javafx.factory.builder;


import main.java.leiDina.tec.javafx.exception.BuilderException;

/**
 * The root interface for a controller builder. This interface is implemented my objects that are abel to instantiate and build a controller for a
 * javafx scene.
 *
 * @author vitor.alves
 */
public interface ControllerBuilder<T> {

    /**
     * Creates and builds a controller, of the type of the specified {@param clazz}.
     *
     * @param clazz The class of which controller well be created of.
     * @return the instance of the specified class, build and configure for a class of type T.
     * @throws BuilderException if the instantiation or configuration of the object fails.
     */
    T build(Class<?> clazz) throws BuilderException;

    /**
     * @return the type of the controller this controller builder will build.
     */
    Class<T> getType();

}
