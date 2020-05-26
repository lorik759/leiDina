package main.java.leiDina.tec.core;

import java.util.Map;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.factory.DefaultBeanFactory;
import main.java.leiDina.tec.core.beans.factory.BootableBeanFactory;
import main.java.leiDina.tec.core.env.ApplicationProperty;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;


/**
 * @author vitor.alves
 */
public class DefaultApplicationContext implements ApplicationContext {

    private final BootableBeanFactory beanFactory;

    private ConfigurableApplicationEnvironment configurableApplicationEnvironment;

    public DefaultApplicationContext() {
        this.beanFactory = new DefaultBeanFactory();
    }

    @Override
    public void setConfigurableApplicationEnvironment(ConfigurableApplicationEnvironment configurableApplicationEnvironment) {
        this.configurableApplicationEnvironment = configurableApplicationEnvironment;
    }

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

    @Override
    public <T> T getBean(String beanName) throws BeanException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public <T> T getBean(Class<?> type) throws BeanException {
        return this.beanFactory.getBean(type);
    }

    @Override
    public boolean beanExists(String name) {
        return this.beanFactory.beanExists(name);
    }

    @Override
    public <T> Map<String, T> getBeansOfTypes(Class<T> type) throws BeanException {
        return this.beanFactory.getBeansOfTypes(type);
    }

    @Override
    public Class<?> getBeanType(String name) throws BeanException {
        return this.beanFactory.getBeanType(name);
    }
}
