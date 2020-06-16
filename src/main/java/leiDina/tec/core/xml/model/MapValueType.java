
package main.java.leiDina.tec.core.xml.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mapValueType", namespace = "http://www.vaplication.com/objects", propOrder = {
    "object"
})
public class MapValueType {

    @XmlElement(namespace = "http://www.vaplication.com/objects")
    protected ObjectRefType object;
    @XmlAttribute(name = "key", required = true)
    protected String key;

    /**
     * Obtém o valor da propriedade object.
     * 
     * @return
     *     possible object is
     *     {@link ObjectRefType }
     *     
     */
    public ObjectRefType getObject() {
        return object;
    }

    /**
     * Define o valor da propriedade object.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectRefType }
     *     
     */
    public void setObject(ObjectRefType value) {
        this.object = value;
    }

    /**
     * Obtém o valor da propriedade key.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Define o valor da propriedade key.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapValueType valueType = (MapValueType) o;
        return Objects.equals(getObject(), valueType.getObject()) &&
            Objects.equals(getKey(), valueType.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObject(), getKey());
    }
}
