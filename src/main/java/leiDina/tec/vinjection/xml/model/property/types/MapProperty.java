package main.java.leiDina.tec.vinjection.xml.model.property.types;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import main.java.leiDina.tec.vinjection.model.PropertyType;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "mapProperty", namespace = "http://www.vaplication.com/definition")
public class MapProperty implements XmlProperty {

    private List<MapValue> mapValues;

    public List<MapValue> getMapValues() {
        return mapValues;
    }

    @XmlElement(name = "entry", namespace = "http://www.vaplication.com/definition")
    public void setMapValues(List<MapValue> mapValues) {
        this.mapValues = mapValues;
    }

    @Override
    public PropertyType getType() {
        return PropertyType.MAP;
    }
}
