package main.java.leiDina.tec.vinjection.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.vinjection.BeanWireThreadContext;
import main.java.leiDina.tec.vinjection.exception.VijectionException;
import main.java.leiDina.tec.vinjection.messages.VInjectionSystemMessages;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;
import main.java.leiDina.tec.vinjection.service.BeanLoader;
import main.java.leiDina.tec.vinjection.service.BeanWire;
import main.java.leiDina.tec.vinjection.service.ObjectInstantiationService;
import main.java.leiDina.tec.vinjection.service.ObjectInstantiationServiceImp;
import main.java.leiDina.tec.vinjection.service.XmlBeanLoader;

/**
 * @author vitor.alves
 */
public class XmlBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionById = new HashMap<>();

    private Map<Class<?>, BeanDefinition> beanDefinitionByType = new HashMap<>();

    private final Set<String> packages;

    private final ApplicationDefinitions applicationDefinitions;

    private ObjectInstantiationService objectInstantiationService;

    public XmlBeanFactory(Set<String> packages, ApplicationDefinitions applicationDefinitions) {
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
        this.objectInstantiationService = new ObjectInstantiationServiceImp(this.beanDefinitionById);
        BeanWireThreadContext.init(new BeanWire(this));
    }

    private void createMapsOfBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            this.beanDefinitionById.put(beanDefinition.getId(), beanDefinition);
            this.beanDefinitionByType.put(beanDefinition.getType(), beanDefinition);
        }
    }

    @Override
    public BeanDefinition getBeanByType(Class<?> type) {
        BeanDefinition beanDefinition = this.beanDefinitionByType.get(type);
        if (beanDefinition == null) {
            throw new VijectionException(VInjectionSystemMessages.BEAN_NOT_FOUND.create(type));
        }
        Object instance = beanDefinition.getInstance();
        if (instance == null) {
            instance = objectInstantiationService.createFor(beanDefinition);
            beanDefinition.setInstance(instance);
        }
        return beanDefinition;
    }

    @Override
    public BeanDefinition getBeanById(String id) {
        BeanDefinition beanDefinition = this.beanDefinitionById.get(id);
        if (beanDefinition == null) {
            throw new VijectionException(VInjectionSystemMessages.BEAN_NOT_FOUND.create(id));
        }
        Object instance = beanDefinition.getInstance();
        if (instance == null) {
            instance = this.objectInstantiationService.createFor(beanDefinition);
            beanDefinition.setInstance(instance);
        }
        return beanDefinition;
    }
}
