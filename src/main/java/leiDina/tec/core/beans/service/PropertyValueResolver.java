package main.java.leiDina.tec.core.beans.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import main.java.leiDina.tec.core.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.beans.model.BeanRefPropertyValue;
import main.java.leiDina.tec.core.beans.model.MapPropertyValue;
import main.java.leiDina.tec.core.beans.model.PropertyValue;


/**
 * @author vitor.alves
 */
public class PropertyValueResolver {

    private final BeanFactory beanFactory;

    private final PropertyValue propertyValue;

    public PropertyValueResolver(BeanFactory beanFactory, PropertyValue propertyValue) {
        this.beanFactory = beanFactory;
        this.propertyValue = propertyValue;
    }

    public PropertyValue resolve() {
        Object resolvedValue = this.resolveValueIfNecessary(propertyValue.getValue());
        return new PropertyValue(propertyValue.getName(), resolvedValue);
    }

    private Object resolveValueIfNecessary(Object value) {
        if (value instanceof MapPropertyValue) {
            return this.resolveMapValue((MapPropertyValue) value);
        }
        if (value instanceof BeanRefPropertyValue) {
            return this.resolveBeanReferenceType((BeanRefPropertyValue) value);
        }
        return value;
    }

    private Object resolveBeanReferenceType(BeanRefPropertyValue value) {
        return this.beanFactory.getBean(value.getBeanName());
    }

    private Map resolveMapValue(MapPropertyValue value) {
        Map map = new HashMap();
        for (Entry<?, ?> entry : value.getMapValue().entrySet()) {
            map.put(entry.getKey(), resolveValueIfNecessary(entry.getValue()));
        }
        return map;
    }

}
