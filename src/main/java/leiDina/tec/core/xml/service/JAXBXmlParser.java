package main.java.leiDina.tec.core.xml.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import main.java.leiDina.tec.core.utils.ClassUtils;

/**
 * A simple xml parser that uses {@link JAXBContext} to convert a xml to a java object.
 *
 * @author vitor.alves
 */
public class JAXBXmlParser<T> {

    /**
     * Converts the xml file to a java object.
     *
     * @param xmlName the xml file name to be converted.
     * @param type the type of the object in which the xml file will be converted to.
     * @return a list of objects that represents the xml file.
     * @throws Exception a {@link Exception}.
     */
    public List<T> parse(String xmlName, Class<T> type) throws Exception {
        List<T> definitions = new ArrayList<>();
        Enumeration<URL> resources  = ClassUtils.getClassLoader().getResources(xmlName);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T definition = (T) unmarshaller.unmarshal(url);
            definitions.add(definition);
        }
        return definitions;
    }
}
