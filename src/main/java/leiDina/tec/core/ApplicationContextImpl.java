package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.ControllerFactoryImpl;

/**
 * A base implementation of the {@link ApplicationContext} interface. This implementation provides the base implementation of all application context
 * resource.
 *
 * @author vitor.alves
 */
public class ApplicationContextImpl implements ApplicationContext {

    private final ApplicationDefinitions applicationDefinitions;

    private ControllerFactory controllerFactory;

    private ConfigurableApplicationEnvironment environment;

    public ApplicationContextImpl(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    @Override
    public void init() {
        applicationDefinitions.getLogger().info("Creating Controller Factory.");
        this.controllerFactory = new ControllerFactoryImpl();
        controllerFactory.init(environment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControllerFactory getControllerFactory() {
        return this.controllerFactory;
    }

    @Override
    public void setEnvironment(ConfigurableApplicationEnvironment environment) {
        this.environment = environment;
    }
}
