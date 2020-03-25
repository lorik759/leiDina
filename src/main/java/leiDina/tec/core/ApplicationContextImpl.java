package main.java.leiDina.tec.core;


import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.exception.ControllerException;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.ControllerFactoryImpl;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;
import main.java.leiDina.tec.javafx.service.NodeAssociation;

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

    private ModelSceneWire modelSceneWire;

    public ApplicationContextImpl(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        applicationDefinitions.getLogger().info("Creating Controller Factory.");
        this.controllerFactory = new ControllerFactoryImpl();
        controllerFactory.init(environment);
        applicationDefinitions.getLogger().info("Creating Model Wire.");
        SystemProperty<Class<?>> modelSceneSystemPropertys = environment.loadSystemPropertiesFor(ModelSceneWire.class);
        this.createModelSceneWire(modelSceneSystemPropertys);
    }

    /**
     * Creates and builds the {@link ModelSceneWire} with the properties pass by the {@link ConfigurableApplicationEnvironment}
     *
     * @param modelSceneSystemPropertys A set of properties that will be used to set up the {@link ModelSceneWire}.
     */
    private void createModelSceneWire(SystemProperty<Class<?>> modelSceneSystemPropertys) {
        this.modelSceneWire = new ModelSceneWire();
        for (Class<?> clazz : modelSceneSystemPropertys.getProperties()) {
            if (NodeAssociation.class.isAssignableFrom(clazz)) {
                try {
                    NodeAssociation nodeAssociation = (NodeAssociation) ReflectionUtils.newInstance(clazz);
                    modelSceneWire.addModelComponantAssociation(nodeAssociation.type(), nodeAssociation);
                } catch (ReflectiveOperationException e) {
                    throw new ControllerException(FXSystemMessages.CREATE_NODE_ASSOCIATION_EXCEPTION.create(clazz), e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControllerFactory getControllerFactory() {
        return this.controllerFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigurableApplicationEnvironment getConfigurableApplicationEnvironment() {
        return this.environment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnvironment(ConfigurableApplicationEnvironment environment) {
        this.environment = environment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModelSceneWire getModelWire() {
        return this.modelSceneWire;
    }
}
