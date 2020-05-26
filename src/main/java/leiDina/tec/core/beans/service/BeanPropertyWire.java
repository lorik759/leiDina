package main.java.leiDina.tec.core.beans.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.beans.exception.BeanWireException;
import main.java.leiDina.tec.core.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

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
        Map<Field, String> fields = getAllFieldsToWire();
        this.wire(fields);
    }

    private void wire(Map<Field, String> fields) {
        for (Field field : fields.keySet()) {
            this.setValueTo(field, beanFactory.getBean(fields.get(field)));
        }
    }

    private void setValueTo(Field field, Object value) {
        try {
            ReflectionUtils.set(field, this.bean, value);
        } catch (IllegalAccessException e) {
            throw new BeanWireException(BaseSystemMessages.FAILED_TO_WIRE_OBJECT.create(this.beanType, value.getClass()), e);
        }
    }

    private Map<Field, String> getAllFieldsToWire() {
        Map<Field, String> fields = new HashMap<>();
        this.addFieldsToWireFor(this.beanType, fields);
        return fields;
    }

    private void addFieldsToWireFor(Class<?> beanType, Map<Field, String> fields) {
        if (beanType != null) {
            fields.putAll(this.filterForAnnotatedFields(beanType.getDeclaredFields()));
            this.addFieldsToWireFor(beanType.getSuperclass(), fields);
        }
    }

    private Map<Field, String> filterForAnnotatedFields(Field[] declaredFields) {
        Map<Field, String> fields = new HashMap<>();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Resource.class)) {
                fields.put(declaredField, declaredField.getDeclaredAnnotation(Resource.class).name());
            }
        }
        return fields;
    }
}
