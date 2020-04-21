package main.java.leiDina.tec.javafx.factory;

import java.util.HashMap;
import java.util.Map;
import main.java.leiDina.tec.javafx.factory.builder.ControllerBuilder;
import main.java.leiDina.tec.javafx.factory.builder.GenericControllerBuilder;

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
    public Object getController(Class<?> controllerClass) {
        ControllerBuilder<?> builder = null;
        Class<?> clazz = controllerClass;
        while (clazz != null && builder == null) {
            builder = buiders.get(clazz);
            if (builder == null) {
                clazz = clazz.getSuperclass();
            }
        }
        return builder.build(controllerClass);
    }

    /**
     * {@inheritDoc}
     */
    public <T> void addControllerBuilder(ControllerBuilder<T> builder) {
        buiders.put(builder.getType(), builder);
    }
}
