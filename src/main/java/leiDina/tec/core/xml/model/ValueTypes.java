
package main.java.leiDina.tec.core.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import main.java.leiDina.tec.core.service.ClassValueResolver;
import main.java.leiDina.tec.core.service.StringValueResolver;
import main.java.leiDina.tec.core.service.ValueResolver;


@XmlType(name = "valueTypes", namespace = "http://www.vaplication.com/objects")
@XmlEnum
public enum ValueTypes {

    @XmlEnumValue("class")
    CLASS("class", new ClassValueResolver()),
    @XmlEnumValue("string")
    STRING("string", new StringValueResolver());
    private final String value;

    private final ValueResolver valueResolver;

    ValueTypes(String value, ValueResolver valueResolver) {
        this.value = value;
        this.valueResolver = valueResolver;
    }

    public String value() {
        return value;
    }

    public static ValueTypes fromValue(String value) {
        for (ValueTypes c: ValueTypes.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(value);
    }

    public Object resolveFor(String value) throws Exception {
        return this.valueResolver.resolve(value);
    }
}
