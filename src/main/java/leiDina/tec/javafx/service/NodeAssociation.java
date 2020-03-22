package main.java.leiDina.tec.javafx.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author vitor.alves
 */
public interface NodeAssociation {

    void associate(Object model, Method method, Map<String, Object> componants);

    Class<? extends Annotation> type();

}
