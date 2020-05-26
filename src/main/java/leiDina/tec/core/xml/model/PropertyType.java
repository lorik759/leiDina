
package main.java.leiDina.tec.core.xml.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de propertyType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="propertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="bean" type="{http://www.vaplication.com/beans}beanRefType"/>
 *         &lt;element name="map" type="{http://www.vaplication.com/beans}mapType"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "propertyType", namespace = "http://www.vaplication.com/beans", propOrder = {
    "bean",
    "map"
})
public class PropertyType {

    @XmlElement(namespace = "http://www.vaplication.com/beans")
    protected BeanRefType bean;
    @XmlElement(namespace = "http://www.vaplication.com/beans")
    protected MapType map;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Obtém o valor da propriedade bean.
     * 
     * @return
     *     possible object is
     *     {@link BeanRefType }
     *     
     */
    public BeanRefType getBean() {
        return bean;
    }

    /**
     * Define o valor da propriedade bean.
     * 
     * @param value
     *     allowed object is
     *     {@link BeanRefType }
     *     
     */
    public void setBean(BeanRefType value) {
        this.bean = value;
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
        if (this.bean != null) {
            return this.bean;
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
        return Objects.equals(getBean(), that.getBean()) &&
            Objects.equals(getMap(), that.getMap()) &&
            Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBean(), getMap(), getName());
    }
}
