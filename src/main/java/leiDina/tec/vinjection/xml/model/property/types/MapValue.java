package main.java.leiDina.tec.vinjection.xml.model.property.types;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import main.java.leiDina.tec.vinjection.xml.model.property.BaseProperty;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "entry", namespace = "http://www.vaplication.com/definition")
public class MapValue extends BaseProperty {

    private String key;

    public String getKey() {
        return key;
    }

    @XmlAttribute
    public void setKey(String key) {
        this.key = key;
    }
}
