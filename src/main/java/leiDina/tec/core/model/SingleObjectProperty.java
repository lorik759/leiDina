package main.java.leiDina.tec.core.model;

import java.util.Collections;
import java.util.List;

/**
 * A {@link SystemProperty} that indicants a single object property holder.
 *
 * @author vitor.alves
 */
@Deprecated
public class SingleObjectProperty implements SystemProperty<Object> {

    private Object singleProperty;

    private String name;

    private Class<?> type;

    public SingleObjectProperty() {
    }

    public SingleObjectProperty(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }

    public SingleObjectProperty(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> getProperties() {
        return Collections.singletonList(this.singleProperty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getProperty() {
        return this.singleProperty;
    }

    /**
     * Overrides the current property object.
     *
     * @param property a property object thar will overwrite the current property.
     */
    @Override
    public void addProperty(Object property) {
        this.singleProperty = property;
    }

    /**
     * As a single object property, it dos not have multiple properties.
     */
    @Override
    public void addProperties(List<?> properties) {
        // Only holds a single property.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getType() {
        return type != null ? type : singleProperty.getClass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return this.name != null ? name : this.getType().getSimpleName();
    }
}
