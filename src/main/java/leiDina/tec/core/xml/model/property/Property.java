package main.java.leiDina.tec.core.xml.model.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "property", namespace = "http://www.vaplication.com/definition")
public class Property {

    private String propertyName;

    private BeanProperty beanProperty;

    private ListProperty listProperty;

    private MapProperty mapProperty;

    public String getPropertyName() {
        return propertyName;
    }

    @XmlAttribute(name = "propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public BeanProperty getBeanProperty() {
        return beanProperty;
    }

    @XmlElement(name = "beanProperty", namespace = "http://www.vaplication.com/definition")
    public void setBeanProperty(BeanProperty beanProperty) {
        this.beanProperty = beanProperty;
    }

    public ListProperty getListProperty() {
        return listProperty;
    }

    @XmlElement
    public void setListProperty(ListProperty listProperty) {
        this.listProperty = listProperty;
    }

    public MapProperty getMapProperty() {
        return mapProperty;
    }

    @XmlElement(name = "mapProperty", namespace = "http://www.vaplication.com/definition")
    public void setMapProperty(MapProperty mapProperty) {
        this.mapProperty = mapProperty;
    }
}
