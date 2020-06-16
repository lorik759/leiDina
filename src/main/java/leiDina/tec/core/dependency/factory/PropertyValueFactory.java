package main.java.leiDina.tec.core.dependency.factory;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.dependency.exception.DependencyDefinitionException;
import main.java.leiDina.tec.core.dependency.model.ObjectRefPropertyValue;
import main.java.leiDina.tec.core.dependency.model.MapPropertyValue;
import main.java.leiDina.tec.core.dependency.model.PropertyValue;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.xml.model.ObjectRefType;
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
        if (propertyType instanceof ObjectRefType) {
            ObjectRefType objectRefType = (ObjectRefType) propertyType;
            return new ObjectRefPropertyValue(objectRefType.getRef());
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
                objectMap.put(keyType.resolveFor(mapValueType.getKey()), resolveValue(mapValueType.getObject()));
            } catch (Exception e) {
                throw new DependencyDefinitionException(BaseSystemMessages.FAILED_TO_RESOLVE_VALUE.create(), e);
            }
        });
        return new MapPropertyValue(objectMap, keyType);
    }
}
