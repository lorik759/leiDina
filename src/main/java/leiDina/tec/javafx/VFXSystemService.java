package main.java.leiDina.tec.javafx;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SingleObjectProperty;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.BaseSystemService;
import main.java.leiDina.tec.core.service.ClassPropertyResolver;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.exception.ControllerException;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.ControllerFactoryImpl;
import main.java.leiDina.tec.javafx.factory.builder.ControllerBuilder;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;
import main.java.leiDina.tec.javafx.service.NodeAssociation;
import main.java.leiDina.tec.javafx.service.VFXServiceKey;

/**
 * The system service for the VFXModule.
 *
 * @author vitor.alves
 */
public class VFXSystemService extends BaseSystemService {

    private static final String SERVICE_NAME = "VFXSystemService";

    private static final String FX_SYSTEM_ENVIRONMENT = "fx-service-properties.xml";

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ConfigurableApplicationEnvironment environment) {
        this.createModelSceneWireProperty(environment);
        this.createControllerFactoryProperty(environment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    /**
     * Creates the {@link SystemProperty} of the {@link ControllerFactory}.
     *
     * @param environment the {@link ConfigurableApplicationEnvironment} of the VFXModule.
     */
    private void createControllerFactoryProperty(ConfigurableApplicationEnvironment environment) {
        SystemProperty<Class<?>> controllerFactorySystemProperties = environment.loadSystemPropertiesFor(ControllerFactory.class, new ClassPropertyResolver());
        ControllerFactory controllerFactory = this.createControllerFactory(controllerFactorySystemProperties);
        SingleObjectProperty controllerFactoryProperty = new SingleObjectProperty(ControllerFactory.class.getSimpleName(), ControllerFactory.class);
        controllerFactoryProperty.addProperty(controllerFactory);
        this.add(controllerFactoryProperty);
    }

    /**
     * Creates and builds the {@link ControllerFactory} with the properties pass by the {@link ConfigurableApplicationEnvironment}
     *
     * @param controllerFactorySystemProperty A set of properties that will be used to set up the {@link ControllerFactory}.
     */
    private ControllerFactory createControllerFactory(SystemProperty<Class<?>> controllerFactorySystemProperty) {
        ControllerFactory controllerFactory = new ControllerFactoryImpl();
        for (Class<?> clazz : controllerFactorySystemProperty.getProperties()) {
            if (ControllerBuilder.class.isAssignableFrom(clazz)) {
                try {
                    ControllerBuilder<?> controllerBuilder = (ControllerBuilder<?>) ReflectionUtils.newInstance(clazz);
                    controllerFactory.addControllerBuilder(controllerBuilder);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    throw new ControllerException(FXSystemMessages.CREATE_CONTROLLER_BUILDER_EXCEPTION.create(clazz), e);
                }
            }
        }
        return controllerFactory;
    }

    /**
     * Creates the {@link SystemProperty} of the {@link ModelSceneWire}.
     *
     * @param environment the {@link ConfigurableApplicationEnvironment} of the VFXModule.
     */
    private void createModelSceneWireProperty(ConfigurableApplicationEnvironment environment) {
        SystemProperty<Class<?>> modelSceneWireProperties = environment.loadSystemPropertiesFor(ModelSceneWire.class, new ClassPropertyResolver());
        ModelSceneWire modelSceneWire = this.createModelSceneWire(modelSceneWireProperties);
        SingleObjectProperty modelSceneWireProperty = new SingleObjectProperty();
        modelSceneWireProperty.addProperty(modelSceneWire);
        this.add(modelSceneWireProperty);
    }

    /**
     * Creates and builds the {@link ModelSceneWire} with the properties pass by the {@link ConfigurableApplicationEnvironment}
     *
     * @param modelSceneSystemPropertys A set of properties that will be used to set up the {@link ModelSceneWire}.
     */
    private ModelSceneWire createModelSceneWire(SystemProperty<Class<?>> modelSceneSystemPropertys) {
        ModelSceneWire modelSceneWire = new ModelSceneWire();
        for (Class<?> clazz : modelSceneSystemPropertys.getProperties()) {
            if (NodeAssociation.class.isAssignableFrom(clazz)) {
                try {
                    NodeAssociation<?, ?> nodeAssociation = (NodeAssociation<?, ?>) ReflectionUtils.newInstance(clazz);
                    modelSceneWire.addModelComponentAssociation(nodeAssociation.type(), nodeAssociation);
                } catch (ReflectiveOperationException e) {
                    throw new ControllerException(FXSystemMessages.CREATE_NODE_ASSOCIATION_EXCEPTION.create(clazz), e);
                }
            }
        }
        return modelSceneWire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemServiceKey getKey() {
        return new VFXServiceKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends SystemProperty<?>> void addProperty(T property) {
        Class<?> type = property.getType();
        SystemProperty<?> propertyByType = this.getPropertyByType(type);
        if (type.isAssignableFrom(ControllerFactory.class)) {
            ControllerFactory controllerFactory = (ControllerFactory) propertyByType.getProperty();
            List<ControllerBuilder<?>> properties = (List<ControllerBuilder<?>>) property.getProperties();
            for (ControllerBuilder<?> propertyProperty : properties) {
                controllerFactory.addControllerBuilder(propertyProperty);
            }
        } else if (type.isAssignableFrom(ModelSceneWire.class)) {
            ModelSceneWire modelSceneWire = (ModelSceneWire) propertyByType.getProperty();
            List<NodeAssociation<?, ?>> properties = (List<NodeAssociation<?, ?>>) property.getProperties();
            for (NodeAssociation<?, ?> nodeAssociation : properties) {
                modelSceneWire.addModelComponentAssociation(nodeAssociation.type(), nodeAssociation);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEnvironmentName() {
        return FX_SYSTEM_ENVIRONMENT;
    }
}
