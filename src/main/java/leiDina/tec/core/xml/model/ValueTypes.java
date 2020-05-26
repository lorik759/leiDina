
package main.java.leiDina.tec.core.xml.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import main.java.leiDina.tec.core.service.ClassValueResolver;
import main.java.leiDina.tec.core.service.StringValueResolver;
import main.java.leiDina.tec.core.service.ValueResolver;


/**
 * <p>Classe Java de valueTypes.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="valueTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="class"/>
 *     &lt;enumeration value="string"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "valueTypes", namespace = "http://www.vaplication.com/beans")
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
