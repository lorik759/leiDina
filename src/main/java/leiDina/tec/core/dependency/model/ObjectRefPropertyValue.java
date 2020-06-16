package main.java.leiDina.tec.core.dependency.model;

import java.util.Objects;

/**
 * @author vitor.alves
 */
public class ObjectRefPropertyValue {

    private final String objectName;

    public ObjectRefPropertyValue(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectName() {
        return objectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectRefPropertyValue that = (ObjectRefPropertyValue) o;
        return Objects.equals(getObjectName(), that.getObjectName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectName());
    }
}
