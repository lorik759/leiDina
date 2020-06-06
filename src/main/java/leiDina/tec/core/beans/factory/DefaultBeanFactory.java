package main.java.leiDina.tec.core.beans.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.beans.exception.BeanCreationException;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.model.BaseBeanDefinition;
import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.beans.model.DefaultBeanDefinitionHolder;
import main.java.leiDina.tec.core.beans.model.PropertyValue;
import main.java.leiDina.tec.core.beans.service.BeanPopulateService;
import main.java.leiDina.tec.core.beans.service.BeanWire;
import main.java.leiDina.tec.core.beans.service.BeanWireImp;
import main.java.leiDina.tec.core.beans.service.ObjectInstantiationService;
import main.java.leiDina.tec.core.beans.service.PropertyValueResolver;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;

/**
 * The default implementation of the {@link BootableBeanFactory}. This bean factory creates eagerly singleton instantiations for all {@link
 * BeanDefinition} register tho this object.
 *
 * @author vitor.alves
 */
public class DefaultBeanFactory extends DefaultBeanDefinitionHolder implements BootableBeanFactory {

    private static final String BEAN_WIRE = "beanWire";

    private final Map<String, Object> beansByName = new HashMap<>();

    private final Map<Class<?>, Map<String, ?>> beansByType = new HashMap<>();

    private final List<String> beanNamesBeingResolved = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getBean(String beanName) throws BeanException {
        return (T) getOrCreateBean(beanName);
    }

    /**
     * gets or creates a bean instance for the specified bean name.
     *
     * @param beanName the name of the required bean.
     * @return the instance of the bean.
     */
    private Object getOrCreateBean(String beanName) {
        Object bean = this.getCachedBean(beanName);
        if (bean == null) {
            bean = this.createInstanceFor(beanName);
            this.addBeanToCache(beanName, bean);
        }
        return bean;
    }

    /**
     * Adds the bean instance to this factory cache.
     *
     * @param beanName the name of the bean.
     * @param bean the instance of the bean.
     */
    private void addBeanToCache(String beanName, Object bean) {
        this.beansByName.put(beanName, bean);
    }

    /**
     * Creates the instance of the specified bean name, based on a {@link BeanDefinition} with the same name. To create the instance, it must first
     * check if the bean is already being resolved, if not the name of the bean is added to the list of beans being resolved, and an instance is
     * created for it.
     *
     * @param beanName the name of the bean.
     * @return the instance of the bean with the specified name.
     */
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

    /**
     * Populates the bean instance with its dependencies mapped in the {@link BeanDefinition}.
     *
     * @param beanDefinition the {@link BeanDefinition} with all the dependencies of the bean instance.
     * @param beanInstance the instance of the mapped bean in the specified {@link BeanDefinition}.
     */
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

    /**
     * Gets the resolved values of the {@link PropertyValue} from the {@link BeanDefinition}.
     *
     * @param beanDefinition the {@link BeanDefinition} with the unresolved {@link PropertyValue}.
     * @return a list of the resolved {@link PropertyValue} from within the {@link BeanDefinition}.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getBean(Class<?> type) {
        return this.getBean(this.getBeanName(type));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean beanExists(String name) {
        return beansByName.get(name) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Map<String, T> getBeansOfTypes(Class<T> type) {
        Map<String, T> candidateBeans = this.getCachedBeans(type);
        if (candidateBeans == null) {
            candidateBeans = this.findBeansFor(type);
            this.addMapToCache(type, candidateBeans);
        }
        return candidateBeans;
    }

    /**
     * finds all beans of the specifide type and creates a map the bean insentience by the bean name.
     *
     * @param type the type of the specified beans.
     * @return a map of the bean instance by bean name.
     */
    private <T> Map<String, T> findBeansFor(Class<T> type) {
        final Map<String, T> candidateBeans = new HashMap<>();
        this.findCandidatesFor(type).forEach(candidateName -> candidateBeans.put(candidateName, this.getBean(candidateName)));
        return candidateBeans;
    }

    private void addMapToCache(Class<?> type, Map<String, ?> beans) {
        this.beansByType.put(type, beans);
    }

    /**
     * Finds all bean names that are of the specified type.
     *
     * @param type the type of the searched beans.
     * @return a list of all bean names that are of the specified type.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getBeanType(String name) throws BeanException {
        return this.getBeanDefinitionForName(name).getBeanClass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void instantiateSingletons() {
        this.instantiateFactoryBeans();
        this.instantiateMappedBeans();
    }

    /**
     * Instantiate all beans that where mapped by the {@link BeanDefinition} and registered to this bean factory.
     */
    private void instantiateMappedBeans() {
        for (String beanName : this.getAllBeanNames()) {
            if (!this.beanExists(beanName)) {
                this.getBean(beanName);
            }
        }
    }

    /**
     * Instantiates factory beans. This ar bean that a provided by default, by the bean factory.
     */
    private void instantiateFactoryBeans() {
        this.createBeanWire();
    }

    private void createBeanWire() {
        this.addBeanDefinition(new BaseBeanDefinition(BEAN_WIRE, BeanWire.class));
        this.addBeanToCache(BEAN_WIRE, new BeanWireImp(this));
    }
}
