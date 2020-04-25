package main.java.leiDina.tec.vinjection.xml.model.property;

import javax.xml.bind.annotation.XmlElement;
import main.java.leiDina.tec.vinjection.xml.model.property.types.BeanProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.ListProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.MapProperty;

/**
 * @author vitor.alves
 */
public abstract class BaseProperty {

    private BeanProperty beanProperty;

    private ListProperty listProperty;

    private MapProperty mapProperty;

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

    public XmlProperty getProperty() {
        if (beanProperty != null) {
            return beanProperty;
        } else if (listProperty != null) {
            return listProperty;
        } else if (mapProperty != null) {
            return mapProperty;
        }
        return null;
    }

}
