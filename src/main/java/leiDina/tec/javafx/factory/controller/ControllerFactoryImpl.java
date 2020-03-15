package main.java.leiDina.tec.javafx.factory.controller;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.core.model.ClassSystemProperties;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.factory.controller.builder.ControllerBuilder;
import main.java.leiDina.tec.javafx.factory.controller.builder.GenericControllerBuilder;

/**
 * A base implementation of a {@link ControllerFactory}. Identifies controllers base on class en uses a {@link ControllerBuilder} to build e setup
 * controller objects. A builder is searched by class and super class until a builder is found. If no builder is found, {@link
 * GenericControllerBuilder} is used instead.
 *
 * @author vitor.alves
 */
public class ControllerFactoryImpl implements ControllerFactory {

    private final Map<Class<?>, ControllerBuilder<?>> buiders = new HashMap<>();

    public ControllerFactoryImpl(ClassSystemProperties classSystemProperties) {
        getBuildersFromProperties(classSystemProperties);
    }

    private void getBuildersFromProperties(ClassSystemProperties classSystemProperties) {
        for (ClassSystemProperties systemProperty : classSystemProperties.getClassSystemProperties()) {
            Class<?> type = systemProperty.getType();
            if (type.isAssignableFrom(ControllerBuilder.class)) {
                buiders.put(type, (ControllerBuilder<?>) ReflectionUtils.newInstance(type));
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
            builder = getGenericControllerBuilder();
        }
        return builder.build(controllerClass);
    }

    /**
     * If no specific {@link ControllerBuilder} for the requested class is not found, the a generic controller builder is used instead.
     *
     * @return A {@link GenericControllerBuilder}.
     */
    private ControllerBuilder<?> getGenericControllerBuilder() {
        return new GenericControllerBuilder();
    }
}
