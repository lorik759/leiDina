
package main.java.leiDina.tec.core.xml.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mapType", namespace = "http://www.vaplication.com/objects", propOrder = {
    "value"
})
public class MapType {

    @XmlElement(namespace = "http://www.vaplication.com/objects", required = true)
    protected List<MapValueType> value;
    @XmlAttribute(name = "key-type")
    protected ValueTypes keyType;

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MapValueType }
     * 
     * 
     */
    public List<MapValueType> getValue() {
        if (value == null) {
            value = new ArrayList<MapValueType>();
        }
        return this.value;
    }

    public void setValue(List<MapValueType> value) {
        this.value = value;
    }

    /**
     * Obtém o valor da propriedade keyType.
     * 
     * @return
     *     possible object is
     *     {@link ValueTypes }
     *     
     */
    public ValueTypes getKeyType() {
        if (keyType == null) {
            return ValueTypes.CLASS;
        } else {
            return keyType;
        }
    }

    /**
     * Define o valor da propriedade keyType.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueTypes }
     *     
     */
    public void setKeyType(ValueTypes value) {
        this.keyType = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapType mapType = (MapType) o;
        return Objects.equals(getValue(), mapType.getValue()) &&
            getKeyType() == mapType.getKeyType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getKeyType());
    }
}
