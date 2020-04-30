package main.java.leiDina.tec.vinjection.model;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;

/**
 * @author vitor.alves
 */
public interface PropertyDefinition {

    PropertyValue getPropertyValue();

    PropertyType getType();

    Class<?> getPropertyClass();

    String getPropertyName();

    PropertyDescriptor getPropertyDescriptor();

    Method getWriteMethod();

    Method getReaderMethod();

}
