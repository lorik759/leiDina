package main.java.leiDina.tec.core.xml.model;

import java.util.List;
import main.java.leiDina.tec.core.service.Builder;

/**
 * @author vitor.alves
 */
public class BeanTypeBuilder implements Builder<BeanType> {

    private BeanType beanType;

    public BeanTypeBuilder aBeanType() {
        this.beanType = new BeanType();
        return this;
    }

    public BeanTypeBuilder withId(String id) {
        this.beanType.setId(id);
        return this;
    }

    public BeanTypeBuilder withClass(String clazz) {
        this.beanType.setClazz(clazz);
        return this;
    }

    public BeanTypeBuilder withParent(String parent) {
        this.beanType.setParent(parent);
        return this;
    }

    public BeanTypeBuilder withProperties(List<PropertyType> properties) {
        this.beanType.setProperty(properties);
        return this;
    }

    @Override
    public BeanType build() {
        return this.beanType;
    }
}
