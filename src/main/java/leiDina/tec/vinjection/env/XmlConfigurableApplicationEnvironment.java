package main.java.leiDina.tec.vinjection.env;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.utils.Tuple;
import main.java.leiDina.tec.core.utils.UnmodifiableTuple;
import main.java.leiDina.tec.vinjection.enums.DefinitionProperty;
import main.java.leiDina.tec.vinjection.exception.VijectionException;
import main.java.leiDina.tec.vinjection.messages.VInjectionSystemMessages;
import main.java.leiDina.tec.vinjection.model.BeanSystemProperty;
import main.java.leiDina.tec.vinjection.model.PackageSystemProperty;
import main.java.leiDina.tec.vinjection.xml.model.Definition;
import main.java.leiDina.tec.vinjection.xml.model.bean.Bean;
import main.java.leiDina.tec.vinjection.xml.model.bean.Beans;
import main.java.leiDina.tec.vinjection.xml.service.JAXBXmlParser;
import main.java.leiDina.tec.vinjection.xml.service.XmlParser;

/**
 * @author vitor.alves
 */
public class XmlConfigurableApplicationEnvironment implements ConfigurableApplicationEnvironment {

    private final String environmentLocation;

    private final XmlParser xmlParser;

    public XmlConfigurableApplicationEnvironment(String environmentLocation) {
        this.environmentLocation = environmentLocation;
        this.xmlParser = new JAXBXmlParser();
    }

    @Override
    public <T> SystemProperty<T> loadSystemPropertiesFor(String key) {
        List<Definition> definitions;
        try {
            definitions = xmlParser.parse(environmentLocation);
        } catch (Exception e) {
            throw new VijectionException(VInjectionSystemMessages.UNABLE_TO_PARSE_XML.create(environmentLocation));
        }
        return this.getSistemPropertyFor(definitions, key);
    }

    private <T> SystemProperty<T> getSistemPropertyFor(List<Definition> definitions, String key) {
        Tuple<List<Bean>, List<String>> tuple = this.getValuesFromDefinitions(definitions);
        if (DefinitionProperty.BEANS.getName().equals(key)) {
            BeanSystemProperty beanSystemProperty = new BeanSystemProperty();
            beanSystemProperty.addProperties(tuple.getHead());
            return (SystemProperty<T>) beanSystemProperty;
        } else {
            PackageSystemProperty packageSystemProperty = new PackageSystemProperty();
            packageSystemProperty.addProperties(tuple.getTail());
            return (SystemProperty<T>) packageSystemProperty;
        }
    }

    private Tuple<List<Bean>, List<String>> getValuesFromDefinitions(List<Definition> definitions) {
        List<Bean> beans = new ArrayList<>();
        List<String> packages = new ArrayList<>();
        for (Definition definition : definitions) {
            Beans definitionBeans = definition.getBeans();
            beans.addAll(definitionBeans != null ? definitionBeans.getBeans() : Collections.emptyList());
            List<String> definitionPackages = definition.getPackages();
            packages.addAll(definitionPackages != null ? definitionPackages : Collections.emptyList());
        }
        return new UnmodifiableTuple<>(beans, packages);
    }
}
