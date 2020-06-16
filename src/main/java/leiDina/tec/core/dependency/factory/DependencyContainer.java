package main.java.leiDina.tec.core.dependency.factory;

import java.util.Map;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;

/**
 * The root interface for a dependency container. This interface works as an accesses point to the VTEC Dependency Container. For a bootable
 * dependency container look at {@link BootableDependencyContainer}.
 *
 * @author vitor.alves
 */
public interface DependencyContainer {

    /**
     * Gets the object with the uniquely specified name.
     *
     * @param objectName the name of the object.
     * @param <T> the type of the object.
     * @return a object with the specified name.
     * @throws DependencyException a {@link DependencyException}
     */
    <T> T getObject(String objectName) throws DependencyException;

    /**
     * Gets the object with the specified type.
     *
     * @param type the {@link Class} of the requested object.
     * @param <T> the type of the object.
     * @return a object of the specified class.
     * @throws DependencyException a {@link DependencyException}.
     */
    <T> T getObject(Class<?> type) throws DependencyException;

    /**
     * @param name the name of the object.
     * @return true if a object instance of the specified name exists. Otherwise, return false.
     */
    boolean objectExists(String name);

    /**
     * Gets all objects that either extend ou implements the specified class, separated by name.
     *
     * @param type the classe that the object either extend ou implements.
     * @param <T> the type of the object.
     * @return a map of name by object.
     * @throws DependencyException a {@link DependencyException}.
     */
    <T> Map<String, T> getObjectsOfTypes(Class<T> type) throws DependencyException;

    /**
     * Return the class of the object with the specified name.
     *
     * @param name the name of the object.
     * @return the class of the specified name.
     * @throws DependencyException a {@link DependencyException}
     */
    Class<?> getObjectType(String name) throws DependencyException;

}
