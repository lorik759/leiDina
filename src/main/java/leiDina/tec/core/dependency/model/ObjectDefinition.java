package main.java.leiDina.tec.core.dependency.model;

import java.util.Set;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;

/**
 * A root interface of a ObjectDefinition. This represents the base information and properties of a object.
 *
 * @author vitor.alves
 */
public interface ObjectDefinition {

    String getName();

    Class<?> getObjectClass();

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

    void merge(ObjectDefinition objectDefinitionToMerge) throws DependencyException;
}
