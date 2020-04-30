package main.java.leiDina.tec.vinjection.xml.service;

import java.util.List;
import main.java.leiDina.tec.vinjection.xml.model.Definition;

/**
 * A root interface for a xml parser, that converts a xml to a {@link Definition}.
 *
 * @author vitor.alves
 */
public interface XmlParser {

    /**
     * Converts the xml to a {@link Definition}.
     *
     * @param resourceName the name of the xml.
     * @return a list of {@link Definition}.
     * @throws Exception an {@link Exception}.
     */
    List<Definition> parse(String resourceName) throws Exception;

}
