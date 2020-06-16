package main.java.leiDina.tec.core.dependency.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.dependency.annotations.Injected;
import main.java.leiDina.tec.core.dependency.exception.DependencyWireException;
import main.java.leiDina.tec.core.dependency.factory.DependencyContainer;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class ObjectPropertyWire<T> {

    private final DependencyContainer dependencyContainer;

    private final T object;

    private final Class<?> objectType;

    public ObjectPropertyWire(DependencyContainer dependencyContainer, T object) {
        this.dependencyContainer = dependencyContainer;
        this.object = object;
        this.objectType = object.getClass();
    }

    public void wire() {
        Map<Field, Object> fields = getAllFieldsToWire();
        this.wire(fields);
    }

    private void wire(Map<Field, Object> fields) {
        for (Field field : fields.keySet()) {
            this.setValueTo(field, fields.get(field));
        }
    }

    private void setValueTo(Field field, Object value) {
        try {
            ReflectionUtils.set(field, this.object, value);
        } catch (IllegalAccessException e) {
            throw new DependencyWireException(BaseSystemMessages.FAILED_TO_WIRE_OBJECT.create(this.objectType, value.getClass()), e);
        }
    }

    private Map<Field, Object> getAllFieldsToWire() {
        Map<Field, Object> fields = new HashMap<>();
        this.addFieldsToWireFor(this.objectType, fields);
        return fields;
    }

    private void addFieldsToWireFor(Class<?> beanType, Map<Field, Object> fields) {
        if (beanType != null) {
            fields.putAll(this.filterForAnnotatedFields(beanType.getDeclaredFields()));
            this.addFieldsToWireFor(beanType.getSuperclass(), fields);
        }
    }

    private Map<Field, Object> filterForAnnotatedFields(Field[] declaredFields) {
        Map<Field, Object> fields = new HashMap<>();
        for (Field declaredField : declaredFields) {
            if (isFieldAnnotated(declaredField)) {
                fields.put(declaredField, getFieldValue(declaredField));
            }
        }
        return fields;
    }

    private Object getFieldValue(Field declaredField) {
        Resource resource = declaredField.getDeclaredAnnotation(Resource.class);
        if (resource != null && StringUtils.isNotEmpty(resource.name())) {
            return dependencyContainer.getObject(resource.name());
        }
        return dependencyContainer.getObject(declaredField.getType());
    }

    private boolean isFieldAnnotated(Field declaredField) {
        return declaredField.isAnnotationPresent(Resource.class) || declaredField.isAnnotationPresent(Injected.class);
    }
}
