package main.java.leiDina.tec.vinjection.service;

import static main.java.leiDina.tec.vinjection.xml.JAXBXmlParserTest.RESOURCE_FILE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import main.java.leiDina.tec.core.context.ApplicationContext;
import main.java.leiDina.tec.core.ApplicationDefinitionUtils;
import main.java.leiDina.tec.vinjection.env.XmlConfigurableApplicationProvider;
import main.java.leiDina.tec.vinjection.xml.model.Definition;
import main.java.leiDina.tec.vinjection.xml.model.bean.Bean;
import main.java.leiDina.tec.vinjection.xml.model.bean.Beans;
import main.java.leiDina.tec.vinjection.xml.model.property.Property;
import main.java.leiDina.tec.vinjection.xml.model.property.types.BeanProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.KeyType;
import main.java.leiDina.tec.vinjection.xml.model.property.types.MapProperty;
import main.java.leiDina.tec.vinjection.xml.model.property.types.MapValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class PackageLoaderTest {

    @Test
    public void testDefinition() {
        PackageLoader packageLoader = getPackageLoader();
        Set<String> packages = packageLoader.loadAllPackages(RESOURCE_FILE);
        Assertions.assertEquals(2, packages.size());
        Assertions.assertEquals(RESOURCE_FILE, packages.toArray()[0]);
        Assertions.assertEquals("test-second-definiton.xml", packages.toArray()[1]);
    }

    public static PackageLoader getPackageLoader() {
        return new PackageLoader(ApplicationDefinitionUtils.getMockApplicationDefinitions(new XmlConfigurableApplicationProvider()));
    }

    public void test() {
        List<String> strings = new ArrayList<>();
        strings.add("teste");
        strings.add("test2");
        Definition definition = new Definition();
        definition.setPackages(strings);
        Beans beans = new Beans();
        List<Bean> beanList = new ArrayList<>();
        Bean bean = new Bean();
        bean.setId("beanVitor");
        bean.setType(ApplicationContext.class);
        List<Property> propertyList = new ArrayList<>();
        Property property = new Property();
        MapProperty mapProperty = new MapProperty();
        List<MapValue> mapValues = new ArrayList<>();
        MapValue mapValue = new MapValue();
        mapValue.setKey("testKey");
        mapValues.add(mapValue);
        mapProperty.setMapValues(mapValues);
        mapProperty.setKeyType(KeyType.CLASS);
        property.setMapProperty(mapProperty);
        BeanProperty beanProperty = new BeanProperty();
        beanProperty.setRef("refTest");
        property.setPropertyName("propertyNameTest");
//        property.setBean(beanProperty);
        propertyList.add(property);
        mapValue.setBeanProperty(beanProperty);
        bean.setProperties(propertyList);
        beanList.add(bean);
        beans.setBeans(beanList);
        definition.setBeans(beans);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Definition.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(definition, new File("products.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
