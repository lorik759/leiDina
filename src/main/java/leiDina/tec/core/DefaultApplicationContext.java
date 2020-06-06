package main.java.leiDina.tec.core;

import java.util.Map;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.factory.BootableBeanFactory;
import main.java.leiDina.tec.core.beans.factory.DefaultBeanFactory;
import main.java.leiDina.tec.core.env.ApplicationProperty;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;


/**
 * This is the default implementation of the interface {@link ApplicationContext}. This context initializes all default properties of the
 * VApplication. This uses the {@link DefaultBeanFactory} for the instantiation and management af all bean instance. This also initializes and
 * configurers the bean factory.
 *
 * @author vitor.alves
 */
public class DefaultApplicationContext implements ApplicationContext {

    private final BootableBeanFactory beanFactory;

    private ConfigurableApplicationEnvironment configurableApplicationEnvironment;

    public DefaultApplicationContext() {
        this.beanFactory = new DefaultBeanFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfigurableApplicationEnvironment(ConfigurableApplicationEnvironment configurableApplicationEnvironment) {
        this.configurableApplicationEnvironment = configurableApplicationEnvironment;
    }

    /**
     * This method loads the {@link main.java.leiDina.tec.core.beans.model.BeanDefinition} to the {@link DefaultBeanFactory} and starts the bean
     * factory.
     */
    @Override
    public void initialize() {
        this.loadBeanDefinitions();
        this.startBeanFactory();
    }

    private void startBeanFactory() {
        this.beanFactory.instantiateSingletons();
    }

    private void loadBeanDefinitions() {
        for (ApplicationProperty loadApplicationProperty : configurableApplicationEnvironment.loadApplicationProperties()) {
            loadApplicationProperty.registerBeansTo(this.beanFactory);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getBean(String beanName) throws BeanException {
        return this.beanFactory.getBean(beanName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getBean(Class<?> type) throws BeanException {
        return this.beanFactory.getBean(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean beanExists(String name) {
        return this.beanFactory.beanExists(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Map<String, T> getBeansOfTypes(Class<T> type) throws BeanException {
        return this.beanFactory.getBeansOfTypes(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getBeanType(String name) throws BeanException {
        return this.beanFactory.getBeanType(name);
    }
}
