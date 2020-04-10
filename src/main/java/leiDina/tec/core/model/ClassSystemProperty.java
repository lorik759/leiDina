package main.java.leiDina.tec.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A model for system properties of type classes.
 *
 * @author vitor.alves
 */
public class ClassSystemProperty implements SystemProperty<Class<?>> {

    private String name;

    private List<Class<?>> properties;

    public ClassSystemProperty(String name) {
        this.name = name;
        this.properties = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getType() {
        return Class.class;
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
     * A class system property dose not contain a single property, it holds a collection of classes that wil be used as properties.
     */
    @Override
    public Class<?> getProperty() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void addProperty(Class<?> classSystemProperties) {
        this.properties.add(classSystemProperties);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addProperties(List<?> properties) {
        this.properties.addAll((Collection<? extends Class<?>>) properties);
    }
}
