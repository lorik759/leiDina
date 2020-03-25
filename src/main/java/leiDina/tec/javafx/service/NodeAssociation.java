package main.java.leiDina.tec.javafx.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author vitor.alves
 */
public interface NodeAssociation<A extends Annotation> {

    void associate(Object model, A declaredAnnotation, Method method, Map<String, Object> componants);

    Class<A> type();

}
