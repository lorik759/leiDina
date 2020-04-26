package main.java.leiDina.tec.vinjection;

import java.util.List;
import main.java.leiDina.tec.persister.Entity;
import main.java.leiDina.tec.persister.EntityOne;
import main.java.leiDina.tec.vinjection.xml.model.Definition;
import main.java.leiDina.tec.vinjection.xml.model.bean.Bean;
import main.java.leiDina.tec.vinjection.xml.model.bean.Beans;
import main.java.leiDina.tec.vinjection.xml.model.property.Property;
import main.java.leiDina.tec.vinjection.xml.model.property.types.BeanProperty;
import main.java.leiDina.tec.vinjection.xml.service.JAXBXmlParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class JAXBXmlParserTest {

    public static final String RESOURCE_FILE = "test-definiton.xml";

    private static List<Definition> definitions;

    @BeforeAll
    protected static void setUp() throws Exception {
        JAXBXmlParser jaxbXmlParser = new JAXBXmlParser();
        definitions = jaxbXmlParser.parse(RESOURCE_FILE);
    }

    @Test
    public void testDefinition() {
        Definition definition = definitions.get(0);
        List<String> packages = definition.getPackages();
        Beans beans = definition.getBeans();
        this.testPackages(packages);
        this.testBeans(beans);
    }

    private void testBeans(Beans beans) {
        List<Bean> beanList = beans.getBeans();
        Assertions.assertNotNull(beanList);
        Assertions.assertEquals(2, beanList.size());
        Bean bean = beanList.get(0);
        Assertions.assertNotNull(bean);
        Assertions.assertEquals("basicBeanTest", bean.getId());
        Assertions.assertEquals(Entity.class, bean.getType());
        bean = beanList.get(1);
        Assertions.assertNotNull(bean);
        Assertions.assertEquals("beanRefTest", bean.getId());
        Assertions.assertEquals(EntityOne.class, bean.getType());
        Property property = bean.getProperties().get(0);
        Assertions.assertNotNull(property);
        BeanProperty beanProperty = property.getBeanProperty();
        Assertions.assertNotNull(beanProperty);
        Assertions.assertEquals("basicBeanTest", beanProperty.getRef());
    }

    private void testPackages(List<String> packages) {
        List<String> packageName = packages;
        Assertions.assertNotNull(packageName);
        Assertions.assertEquals(1, packageName.size());
        String aPackage = packageName.get(0);
        Assertions.assertEquals("second package", aPackage);
    }


}
