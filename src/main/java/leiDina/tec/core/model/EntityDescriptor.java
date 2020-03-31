package main.java.leiDina.tec.core.model;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.annotations.Column;
import main.java.leiDina.tec.core.exception.EntityCreationException;
import main.java.leiDina.tec.core.io.TextToEntityDigester;
import main.java.leiDina.tec.core.persist.Persistable;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class EntityDescriptor<T extends Persistable> {

    private Map<String, PropertyDescriptor> columnProperty = new HashMap<>();

    private Class<T> type;

    public EntityDescriptor(Class<T> type) {
        this.type = type;
        this.createByType(type);
    }

    private void createByType(Class<T> type) {
        Class<?> superClass = type;
        while (superClass != null) {
            Field[] declaredFields = superClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                PropertyDescriptor propertyDescriptor;
                try {
                    propertyDescriptor = new PropertyDescriptor(declaredField.getName(), superClass);
                } catch (IntrospectionException e) {
                    break;
                }
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (writeMethod != null) {
                    Column column = writeMethod.getDeclaredAnnotation(Column.class);
                    if (column != null) {
                        String name = column.name();
                        columnProperty.put(name, propertyDescriptor);
                    } else if (propertyDescriptor.getName().equals("id")) {
                        columnProperty.put("id", propertyDescriptor);
                    }
                }
            }
            superClass = superClass.getSuperclass();
        }
    }

    public T createInstanceFromLine(String entityLine) {
        try {
            T entity = ReflectionUtils.newInstance(type);
            Map<String, String> entityProperties = new TextToEntityDigester().digest(entityLine);
            for (String key : columnProperty.keySet()) {
                String stringValue = entityProperties.get(key);
                PropertyDescriptor propertyDescriptor = columnProperty.get(key);
                Class<?> returnType = propertyDescriptor.getReadMethod().getReturnType();
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (returnType.isAssignableFrom(String.class)) {
                    ReflectionUtils.invoke(writeMethod, entity, stringValue);
                } else if (returnType.isAssignableFrom(Boolean.class)) {
                    ReflectionUtils.invoke(writeMethod, entity, Boolean.valueOf(stringValue));
                } else if (returnType.isAssignableFrom(Integer.class)) {
                    ReflectionUtils.invoke(writeMethod, entity, Integer.valueOf(stringValue));
                } else if (returnType.isAssignableFrom(Long.class)) {
                    ReflectionUtils.invoke(writeMethod, entity, Long.valueOf(stringValue));
                } else if (returnType.isAssignableFrom(Double.class)) {
                    ReflectionUtils.invoke(writeMethod, entity, Double.valueOf(stringValue));
                }
                // TODO: Add so that its possible to convert entity as well and not just primitive types.
            }
            return entity;
        } catch (ReflectiveOperationException e) {
            throw new EntityCreationException(e);
        }
    }
}
