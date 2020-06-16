package main.java.leiDina.tec.core.dependency.service;

import main.java.leiDina.tec.core.dependency.factory.DependencyContainer;

/**
 * The default implantation of the {@link DependencyWire}. This objects knows how to find and wire dependencies of an object based of annotated fields.
 *
 * @author vitor.alves
 */
public class DependencyWireImp implements DependencyWire {

    private final DependencyContainer dependencyContainer;

    public DependencyWireImp(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    /**
     * Wires and resolves the dependencies of the object.
     *
     * @param object the object to have its dependencies resolved.
     * @param <T> the type of the object.
     */
    @Override
    public <T> void wire(T object) {
        this.getWireFor(object).wire();
    }

    private <T> ObjectPropertyWire<T> getWireFor(T object) {
        return new ObjectPropertyWire<>(dependencyContainer, object);
    }
}
