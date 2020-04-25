package main.java.leiDina.tec.core.xml;

import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.core.xml.model.Definition;
import main.java.leiDina.tec.core.xml.model.bean.Bean;
import main.java.leiDina.tec.core.xml.model.bean.Beans;
import main.java.leiDina.tec.core.xml.model.property.BeanProperty;
import main.java.leiDina.tec.core.xml.model.property.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class DefinitionTest {

    private static final String RESOURCE_FILE = "test-definiton.xml";

    private static Definition definition;

    @BeforeAll
    protected static void setUp() throws Exception {
        Enumeration<URL> resources = null;
        resources = ClassUtils.getClassLoader().getResources(RESOURCE_FILE);
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            JAXBContext jaxbContext = JAXBContext.newInstance(Definition.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            definition = (Definition) unmarshaller.unmarshal(url);
        }
    }

    @Test
    public void testDefinition() {
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
