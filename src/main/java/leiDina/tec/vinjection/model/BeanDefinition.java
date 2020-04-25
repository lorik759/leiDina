package main.java.leiDina.tec.vinjection.model;

import java.util.Collection;

/**
 * @author vitor.alves
 */
public interface BeanDefinition {

    String getId();

    Class<?> getType();

    PropertyDefinition getPropertyDefinitionFor(String propertyName);

    Collection<PropertyDefinition> getPropertyDefinitions();

    <T> T getInstance();

    void setInstance(Object instance);

}
