package main.java.leiDina.tec.core.env;


import main.java.leiDina.tec.core.beans.model.BeanDefinitionHolder;

/**
 * @author vitor.alves
 */
public interface ApplicationProperty {

    void registerBeansTo(final BeanDefinitionHolder beanDefinitionHolder);
}
