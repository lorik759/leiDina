package main.java.leiDina.tec.javafx.factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.exception.ControllerException;
import main.java.leiDina.tec.javafx.factory.builder.ControllerBuilder;
import main.java.leiDina.tec.javafx.factory.builder.GenericControllerBuilder;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * A base implementation of a {@link ControllerFactory}. Identifies controllers base on class and uses a {@link ControllerBuilder} to build and setup
 * controller objects. A builder is searched by class and super class until a builder is found. If no builder is found, {@link
 * GenericControllerBuilder} is used instead.
 *
 * @author vitor.alves
 */
public class ControllerFactoryImpl implements ControllerFactory {

    private final Map<Class<?>, ControllerBuilder<?>> buiders = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ConfigurableApplicationEnvironment environment) {
        SystemProperty<Class<?>> controllerFactorySystemProperty = environment.loadSystemPropertiesFor(ControllerFactory.class);
        this.setBuildersFromProperties(controllerFactorySystemProperty);
    }

    private void setBuildersFromProperties(SystemProperty<Class<?>> controllerFactorySystemProperty) {
        for (Class<?> clazz : controllerFactorySystemProperty.getProperties()) {
            if (ControllerBuilder.class.isAssignableFrom(clazz)) {
                try {
                    ControllerBuilder<?> controllerBuilder = (ControllerBuilder<?>) ReflectionUtils.newInstance(clazz);
                    buiders.put(controllerBuilder.getType(), controllerBuilder);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    throw new ControllerException(FXSystemMessages.CREATE_CONTROLLER_BUILDER_EXCEPTION.create(clazz), e);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getController(Class<?> controllerClass) {
        ControllerBuilder<?> builder = null;
        Class<?> clazz = controllerClass;
        while (clazz != null && builder == null) {
            builder = buiders.get(clazz);
            if (builder == null) {
                clazz = clazz.getSuperclass();
            }
        }
        if (builder == null) {
            builder = buiders.get(Object.class);
        }
        return builder.build(controllerClass);
    }
}
