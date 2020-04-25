package main.java.leiDina.tec.core.xml.model.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "beanProperty")
public class BeanProperty {

    private String ref;

    public String getRef() {
        return ref;
    }

    @XmlAttribute
    public void setRef(String ref) {
        this.ref = ref;
    }
}
