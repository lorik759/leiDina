package main.java.leiDina.tec.javafx;

import java.lang.reflect.InvocationTargetException;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SingleObjectProperty;
import main.java.leiDina.tec.core.model.SystemKey;
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
import main.java.leiDina.tec.javafx.service.VFXKey;

/**
 * @author vitor.alves
 */
public class VFXSystemService extends BaseSystemService {

    private static final String FX_SYSTEM_ENVIRONMENT = "fx-system-properties.xml";

    @Override
    public void init(ConfigurableApplicationEnvironment environment) {
        this.createModelSceneWireProperty(environment);
        this.createControllerFactoryProperty(environment);
    }

    private void createControllerFactoryProperty(ConfigurableApplicationEnvironment environment) {
        SystemProperty<Class<?>> controllerFactorySystemProperties = environment.loadSystemPropertiesFor(ControllerFactory.class, new ClassPropertyResolver());
        ControllerFactory controllerFactory = this.createControllerFactory(controllerFactorySystemProperties);
        SingleObjectProperty controllerFactoryProperty = new SingleObjectProperty(ControllerFactory.class.getSimpleName(), ControllerFactory.class);
        controllerFactoryProperty.addProperty(controllerFactory);
        this.add(controllerFactoryProperty);
    }

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

    @Override
    public SystemKey getKey() {
        return new VFXKey();
    }

    @Override
    public String getEnvironmentName() {
        return FX_SYSTEM_ENVIRONMENT;
    }
}
