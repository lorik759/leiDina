package main.java.leiDina.tec.core.beans.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.beans.annotations.Injected;
import main.java.leiDina.tec.core.beans.exception.BeanWireException;
import main.java.leiDina.tec.core.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class BeanPropertyWire<T> {

    private final BeanFactory beanFactory;

    private final T bean;

    private final Class<?> beanType;

    public BeanPropertyWire(BeanFactory beanFactory, T bean) {
        this.beanFactory = beanFactory;
        this.bean = bean;
        this.beanType = bean.getClass();
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
            ReflectionUtils.set(field, this.bean, value);
        } catch (IllegalAccessException e) {
            throw new BeanWireException(BaseSystemMessages.FAILED_TO_WIRE_OBJECT.create(this.beanType, value.getClass()), e);
        }
    }

    private Map<Field, Object> getAllFieldsToWire() {
        Map<Field, Object> fields = new HashMap<>();
        this.addFieldsToWireFor(this.beanType, fields);
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
            return beanFactory.getBean(resource.name());
        }
        return beanFactory.getBean(declaredField.getType());
    }

    private boolean isFieldAnnotated(Field declaredField) {
        return declaredField.isAnnotationPresent(Resource.class) || declaredField.isAnnotationPresent(Injected.class);
    }
}
