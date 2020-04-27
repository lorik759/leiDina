package main.java.leiDina.tec.javafx.factory.builder;

import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.vinjection.BeanWireThreadContext;
import main.java.leiDina.tec.vinjection.service.BeanWire;

/**
 * A generic builder for a controller. Instantiates a simple object with no new functionality.
 *
 * @author vitor.alves
 */
public class GenericControllerBuilder extends AbstractControllerBuilder<Object> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Object> getType() {
        return Object.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doSpecificStuff(Object instance) {
        BeanWire beanWire = BeanWireThreadContext.getBeanWire();
        try {
            beanWire.wire(instance);
        } catch (Exception e) {
            throw new BuilderException(FXSystemMessages.INSTANTIATING_CONTROLLER_EXCEPTION.create(instance.getClass()), e);
        }
    }
}
