package main.java.leiDina.tec.vinjection.service;

import main.java.leiDina.tec.vinjection.model.BeanPropertyValue;
import main.java.leiDina.tec.vinjection.model.MapPropertyValue;
import main.java.leiDina.tec.vinjection.model.PropertyType;
import main.java.leiDina.tec.vinjection.model.PropertyValue;
import main.java.leiDina.tec.vinjection.xml.model.property.XmlProperty;

/**
 * @author vitor.alves
 */
public class PropertyValueFactory {

    public PropertyValue create(XmlProperty property) {
        PropertyType type = property.getType();
        switch (type) {
            case MAP:
                return new MapPropertyValue(property);
            case BEAN:
                return new BeanPropertyValue(property);
            case LIST:
                // TODO: Create value for list
                return null;
            default:
                return null;
        }
    }
}
