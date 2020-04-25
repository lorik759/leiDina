package main.java.leiDina.tec.vinjection.model;

import java.util.ArrayList;
import java.util.List;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.vinjection.xml.model.bean.Bean;

/**
 * @author vitor.alves
 */
public class BeanSystemProperty implements SystemProperty<Bean> {

    private static final String DEFINITION = "Bean";

    private List<Bean> properties = new ArrayList<>();

    @Override
    public Class<?> getType() {
        return Bean.class;
    }

    @Override
    public String getName() {
        return DEFINITION;
    }

    @Override
    public List<Bean> getProperties() {
        return this.properties;
    }

    @Override
    public Bean getProperty() {
        return properties.get(0);
    }

    @Override
    public void addProperty(Bean property) {
        this.properties.add(property);
    }

    @Override
    public void addProperties(List<?> properties) {
        this.properties = (List<Bean>) properties;
    }
}
