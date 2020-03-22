package main.java.leiDina.tec.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A model for system properties of type classes.
 *
 * @author vitor.alves
 */
public class ClassSystemProperties implements SystemProperties<Class<?>> {

    private Class<?> type;

    private String name;

    private List<Class<?>> classSystemProperties;

    public ClassSystemProperties(Class<?> type, String name) {
        this.type = type;
        this.name = name;
        this.classSystemProperties = new ArrayList<>();
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Class<?>> getSystemProperties() {
        return classSystemProperties;
    }

    public void addSystemProperty(Class<?> classSystemProperties) {
        this.classSystemProperties.add(classSystemProperties);
    }
}
