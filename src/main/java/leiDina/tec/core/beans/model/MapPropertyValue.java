package main.java.leiDina.tec.core.beans.model;

import java.util.Map;
import java.util.Objects;
import main.java.leiDina.tec.core.xml.model.ValueTypes;

/**
 * @author vitor.alves
 */
public class MapPropertyValue {

    private Map<?, ?> mapValue;

    private ValueTypes keyValueType;

    public MapPropertyValue(Map<?, ?> mapValue, ValueTypes keyValueType) {
        this.mapValue = mapValue;
        this.keyValueType = keyValueType;
    }

    public Map<?, ?> getMapValue() {
        return mapValue;
    }

    public void setMapValue(Map<Object, Object> mapValue) {
        this.mapValue = mapValue;
    }

    public ValueTypes getKeyValueType() {
        return keyValueType;
    }

    public void setKeyValueType(ValueTypes keyValueType) {
        this.keyValueType = keyValueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapPropertyValue that = (MapPropertyValue) o;
        return Objects.equals(getMapValue(), that.getMapValue()) &&
            getKeyValueType() == that.getKeyValueType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMapValue(), getKeyValueType());
    }
}
