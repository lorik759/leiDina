package main.java.leiDina.tec.vinjection.xml.model.property.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * @author vitor.alves
 */
@XmlType(name = "mapPropertyValues", namespace = "http://www.vaplication.com/definition")
@XmlEnum
public enum KeyType {

    @XmlEnumValue("class")
    CLASS,

    @XmlEnumValue("string")
    STRING
}
