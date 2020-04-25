package main.java.leiDina.tec.vinjection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.vinjection.enums.DefinitionProperty;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;
import main.java.leiDina.tec.vinjection.model.BeanDefinitionImpl;
import main.java.leiDina.tec.vinjection.xml.model.bean.Bean;

/**
 * @author vitor.alves
 */
public class XmlBeanLoader implements BeanLoader {

    private final ConfigurableApplicationProvider environmentProvider;

    private final Logger logger;

    public XmlBeanLoader(ApplicationDefinitions applicationDefinitions) {
        this.environmentProvider = applicationDefinitions.getConfigurableApplicationProvider();
        this.logger = applicationDefinitions.getLogger();
    }

    @Override
    public List<BeanDefinition> loadBeanDefinitionFrom(String aPackage) {
        logger.info("Loading beans form XML : " + aPackage);
        ConfigurableApplicationEnvironment environment = environmentProvider.getEnvironmentFor(aPackage);
        SystemProperty<Bean> beanSystemProperty = environment.loadSystemPropertiesFor(DefinitionProperty.BEANS.getName());
        return this.createDefinitionFromXmlModel(beanSystemProperty.getProperties());
    }

    private List<BeanDefinition> createDefinitionFromXmlModel(List<Bean> beans) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        for (Bean bean : beans) {
            beanDefinitions.add(new BeanDefinitionImpl(bean));
        }
        return beanDefinitions;
    }
}
