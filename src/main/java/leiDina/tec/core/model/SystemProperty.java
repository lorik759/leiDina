package main.java.leiDina.tec.core.model;

import java.util.List;

/**
 * A base interface that represents a set of properties to be used by the system.
 *
 * @author vitor.alves
 */
public interface SystemProperty<T> {

    /**
     * @return the type of the class that will use this system property.
     */
    Class<?> getType();

    /**
     * @return the name of who will use this system property.
     */
    String getName();

    /**
     * @return A list of properties.
     */
    List<T> getProperties();

    /**
     * Adds a property to the set of properties.
     *
     * @param property a property.
     */
    void addProperty(T property);
}
