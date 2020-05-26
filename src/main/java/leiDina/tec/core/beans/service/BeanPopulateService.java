package main.java.leiDina.tec.core.beans.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.beans.model.PropertyValue;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class BeanPopulateService {

    private final BeanDefinition beanDefinition;

    private List<PropertyValue> propertyValues;

    public BeanPopulateService(List<PropertyValue> propertyValues, BeanDefinition beanDefinition) {
        this.propertyValues = propertyValues;
        this.beanDefinition = beanDefinition;
    }

    public void populate(Object beanInstance) throws Exception {
        for (PropertyValue propertyValue : propertyValues) {
            populate(propertyValue, beanInstance);
        }
    }

    private void populate(PropertyValue propertyValue, Object beanInstance) throws Exception {
        writeValue(beanInstance, this.getWriteMethodFor(propertyValue), propertyValue.getValue());
    }

    private Method getWriteMethodFor(PropertyValue propertyValue) throws NoSuchMethodException, NoSuchFieldException {
        return ReflectionUtils.getWriteMethodFor(beanDefinition.getBeanClass(), propertyValue.getName());
    }

    private void writeValue(Object beanInstance, Method writeMethod, Object value) throws InvocationTargetException, IllegalAccessException {
        ReflectionUtils.invoke(writeMethod, beanInstance, value);
    }
}
