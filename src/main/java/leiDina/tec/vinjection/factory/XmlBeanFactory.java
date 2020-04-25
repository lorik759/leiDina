package main.java.leiDina.tec.vinjection.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;
import main.java.leiDina.tec.vinjection.service.BeanLoader;
import main.java.leiDina.tec.vinjection.service.XmlBeanLoader;

/**
 * @author vitor.alves
 */
public class XmlBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionById = new HashMap<>();

    private Map<Class<?>, BeanDefinition> beanDefinitionByType = new HashMap<>();

    private final List<String> packages;

    private final ApplicationDefinitions applicationDefinitions;

    public XmlBeanFactory(List<String> packages, ApplicationDefinitions applicationDefinitions) {
        this.packages = packages;
        this.applicationDefinitions = applicationDefinitions;
    }

    @Override
    public void init() {
        BeanLoader xmlBeanLoader = new XmlBeanLoader(applicationDefinitions);
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        for (String aPackage : packages) {
            beanDefinitions.addAll(xmlBeanLoader.loadBeanDefinitionFrom(aPackage));
        }
        this.createMapsOfBeanDefinitions(beanDefinitions);
    }

    private void createMapsOfBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            this.beanDefinitionById.put(beanDefinition.getId(), beanDefinition);
            this.beanDefinitionByType.put(beanDefinition.getType(), beanDefinition);
        }
    }

    @Override
    public BeanDefinition getBeanByType(Class<?> type) {
        return this.beanDefinitionByType.get(type);
    }

    @Override
    public BeanDefinition getBeanById(String id) {
        return this.beanDefinitionById.get(id);
    }
}
