
package main.java.leiDina.tec.core.xml.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "propertyType", namespace = "http://www.vaplication.com/objects", propOrder = {
    "object",
    "map"
})
public class PropertyType {

    @XmlElement(namespace = "http://www.vaplication.com/objects")
    protected ObjectRefType object;
    @XmlElement(namespace = "http://www.vaplication.com/objects")
    protected MapType map;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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
     * Obtém o valor da propriedade map.
     * 
     * @return
     *     possible object is
     *     {@link MapType }
     *     
     */
    public MapType getMap() {
        return map;
    }

    /**
     * Define o valor da propriedade map.
     * 
     * @param value
     *     allowed object is
     *     {@link MapType }
     *     
     */
    public void setMap(MapType value) {
        this.map = value;
    }

    /**
     * Obtém o valor da propriedade name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define o valor da propriedade name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


    public Object getPropertyValue() {
        if (this.object != null) {
            return this.object;
        }else {
            return this.map;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PropertyType that = (PropertyType) o;
        return Objects.equals(getObject(), that.getObject()) &&
            Objects.equals(getMap(), that.getMap()) &&
            Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObject(), getMap(), getName());
    }
}
