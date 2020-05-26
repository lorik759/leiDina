package main.java.leiDina.tec.core.beans.model;

import java.util.Set;
import main.java.leiDina.tec.core.service.Builder;

/**
 * @author vitor.alves
 */
public class BaseBeanDefinitionBuilder implements Builder<BaseBeanDefinition> {

    private BaseBeanDefinition beanDefinition;

    public BaseBeanDefinitionBuilder aBaseBeanDefinition(String name, Class<?> clazz) {
        this.beanDefinition = new BaseBeanDefinition(name, clazz);
        return this;
    }

    public BaseBeanDefinitionBuilder withParent(String parent) {
        this.beanDefinition.setParent(parent);
        return this;
    }

    public BaseBeanDefinitionBuilder isAbstract(boolean isAbstract) {
        this.beanDefinition.setAbstract(isAbstract);
        return this;
    }

    public BaseBeanDefinitionBuilder withDependsOn(String... dependsOn) {
        this.beanDefinition.setDependsOn(dependsOn);
        return this;
    }

    public BaseBeanDefinitionBuilder withPropertyValues(Set<PropertyValue> propertyValues) {
        propertyValues.forEach(propertyValue -> beanDefinition.addPropertyValue(propertyValue));
        return this;
    }

    public BaseBeanDefinitionBuilder withLocationName(String locationName) {
        this.beanDefinition.setLocationName(locationName);
        return this;
    }

    @Override
    public BaseBeanDefinition build() {
        return this.beanDefinition;
    }
}
