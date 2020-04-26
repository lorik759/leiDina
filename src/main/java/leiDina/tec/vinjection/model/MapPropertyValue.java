package main.java.leiDina.tec.vinjection.model;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.vinjection.exception.PropertyException;
import main.java.leiDina.tec.vinjection.messages.VInjectionSystemMessages;
import main.java.leiDina.tec.vinjection.service.PropertyValueFactory;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.KeyType;
import main.java.leiDina.tec.vinjection.xml.model.property.types.MapProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.MapValue;

/**
 * @author vitor.alves
 */
public class MapPropertyValue implements PropertyValue {

    private MapProperty mapProperty;

    public MapPropertyValue(XmlProperty mapProperty) {
        this.mapProperty = (MapProperty) mapProperty;
    }

    @Override
    public Object getValue() {
        KeyType keyType = mapProperty.getKeyType();
        return this.getValue(keyType);
    }

    private Object getValue(KeyType keyType) {
        Map map = new HashMap();
        for (MapValue mapValue : this.mapProperty.getMapValues()) {
            map.put(this.resloveKey(keyType, mapValue.getKey()), this.resolveValue(mapValue.getProperty()));
        }
        return map;
    }

    private Object resolveValue(XmlProperty property) {
        return new PropertyValueFactory().create(property).getValue();
    }

    private Object resloveKey(KeyType keyType, String key) {
        switch (keyType) {
            case CLASS:
                try {
                    return Class.forName(key);
                } catch (ClassNotFoundException e) {
                    throw new PropertyException(VInjectionSystemMessages.FAILED_TO_RESOLVE_KEY_OF_TYPE.create(keyType, key), e);
                }
            case STRING:
                if (StringUtils.isEmpty(key)) {
                    throw new PropertyException(VInjectionSystemMessages.FAILED_TO_RESOLVE_KEY_OF_TYPE.create(keyType, key));
                }
                return key;
        }
        return null;
    }
}
