package main.java.leiDina.tec.core.env;


import main.java.leiDina.tec.core.dependency.model.ObjectDefinitionHolder;
import main.java.leiDina.tec.core.dependency.model.ObjectDefinition;

/**
 * The root interface of an application property. Its through this interface that the {@link ObjectDefinition}
 * are registered to the {@link ObjectDefinitionHolder}.
 *
 * @author vitor.alves
 */
public interface ApplicationProperty {

    /**
     * Register the {@link ObjectDefinition} to the specified {@link ObjectDefinitionHolder}.
     *
     * @param objectDefinitionHolder the {@link ObjectDefinitionHolder} in which the objects of this {@link ApplicationProperty} contains.
     */
    void registerObjectsTo(final ObjectDefinitionHolder objectDefinitionHolder);
}
