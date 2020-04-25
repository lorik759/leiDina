package main.java.leiDina.tec.core.xml.model.property;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vitor.alves
 */
@XmlRootElement(name = "mapProperty", namespace = "http://www.vaplication.com/definition")
public class MapProperty {

    private List<MapValue> mapValues;

    public List<MapValue> getMapValues() {
        return mapValues;
    }

    @XmlElement(name = "entry", namespace = "http://www.vaplication.com/definition")
    public void setMapValues(List<MapValue> mapValues) {
        this.mapValues = mapValues;
    }
}
