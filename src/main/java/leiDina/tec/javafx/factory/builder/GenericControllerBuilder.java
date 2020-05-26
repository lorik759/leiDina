package main.java.leiDina.tec.javafx.factory.builder;

import main.java.leiDina.tec.core.beans.service.BeanWire;

/**
 * A generic builder for a controller. Instantiates a simple object with no new functionality.
 *
 * @author vitor.alves
 */
public class GenericControllerBuilder extends AbstractControllerBuilder<Object> {

    private BeanWire beanWire;

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
        beanWire.wire(instance);
    }

    public void setBeanWire(BeanWire beanWire) {
        this.beanWire = beanWire;
    }
}
