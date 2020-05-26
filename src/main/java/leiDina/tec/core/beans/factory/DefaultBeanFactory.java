package main.java.leiDina.tec.core.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.beans.exception.BeanCreationException;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.model.BaseBeanDefinition;
import main.java.leiDina.tec.core.beans.model.DefaultBeanDefinitionHolder;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.beans.model.PropertyValue;
import main.java.leiDina.tec.core.beans.service.BeanPopulateService;
import main.java.leiDina.tec.core.beans.service.BeanWire;
import main.java.leiDina.tec.core.beans.service.BeanWireImp;
import main.java.leiDina.tec.core.beans.service.ObjectInstantiationService;
import main.java.leiDina.tec.core.beans.service.PropertyValueResolver;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;

/**
 * @author vitor.alves
 */
public class DefaultBeanFactory extends DefaultBeanDefinitionHolder implements BootableBeanFactory {

    private static  final String BEAN_WIRE = "beanWire";

    private final Map<String, Object> beansByName = new HashMap<>();

    private final Map<Class<?>, Map<String, ?>> beansByType = new HashMap<>();

    private final List<String> beanNamesBeingResolved = new ArrayList<>();

    @Override
    public <T> T getBean(String beanName) throws BeanException {
        Object bean = this.getCachedBean(beanName);
        if (bean == null) {
            bean = this.createInstanceFor(beanName);
            this.addBeanToCache(beanName, bean);
        }
        return (T) bean;
    }

    private void addBeanToCache(String beanName, Object bean) {
        this.beansByName.put(beanName, bean);
    }

    private Object createInstanceFor(String beanName) throws BeanException {
        this.checkIfAlreadyBeingResolved(beanName);
        this.addBeanToBeingResolved(beanName);
        Object beanInstance = this.doCreateInstanceFor(beanName);
        this.removeBeanNameFromBeingResolved(beanName);
        return beanInstance;
    }

    private void addBeanToBeingResolved(String beanName) {
        this.beanNamesBeingResolved.add(beanName);
    }

    private Object doCreateInstanceFor(String beanName) {
        BeanDefinition beanDefinition = this.getBeanDefinitionForName(beanName);
        Object beanInstance = this.createInstanceFor(beanDefinition);
        this.populateBean(beanDefinition, beanInstance);
        return beanInstance;
    }

    private void removeBeanNameFromBeingResolved(String beanName) {
        this.beanNamesBeingResolved.remove(beanName);
    }

    private void checkIfAlreadyBeingResolved(String beanName) {
        if (this.beanNamesBeingResolved.contains(beanName)) {
            throw new BeanCreationException(BaseSystemMessages.CIRCULAR_BEAN_DEPENDENCY.create(beanName));
        }
    }

    private void populateBean(BeanDefinition beanDefinition, Object beanInstance) {
        this.doPopulateBean(beanDefinition, beanInstance, this.getResolvedPropertyValues(beanDefinition));
    }

    private void doPopulateBean(BeanDefinition beanDefinition, Object beanInstance, List<PropertyValue> resolvedPropertyValues)
        throws BeanCreationException {
        try {
            new BeanPopulateService(resolvedPropertyValues, beanDefinition).populate(beanInstance);
        } catch (Exception e) {
            throw new BeanCreationException(BaseSystemMessages.FAILED_TO_POPULATE_BEAN
                .create(beanDefinition.getName(), beanDefinition.getBeanClass(), beanDefinition.getLocationName()), e);
        }
    }

    private List<PropertyValue> getResolvedPropertyValues(BeanDefinition beanDefinition) {
        final List<PropertyValue> resolvedPropertyValues = new ArrayList<>();
        beanDefinition.getPropertyValues().forEach(propertyValue -> resolvedPropertyValues.add(this.getResolvedValue(propertyValue)));
        return resolvedPropertyValues;
    }

    private PropertyValue getResolvedValue(PropertyValue propertyValue) {
        return new PropertyValueResolver(this, propertyValue).resolve();
    }

    private Object createInstanceFor(BeanDefinition beanDefinition) throws BeanCreationException {
        try {
            return new ObjectInstantiationService(beanDefinition).createInstance();
        } catch (ReflectiveOperationException e) {
            throw new BeanCreationException(BaseSystemMessages.FAILED_TO_INSTANTIATE_BEAN
                .create(beanDefinition.getName(), beanDefinition.getBeanClass(), beanDefinition.getLocationName()));
        }
    }

    private <T> T getCachedBean(String beanName) {
        return (T) this.beansByName.get(beanName);
    }

    @Override
    public <T> T getBean(Class<?> type) {
        return this.getBean(this.getBeanName(type));
    }

    @Override
    public boolean beanExists(String name) {
        return beansByName.get(name) != null;
    }

    @Override
    public <T> Map<String, T> getBeansOfTypes(Class<T> type) {
        Map<String, T> candidateBeans = this.getCachedBeans(type);
        if (candidateBeans == null) {
            candidateBeans = this.findBeansFor(type);
            this.addMapToCache(type, candidateBeans);
        }
        return candidateBeans;
    }

    private <T> Map<String, T> findBeansFor(Class<T> type) {
        final Map<String, T> candidateBeans = new HashMap<>();
        this.findCandidatesFor(type).forEach(candidateName -> candidateBeans.put(candidateName, this.getBean(candidateName)));
        return candidateBeans;
    }

    private void addMapToCache(Class<?> type, Map<String, ?> beans) {
        this.beansByType.put(type, beans);
    }

    private <T> List<String> findCandidatesFor(Class<T> type) {
        List<String> possibleBeanNamesCandidates = new ArrayList<>();
        for (String beanName : this.getAllBeanNames()) {
            BeanDefinition beanDefinition = this.getBeanDefinitionForName(beanName);
            if (beanDefinition.isOfType(type)) {
                possibleBeanNamesCandidates.add(beanName);
            }
        }
        return possibleBeanNamesCandidates;
    }

    private <T> Map<String, T> getCachedBeans(Class<T> type) {
        return (Map<String, T>) this.beansByType.get(type);
    }

    @Override
    public Class<?> getBeanType(String name) throws BeanException {
        return this.getBeanDefinitionForName(name).getBeanClass();
    }

    @Override
    public void instantiateSingletons() {
        this.createBeanWire();
        for (String beanName : this.getAllBeanNames()) {
            if (!this.beanExists(beanName)) {
                this.getBean(beanName);
            }
        }
    }

    private void createBeanWire() {
        this.addBeanDefinition(new BaseBeanDefinition(BEAN_WIRE, BeanWire.class));
        this.addBeanToCache(BEAN_WIRE, new BeanWireImp(this));
    }
}
