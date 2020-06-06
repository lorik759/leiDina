package main.java.leiDina.tec.core.env;


import main.java.leiDina.tec.core.beans.model.BeanDefinitionHolder;

/**
 * The root interface of an application property. Its through this interface that the {@link main.java.leiDina.tec.core.beans.model.BeanDefinition}
 * are registered to the {@link BeanDefinitionHolder}.
 *
 * @author vitor.alves
 */
public interface ApplicationProperty {

    /**
     * Register the {@link main.java.leiDina.tec.core.beans.model.BeanDefinition} to the specified {@link BeanDefinitionHolder}.
     *
     * @param beanDefinitionHolder the {@link BeanDefinitionHolder} in which the beans of this {@link ApplicationProperty} contains.
     */
    void registerBeansTo(final BeanDefinitionHolder beanDefinitionHolder);
}
