package main.java.leiDina.tec.core.beans.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import main.java.leiDina.tec.core.beans.factory.BaseBeanDefinitionFactory;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.xml.model.BeanDefinitionType;
import main.java.leiDina.tec.core.xml.model.BeanType;
import main.java.leiDina.tec.core.xml.service.JAXBXmlParser;


/**
 * @author vitor.alves
 */
public class BeanResourceLoader {

    private final BaseBeanDefinitionFactory beanDefinitionFactory = new BaseBeanDefinitionFactory();

    private final String resource;

    public BeanResourceLoader(String resource) {
        this.resource = resource;
    }

    public Set<BeanDefinition> load() {
        return this.createBeanDefinitionFor(this.loadBeanTypesFromResource());
    }

    private Set<BeanDefinition> createBeanDefinitionFor(List<BeanType> beanTypes) {
        final Set<BeanDefinition> beanDefinitions = new HashSet<>();
        beanTypes.forEach(beanType -> beanDefinitions.add(beanDefinitionFactory.createFor(beanType, resource)));
        return beanDefinitions;
    }

    private List<BeanType> loadBeanTypesFromResource() {
        List<BeanType> beanTypes = new ArrayList<>();
        for (BeanDefinitionType beanDefinitionType : getBeanDefinitionTypes()) {
            if (isBeansNotEmpty(beanDefinitionType)) {
                beanTypes.addAll(beanDefinitionType.getBean());
            }
        }
        return beanTypes;
    }

    private boolean isBeansNotEmpty(BeanDefinitionType beanDefinitionType) {
        return beanDefinitionType.getBean() != null && !beanDefinitionType.getBean().isEmpty();
    }

    private List<BeanDefinitionType> getBeanDefinitionTypes() {
        try {
            return new JAXBXmlParser<BeanDefinitionType>().parse(resource, BeanDefinitionType.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
