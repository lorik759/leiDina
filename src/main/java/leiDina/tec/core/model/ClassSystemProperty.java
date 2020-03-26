package main.java.leiDina.tec.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for system properties of type classes.
 *
 * @author vitor.alves
 */
public class ClassSystemProperty implements SystemProperty<Class<?>> {

    private Class<?> type;

    private String name;

    private List<Class<?>> properties;

    public ClassSystemProperty(Class<?> type, String name) {
        this.type = type;
        this.name = name;
        this.properties = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public List<Class<?>> getProperties() {
        return properties;
    }

    /**
     * {@inheritDoc}
     */
    public void addProperty(Class<?> classSystemProperties) {
        this.properties.add(classSystemProperties);
    }
}
