package main.java.leiDina.tec.javafx.service;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javafx.scene.Node;

/**
 * A service that associates a model with a component based on a specified annotation. The annotation specifies the type of the component that will be
 * associated to the model.
 *
 * @author vitor.alves
 */
public interface NodeAssociation<A extends Annotation, N extends Node> {

    /**
     * Associates the model with a component from the view.
     *
     * @param model the model of the controller.
     * @param declaredAnnotation The annotation that specifies the type of the component.
     * @param propertyDescriptor the {@link PropertyDescriptor} of the field from the model.
     * @param node the node that represents the parameter of the model.
     */
    void associate(final Object model, final A declaredAnnotation, final PropertyDescriptor propertyDescriptor, final N node);

    /**
     * @return the annotation class that represents the component in which the to associate the model with.
     */
    Class<A> type();

}
