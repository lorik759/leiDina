package main.java.leiDina.tec.core;


import javafx.util.BuilderFactory;
import main.java.leiDina.tec.beans.factory.BeanFactory;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.ClassSystemProperties;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactoryImpl;
import main.java.leiDina.tec.javafx.scene.NodeBuilderFactory;

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
        ClassSystemProperties classSystemPropertiesForControllerFactory = environment.loadSystemPropertiesFor(ControllerFactory.class);
        this.controllerFactory = new ControllerFactoryImpl(classSystemPropertiesForControllerFactory);
        applicationDefinitions.getLogger().info("Creating Builder Factory.");
        ClassSystemProperties classSystemPropertiesForBuilderFactory = environment.loadSystemPropertiesFor(BuilderFactory.class);
        this.builderFactory = new NodeBuilderFactory(classSystemPropertiesForBuilderFactory);
        applicationDefinitions.getLogger().info("Creating Bean Factory.");
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
