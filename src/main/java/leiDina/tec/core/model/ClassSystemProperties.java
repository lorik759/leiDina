package main.java.leiDina.tec.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vitor.alves
 */
public class ClassSystemProperties {

    private Class<?> type;

    private String name;

    private List<ClassSystemProperties> classSystemProperties;

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

    public List<ClassSystemProperties> getClassSystemProperties() {
        return classSystemProperties;
    }

    public void addSystemProperty(ClassSystemProperties classSystemProperties) {
        this.classSystemProperties.add(classSystemProperties);
    }
}
