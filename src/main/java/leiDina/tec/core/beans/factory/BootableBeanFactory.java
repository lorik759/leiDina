package main.java.leiDina.tec.core.beans.factory;


import main.java.leiDina.tec.core.beans.model.BeanDefinitionHolder;

/**
 * A bootable bean factory is a {@link BeanFactory} that is able to instantiate all beans, based on {@link
 * main.java.leiDina.tec.core.beans.model.BeanDefinition} registered to this. This bean factory is only able to instantiate eagerly, singleton beans.
 * For a better understanding of the default working of an {@link BootableBeanFactory} look at {@link DefaultBeanFactory}.
 *
 * @author vitor.alves
 */
public interface BootableBeanFactory extends BeanFactory, BeanDefinitionHolder {

    /**
     * Instantiates all singletons beans.
     */
    void instantiateSingletons();

}
