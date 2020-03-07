package main.java.leiDina.tec.javafx.factory.controller.builder;

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
    protected void doSpecificStuff(Object instance) {
        // it does nothing
    }
}
