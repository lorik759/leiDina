package main.java.leiDina.tec.javafx.factory.builder;


import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import sun.reflect.misc.ReflectUtil;

/**
 * A base implementation of {@link ControllerBuilder}, which instantiates and passes the controller to be implemented by the classes that extend this.
 *
 * @author vitor.alves
 */
public abstract class AbstractControllerBuilder<T> implements ControllerBuilder<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T build(Class<?> clazz) throws BuilderException {
        T instance = createInstance(clazz);
        doSpecificStuff(instance);
        return instance;
    }

    @Override
    public abstract Class<T> getType();

    /**
     * Do specific stuff to the instance.
     *
     * @param instance A instance of type T.
     */
    protected abstract void doSpecificStuff(T instance);

    /**
     * Creates a intense of the specified class.
     *
     * @param clazz a {@link Class}
     * @return the intense of the specified class.
     */
    protected T createInstance(Class<?> clazz) {
        try {
            return (T) ReflectUtil.newInstance(clazz);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BuilderException(FXSystemMessages.INSTANTIATING_CONTROLLER_EXCEPTION.create(clazz.getName()), e);
        }
    }
}
