package main.java.leiDina.tec.core;

import java.util.Map;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.dependency.factory.BootableDependencyContainer;
import main.java.leiDina.tec.core.dependency.factory.DefaultDependencyContainer;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;
import main.java.leiDina.tec.core.env.ApplicationProperty;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;


/**
 * This is the default implementation of the interface {@link ApplicationContext}. This context initializes all default properties of the
 * VApplication. This uses the {@link DefaultDependencyContainer} for the instantiation and management af all object instance. This also initializes and
 * configurers the object factory.
 *
 * @author vitor.alves
 */
public class DefaultApplicationContext implements ApplicationContext {

    private final BootableDependencyContainer dependencyContainer;

    private ConfigurableApplicationEnvironment configurableApplicationEnvironment;

    public DefaultApplicationContext() {
        this.dependencyContainer = new DefaultDependencyContainer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfigurableApplicationEnvironment(ConfigurableApplicationEnvironment configurableApplicationEnvironment) {
        this.configurableApplicationEnvironment = configurableApplicationEnvironment;
    }

    /**
     * This method loads the {@link ObjectDefinition} to the {@link DefaultDependencyContainer} and starts the object
     * factory.
     */
    @Override
    public void initialize() {
        this.loadObjectDefinitions();
        this.startDependencyContainer();
    }

    private void startDependencyContainer() {
        this.dependencyContainer.instantiateSingletons();
    }

    private void loadObjectDefinitions() {
        for (ApplicationProperty loadApplicationProperty : configurableApplicationEnvironment.loadApplicationProperties()) {
            loadApplicationProperty.registerObjectsTo(this.dependencyContainer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(String objectName) throws DependencyException {
        return this.dependencyContainer.getObject(objectName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getObject(Class<?> type) throws DependencyException {
        return this.dependencyContainer.getObject(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean objectExists(String name) {
        return this.dependencyContainer.objectExists(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> Map<String, T> getObjectsOfTypes(Class<T> type) throws DependencyException {
        return this.dependencyContainer.getObjectsOfTypes(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getObjectType(String name) throws DependencyException {
        return this.dependencyContainer.getObjectType(name);
    }
}
