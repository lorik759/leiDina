package main.java.leiDina.tec.core.xml.model.property;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "listProperty")
public class ListProperty {

    private List<String> value;

    public List<String> getValue() {
        return value;
    }

    @XmlElement
    public void setValue(List<String> value) {
        this.value = value;
    }
}
