package main.java.leiDina.tec.core.beans.model;

import java.util.Set;
import main.java.leiDina.tec.core.beans.exception.BeanException;

/**
 * A root interface of a BeanDefinition. This represents the base information and properties of a bean.
 *
 * @author vitor.alves
 */
public interface BeanDefinition {

    String getName();

    Class<?> getBeanClass();

    void setParent(String parent);

    String getParent();

    void setAbstract(boolean isAbstract);

    boolean isAbstract();

    boolean isOfType(Class<?> type);

    void setDependsOn(String... dependencies);

    String[] getDependsOn();

    void addPropertyValue(PropertyValue propertyValue);

    Set<PropertyValue> getPropertyValues();

    void setLocationName(String locationName);

    String getLocationName();

    void merge(BeanDefinition beanDefinitionToMerge) throws BeanException;
}
