package main.java.leiDina.tec.core.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author vitor.alves
 */
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

    @Override
    public List<Object> getProperties() {
        return Collections.singletonList(this.singleProperty);
    }

    @Override
    public Object getProperty() {
        return this.singleProperty;
    }

    @Override
    public void addProperty(Object property) {
        this.singleProperty = property;
    }

    @Override
    public Class<?> getType() {
        return type != null ? type : singleProperty.getClass();
    }

    @Override
    public String getName() {
        return this.name != null ? name : this.getType().getSimpleName();
    }

    @Override
    public void addProperties(Collection<?> properties) {
        // Only holds a single property.
    }
}
