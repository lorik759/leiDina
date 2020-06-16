package main.java.leiDina.tec.core.dependency.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;
import main.java.leiDina.tec.core.dependency.model.PropertyValue;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class ObjectPopulateService {

    private final ObjectDefinition objectDefinition;

    private List<PropertyValue> propertyValues;

    public ObjectPopulateService(List<PropertyValue> propertyValues, ObjectDefinition objectDefinition) {
        this.propertyValues = propertyValues;
        this.objectDefinition = objectDefinition;
    }

    public void populate(Object objectInstance) throws Exception {
        for (PropertyValue propertyValue : propertyValues) {
            populate(propertyValue, objectInstance);
        }
    }

    private void populate(PropertyValue propertyValue, Object objectInstance) throws Exception {
        writeValue(objectInstance, this.getWriteMethodFor(propertyValue), propertyValue.getValue());
    }

    private Method getWriteMethodFor(PropertyValue propertyValue) throws NoSuchMethodException, NoSuchFieldException {
        return ReflectionUtils.getWriteMethodFor(objectDefinition.getObjectClass(), propertyValue.getName());
    }

    private void writeValue(Object objectInstance, Method writeMethod, Object value) throws InvocationTargetException, IllegalAccessException {
        ReflectionUtils.invoke(writeMethod, objectInstance, value);
    }
}
