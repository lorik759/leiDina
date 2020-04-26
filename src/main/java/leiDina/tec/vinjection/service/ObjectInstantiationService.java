package main.java.leiDina.tec.vinjection.service;

import main.java.leiDina.tec.vinjection.model.BeanDefinition;

/**
 * @author vitor.alves
 */
public interface ObjectInstantiationService {

    Object createFor(BeanDefinition beanDefinition);

}
