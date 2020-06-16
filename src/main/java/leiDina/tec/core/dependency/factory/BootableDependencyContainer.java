package main.java.leiDina.tec.core.dependency.factory;


import main.java.leiDina.tec.core.dependency.model.ObjectDefinitionHolder;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;

/**
 * A bootable dependency container is a {@link DependencyContainer} that is able to instantiate all object, based on {@link
 * ObjectDefinition} registered to this. This dependency container is only able to instantiate eagerly, singleton objects.
 * For a better understanding of the default working of an {@link BootableDependencyContainer} look at {@link DefaultDependencyContainer}.
 *
 * @author vitor.alves
 */
public interface BootableDependencyContainer extends DependencyContainer, ObjectDefinitionHolder {

    /**
     * Instantiates all singletons objects.
     */
    void instantiateSingletons();

}
