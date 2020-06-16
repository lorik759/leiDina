package main.java.leiDina.tec.javafx.factory;

import main.java.leiDina.tec.core.dependency.service.DependencyWire;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * This implementation of the {@link ControllerFactory} is able to instantiate and wire controllers with is dependencies with a {@link DependencyWire}
 *
 * @author vitor.alves
 */
public class InjectableControllerFactory implements ControllerFactory {

    private DependencyWire dependencyWire;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getController(Class<?> controllerClass) {
        Object instance = createInstance(controllerClass);
        this.wire(instance);
        return instance;
    }

    /**
     * Wires the controller instance with its dependencies.
     * 
     * @param instance the controller instance to be wired.
     */
    private void wire(Object instance) {
        this.dependencyWire.wire(instance);
    }

    /**
     * Creates an instance of the specified class.
     *
     * @param clazz a {@link Class}
     * @return the intense of the specified class.
     */
    protected Object createInstance(Class<?> clazz) {
        try {
            return ReflectionUtils.newInstance(clazz);
        } catch (ReflectiveOperationException e) {
            throw new BuilderException(FXSystemMessages.INSTANTIATING_CONTROLLER_EXCEPTION.create(clazz.getName()), e);
        }
    }

    public void setDependencyWire(DependencyWire dependencyWire) {
        this.dependencyWire = dependencyWire;
    }
}
