package main.java.leiDina.tec.vinjection.model;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import main.java.leiDina.tec.vinjection.exception.VijectionException;
import main.java.leiDina.tec.vinjection.messages.VInjectionSystemMessages;
import main.java.leiDina.tec.vinjection.service.PropertyValueFactory;
import main.java.leiDina.tec.vinjection.xml.model.property.Property;

/**
 * @author vitor.alves
 */
public class PropertyDefinitionImpl implements PropertyDefinition {

    private final String name;

    private final PropertyDescriptor propertyDescriptor;

    private final PropertyType type;

    private PropertyValue value;

    public PropertyDefinitionImpl(Class<?> beanClass, Property property) {
        this.name = property.getPropertyName();
        this.type = property.getProperty().getType();
        this.value = new PropertyValueFactory().create(property.getProperty());
        try {
            this.propertyDescriptor = new PropertyDescriptor(this.name, beanClass);
        } catch (IntrospectionException e) {
            throw new VijectionException(VInjectionSystemMessages.UNABLE_TO_RESOLVE_PROPERTY.create(this.name, beanClass), e);
        }
    }

    @Override
    public PropertyValue getPropertyValue() {
        return this.value;
    }

    @Override
    public PropertyType getType() {
        return this.type;
    }

    @Override
    public String getPropertyName() {
        return this.name;
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor() {
        return this.propertyDescriptor;
    }

    @Override
    public Method getWriteMethod() {
        return this.propertyDescriptor.getWriteMethod();
    }

    @Override
    public Method getReaderMethod() {
        return this.propertyDescriptor.getReadMethod();
    }
}
