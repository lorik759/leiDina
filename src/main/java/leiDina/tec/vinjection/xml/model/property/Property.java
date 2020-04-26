package main.java.leiDina.tec.vinjection.xml.model.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "property", namespace = "http://www.vaplication.com/definition")
public class Property extends BaseProperty {

    private String propertyName;

    public String getPropertyName() {
        return propertyName;
    }

    @XmlAttribute(name = "propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
