package main.java.leiDina.tec.vinjection.xml.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.vinjection.xml.model.Definition;

/**
 * An implementation of {@link XmlParser} that uses the {@link JAXBContext}
 *
 * @author vitor.alves
 */
public class JAXBXmlParser implements XmlParser {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Definition> parse(String resourceName) throws Exception {
        List<Definition> definitions = new ArrayList<>();
        Enumeration<URL> resources  = ClassUtils.getClassLoader().getResources(resourceName);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            JAXBContext jaxbContext = JAXBContext.newInstance(Definition.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Definition definition = (Definition) unmarshaller.unmarshal(url);
            definitions.add(definition);
        }

        return definitions;
    }
}
