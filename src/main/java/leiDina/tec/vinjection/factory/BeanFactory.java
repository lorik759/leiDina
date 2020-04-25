package main.java.leiDina.tec.vinjection.factory;

import main.java.leiDina.tec.vinjection.model.BeanDefinition;

/**
 * @author vitor.alves
 */
public interface BeanFactory {

    void init();

    BeanDefinition getBeanByType(Class<?> type);

    BeanDefinition getBeanById(String id);
}
