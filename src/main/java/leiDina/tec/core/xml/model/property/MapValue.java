package main.java.leiDina.tec.core.xml.model.property;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "entry", namespace = "http://www.vaplication.com/definition")
public class MapValue {

    private String key;

    private BeanProperty bean;

    private ListProperty listProperty;

    private MapProperty mapProperty;

    public String getKey() {
        return key;
    }

    @XmlAttribute
    public void setKey(String key) {
        this.key = key;
    }

    public BeanProperty getBean() {
        return bean;
    }

    @XmlElement(name = "beanProperty", namespace = "http://www.vaplication.com/definition")
    public void setBean(BeanProperty bean) {
        this.bean = bean;
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
