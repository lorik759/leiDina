package main.java.leiDina.tec.javafx;


import main.java.leiDina.tec.javafx.factory.controller.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactoryImpl;

/**
 * A base implementation of the {@link ApplicationContext} interface. This implementation provides the base implementation of all application context
 * resource.
 *
 * @author vitor.alves
 */
public class ApplicationContextImpl implements ApplicationContext {

    private final ControllerFactory controllerFactory;

    public ApplicationContextImpl() {
        this.controllerFactory = new ControllerFactoryImpl();
    }

    @Override
    public void init(Class<?> starterClass) {
        // do nothing for now
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ControllerFactory getControllerFactory() {
        return this.controllerFactory;
    }
}
