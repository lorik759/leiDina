package main.java.leiDina.tec.vinjection.xml.model.property.types;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import main.java.leiDina.tec.vinjection.model.PropertyType;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "beanProperty")
public class BeanProperty implements XmlProperty {

    private String ref;

    public String getRef() {
        return ref;
    }

    @XmlAttribute
    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.BEAN;
    }
}
