package main.java.leiDina.tec.vinjection.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.vinjection.xml.model.bean.Bean;
import main.java.leiDina.tec.vinjection.xml.model.property.Property;

/**
 * @author vitor.alves
 */
public class BeanDefinitionImpl implements BeanDefinition {

    private String id;

    private Class<?> type;

    private Map<String, PropertyDefinition> propertyByName = new HashMap<>();

    private Object instance;

    public BeanDefinitionImpl(Bean bean) {
        this.id = bean.getId();
        this.type = bean.getType();
        this.createProperties(bean.getProperties());
    }

    private void createProperties(List<Property> properties) {
        if (properties != null) {
            for (Property property : properties) {
                propertyByName.put(property.getPropertyName(), new PropertyDefinitionImpl(this.type, property));
            }
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }

    @Override
    public PropertyDefinition getPropertyDefinitionFor(String propertyName) {
        return this.propertyByName.get(propertyName);
    }

    @Override
    public Collection<PropertyDefinition> getPropertyDefinitions() {
        return propertyByName.values();
    }

    @Override
    public <T> T getInstance() {
        return (T) this.instance;
    }

    @Override
    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
