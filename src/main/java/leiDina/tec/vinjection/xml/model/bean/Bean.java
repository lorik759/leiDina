package main.java.leiDina.tec.vinjection.xml.model.bean;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import main.java.leiDina.tec.vinjection.xml.model.property.Property;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "bean", namespace = "http://www.vaplication.com/definition")
public class Bean {

    private String id;

    private Class<?> type;

    private List<Property> properties;

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getType() {
        return type;
    }

    @XmlAttribute(name = "class")
    public void setType(Class<?> type) {
        this.type = type;
    }

    @XmlElement(name = "property", namespace = "http://www.vaplication.com/definition")
    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
