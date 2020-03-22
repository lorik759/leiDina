package main.java.leiDina.tec.core;


import javafx.util.BuilderFactory;
import main.java.leiDina.tec.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemProperties;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactoryImpl;

/**
 * A base implementation of the {@link ApplicationContext} interface. This implementation provides the base implementation of all application context
 * resource.
 *
 * @author vitor.alves
 */
public class FullApplicationContext implements ApplicationContext {

    private final ApplicationDefinitions applicationDefinitions;

    private ControllerFactory controllerFactory;

    private BuilderFactory builderFactory;

    private BeanFactory beanFactory;

    private ConfigurableApplicationEnvironment environment;

    public FullApplicationContext(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    @Override
    public void init() {
        applicationDefinitions.getLogger().info("Creating Controller Factory.");
        SystemProperties<Class<?>> classSystemPropertiesForControllerFactory = environment.loadSystemPropertiesFor(ControllerFactory.class);
        this.controllerFactory = new ControllerFactoryImpl(classSystemPropertiesForControllerFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControllerFactory getControllerFactory() {
        return this.controllerFactory;
    }

    @Override
    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public void setEnvironment(ConfigurableApplicationEnvironment environment) {
        this.environment = environment;
    }
}
