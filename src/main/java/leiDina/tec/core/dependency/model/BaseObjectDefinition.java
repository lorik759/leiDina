package main.java.leiDina.tec.core.dependency.model;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import main.java.leiDina.tec.core.dependency.exception.DependencyDefinitionException;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.StringUtils;


/**
 * @author vitor.alves
 */
public class BaseObjectDefinition implements ObjectDefinition {

    private final String name;

    private final Class<?> objectClass;

    private String parent;

    private boolean abstractMark;

    private String[] dependsOn;

    private Set<PropertyValue> propertyValues = new HashSet<>();

    private String locationName;

    public BaseObjectDefinition(String name, Class<?> objectClass) {
        this.name = name;
        this.objectClass = objectClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getObjectClass() {
        return objectClass;
    }

    @Override
    public String getParent() {
        return parent;
    }

    @Override
    public void setParent(String parent) {
        this.parent = parent;
    }

    public boolean isAbstract() {
        return abstractMark;
    }

    public void setAbstract(boolean abstractMark) {
        this.abstractMark = abstractMark;
    }

    @Override
    public boolean isOfType(Class<?> type) {
        return type.isAssignableFrom(this.getObjectClass());
    }

    @Override
    public String[] getDependsOn() {
        return dependsOn;
    }

    @Override
    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

    @Override
    public Set<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public void setDependsOn(String[] dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public void merge(ObjectDefinition objectDefinitionToMerge) throws DependencyException {
        this.validateObjectToMerge(objectDefinitionToMerge);
        this.setDependsOn(mergeDependencies(objectDefinitionToMerge));
        this.mergePropertiesValues(objectDefinitionToMerge);
    }

    private void validateObjectToMerge(ObjectDefinition objectDefinitionToMerge) {
        if (objectDoesntHaveParent() || objectToMergeIsNotParent(objectDefinitionToMerge)) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_IS_NOT_PARENT.create(objectDefinitionToMerge.getName(), getName()));
        }
        if (objectDoesntExtendsParent(objectDefinitionToMerge)) {
            throw new DependencyDefinitionException(BaseSystemMessages.OBJECT_DOESNT_EXTENDS_PARENT
                .create(this.getName(), getObjectClass(), objectDefinitionToMerge.getName(), objectDefinitionToMerge.getObjectClass()));
        }
    }

    private void mergePropertiesValues(ObjectDefinition objectDefinitionToMerge) {
        objectDefinitionToMerge.getPropertyValues().forEach(this::addPropertyValue);
    }

    private boolean objectDoesntExtendsParent(ObjectDefinition objectDefinitionToMerge) {
        return !objectDefinitionToMerge.getObjectClass().isAssignableFrom(this.getObjectClass());
    }

    private boolean objectDoesntHaveParent() {
        return StringUtils.isEmpty(this.getParent());
    }

    private boolean objectToMergeIsNotParent(ObjectDefinition objectDefinitionToMerge) {
        return !objectDefinitionToMerge.getName().equals(this.getParent());
    }

    private String[] mergeDependencies(ObjectDefinition objectDefinitionToMerge) {
        List<String> dependenciesToMerge = asList(objectDefinitionToMerge.getDependsOn());
        if (this.getDependsOn() != null) {
            dependenciesToMerge.addAll(asList(this.getDependsOn()));
        }
        return (String[]) dependenciesToMerge.toArray();
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseObjectDefinition that = (BaseObjectDefinition) o;
        return abstractMark == that.abstractMark &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getObjectClass(), that.getObjectClass()) &&
            Objects.equals(getParent(), that.getParent()) &&
            Arrays.equals(getDependsOn(), that.getDependsOn()) &&
            Objects.equals(getPropertyValues(), that.getPropertyValues()) &&
            Objects.equals(getLocationName(), that.getLocationName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getObjectClass(), getParent(), abstractMark, getPropertyValues(), getLocationName());
        result = 31 * result + Arrays.hashCode(getDependsOn());
        return result;
    }
}
