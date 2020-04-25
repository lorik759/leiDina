package main.java.leiDina.tec.vinjection.model;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author vitor.alves
 */
public interface PropertyDefinition {

    PropertyType getType();

    String getPropertyName();

    PropertyDescriptor getPropertyDescriptor();

    Method getWriteMethod();

    Method getReaderMethod();

}
