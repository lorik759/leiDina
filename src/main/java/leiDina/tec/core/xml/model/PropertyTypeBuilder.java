package main.java.leiDina.tec.core.xml.model;


import main.java.leiDina.tec.core.service.Builder;

/**
 * @author vitor.alves
 */
public class PropertyTypeBuilder implements Builder<PropertyType> {

    private PropertyType propertyType;

    public PropertyTypeBuilder aPropertyType() {
        this.propertyType = new PropertyType();
        return this;
    }

    public PropertyTypeBuilder withName(String name) {
        this.propertyType.setName(name);
        return this;
    }

    public PropertyTypeBuilder withBeanRef(BeanRefType beanRef) {
        this.propertyType.setBean(beanRef);
        return this;
    }

    public PropertyTypeBuilder withMap(MapType map) {
        this.propertyType.setMap(map);
        return this;
    }

    @Override
    public PropertyType build() {
        return this.propertyType;
    }
}
