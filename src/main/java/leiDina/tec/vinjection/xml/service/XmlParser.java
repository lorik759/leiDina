package main.java.leiDina.tec.vinjection.xml.service;

import java.util.List;
import main.java.leiDina.tec.vinjection.xml.model.Definition;

/**
 * @author vitor.alves
 */
public interface XmlParser {

    List<Definition> parse(String resourceName) throws Exception;

}
