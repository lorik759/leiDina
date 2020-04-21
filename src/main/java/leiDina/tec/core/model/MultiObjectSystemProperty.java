package main.java.leiDina.tec.core.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author vitor.alves
 */
public class MultiObjectSystemProperty<T> implements SystemProperty<T> {

    private String name;

    private List<T> properties;

    private Class<T> type;

    public MultiObjectSystemProperty(String name, Class<T> type) {
        this.name = name;
        this.type = type;
        this.properties = new ArrayList<>();
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<T> getProperties() {
        return this.properties;
    }

    @Override
    public T getProperty() {
        return null; // MultiObject property
    }

    @Override
    public void addProperty(T property) {
        this.properties.add(property);
    }

    @Override
    public void addProperties(List<?> properties) {
        this.properties.addAll((Collection<? extends T>) properties);
    }
}
