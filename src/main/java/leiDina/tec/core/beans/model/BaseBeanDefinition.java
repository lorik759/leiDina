package main.java.leiDina.tec.core.beans.model;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import main.java.leiDina.tec.core.beans.exception.BeanDefinitionException;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.StringUtils;


/**
 * @author vitor.alves
 */
public class BaseBeanDefinition implements BeanDefinition {

    private final String name;

    private final Class<?> beanClass;

    private String parent;

    private boolean abstractMark;

    private String[] dependsOn;

    private Set<PropertyValue> propertyValues = new HashSet<>();

    private String locationName;

    public BaseBeanDefinition(String name, Class<?> beanClass) {
        this.name = name;
        this.beanClass = beanClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
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
        return type.isAssignableFrom(this.getBeanClass());
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
    public void merge(BeanDefinition beanDefinitionToMerge) throws BeanException {
        this.validateBeanToMerge(beanDefinitionToMerge);
        this.setDependsOn(mergeDependencies(beanDefinitionToMerge));
        this.mergePropertiesValues(beanDefinitionToMerge);
    }

    private void validateBeanToMerge(BeanDefinition beanDefinitionToMerge) {
        if (beanDoesntHaveParent() || beanToMergeIsNotParent(beanDefinitionToMerge)) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_IS_NOT_PARENT.create(beanDefinitionToMerge.getName(), getName()));
        }
        if (beanDoesntExtendsParent(beanDefinitionToMerge)) {
            throw new BeanDefinitionException(BaseSystemMessages.BEAN_DOESNT_EXTENDS_PARENT
                .create(this.getName(), getBeanClass(), beanDefinitionToMerge.getName(), beanDefinitionToMerge.getBeanClass()));
        }
    }

    private void mergePropertiesValues(BeanDefinition beanDefinitionToMerge) {
        beanDefinitionToMerge.getPropertyValues().forEach(this::addPropertyValue);
    }

    private boolean beanDoesntExtendsParent(BeanDefinition beanDefinitionToMerge) {
        return !beanDefinitionToMerge.getBeanClass().isAssignableFrom(this.getBeanClass());
    }

    private boolean beanDoesntHaveParent() {
        return StringUtils.isEmpty(this.getParent());
    }

    private boolean beanToMergeIsNotParent(BeanDefinition beanDefinitionToMerge) {
        return !beanDefinitionToMerge.getName().equals(this.getParent());
    }

    private String[] mergeDependencies(BeanDefinition beanDefinitionToMerge) {
        List<String> dependenciesToMerge = asList(beanDefinitionToMerge.getDependsOn());
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
        BaseBeanDefinition that = (BaseBeanDefinition) o;
        return abstractMark == that.abstractMark &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getBeanClass(), that.getBeanClass()) &&
            Objects.equals(getParent(), that.getParent()) &&
            Arrays.equals(getDependsOn(), that.getDependsOn()) &&
            Objects.equals(getPropertyValues(), that.getPropertyValues()) &&
            Objects.equals(getLocationName(), that.getLocationName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getBeanClass(), getParent(), abstractMark, getPropertyValues(), getLocationName());
        result = 31 * result + Arrays.hashCode(getDependsOn());
        return result;
    }
}
