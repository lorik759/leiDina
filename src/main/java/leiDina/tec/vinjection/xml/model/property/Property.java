package main.java.leiDina.tec.vinjection.xml.model.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import main.java.leiDina.tec.vinjection.xml.model.property.types.BeanProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.ListProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.MapProperty;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "property", namespace = "http://www.vaplication.com/definition")
public class Property extends BaseProperty{

    private String propertyName;

    public String getPropertyName() {
        return propertyName;
    }

    @XmlAttribute(name = "propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
}
