package main.java.leiDina.tec.vinjection;

import static main.java.leiDina.tec.vinjection.PackageLoaderTest.getPackageLoader;
import static main.java.leiDina.tec.vinjection.JAXBXmlParserTest.RESOURCE_FILE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import main.java.leiDina.tec.vinjection.env.XmlConfigurableApplicationProvider;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;
import main.java.leiDina.tec.vinjection.service.PackageLoader;
import main.java.leiDina.tec.vinjection.service.XmlBeanLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class XmlBeanLoaderTest {

    @Test
    public void testDefinition() {
        PackageLoader packageLoader = getPackageLoader();
        Set<String> packages = packageLoader.loadAllPackages(RESOURCE_FILE);
        XmlBeanLoader beanLoader = new XmlBeanLoader(ApplicationDefinitionUtils.getMockApplicationDefinitions(new XmlConfigurableApplicationProvider()));
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        for (String aPackage : packages) {
            beanDefinitions.addAll(beanLoader.loadBeanDefinitionFrom(aPackage));
        }
        Assertions.assertEquals(4, beanDefinitions.size());
    }

}
