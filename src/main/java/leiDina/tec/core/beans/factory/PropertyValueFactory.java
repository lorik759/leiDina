package main.java.leiDina.tec.core.beans.factory;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.beans.exception.BeanDefinitionException;
import main.java.leiDina.tec.core.beans.model.BeanRefPropertyValue;
import main.java.leiDina.tec.core.beans.model.MapPropertyValue;
import main.java.leiDina.tec.core.beans.model.PropertyValue;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.xml.model.BeanRefType;
import main.java.leiDina.tec.core.xml.model.MapType;
import main.java.leiDina.tec.core.xml.model.PropertyType;
import main.java.leiDina.tec.core.xml.model.ValueTypes;

/**
 * @author vitor.alves
 */
public class PropertyValueFactory {

    public PropertyValue createFor(PropertyType propertyType) {
        return this.createFor(propertyType.getName(), propertyType.getPropertyValue());
    }

    private PropertyValue createFor(String name, Object propertyType) {
        return new PropertyValue(name, this.resolveValue(propertyType));
    }

    private Object resolveValue(Object propertyType) {
        if (propertyType instanceof BeanRefType) {
            BeanRefType beanRefType = (BeanRefType) propertyType;
            return new BeanRefPropertyValue(beanRefType.getRef());
        }
        if (propertyType instanceof MapType) {
            MapType mapType = (MapType) propertyType;
            return this.resolveMapValueType(mapType);
        }
        return propertyType;
    }

    private Object resolveMapValueType(MapType mapType) {
        final Map<Object, Object> objectMap = new HashMap<>();
        ValueTypes keyType = mapType.getKeyType();
        mapType.getValue().forEach(mapValueType -> {
            try {
                objectMap.put(keyType.resolveFor(mapValueType.getKey()), resolveValue(mapValueType.getBean()));
            } catch (Exception e) {
                throw new BeanDefinitionException(BaseSystemMessages.FAILED_TO_RESOLVE_VALUE.create(), e);
            }
        });
        return new MapPropertyValue(objectMap, keyType);
    }
}
