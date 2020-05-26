package main.java.leiDina.tec.core.xml.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import main.java.leiDina.tec.core.utils.ClassUtils;

/**
 * @author vitor.alves
 */
public class JAXBXmlParser<T> {

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
