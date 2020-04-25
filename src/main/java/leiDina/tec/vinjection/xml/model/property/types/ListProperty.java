package main.java.leiDina.tec.vinjection.xml.model.property.types;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import main.java.leiDina.tec.vinjection.model.PropertyType;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "listProperty")
public class ListProperty implements XmlProperty {

    private List<String> value;

    public List<String> getValue() {
        return value;
    }

    @XmlElement
    public void setValue(List<String> value) {
        this.value = value;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.LIST;
    }
}
