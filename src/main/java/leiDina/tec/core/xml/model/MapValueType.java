
package main.java.leiDina.tec.core.xml.model;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de mapValueType complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="mapValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="bean" type="{http://www.vaplication.com/beans}beanRefType"/>
 *       &lt;/choice>
 *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mapValueType", namespace = "http://www.vaplication.com/beans", propOrder = {
    "bean"
})
public class MapValueType {

    @XmlElement(namespace = "http://www.vaplication.com/beans")
    protected BeanRefType bean;
    @XmlAttribute(name = "key", required = true)
    protected String key;

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
        return Objects.equals(getBean(), valueType.getBean()) &&
            Objects.equals(getKey(), valueType.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBean(), getKey());
    }
}
