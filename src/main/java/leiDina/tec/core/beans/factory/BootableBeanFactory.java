package main.java.leiDina.tec.core.beans.factory;


import main.java.leiDina.tec.core.beans.model.BeanDefinitionHolder;

/**
 * A bootable bean factory is a {@link BeanFactory} that is able to Instantiate all singletons beans, based on {@link main.java.leiDina.tec.core.beans.model.BeanDefinition}
 * registered to this bean factory.
 *
 * @author vitor.alves
 */
public interface BootableBeanFactory extends BeanFactory, BeanDefinitionHolder {

    /**
     * Instantiates all singletons beans.
     */
    void instantiateSingletons();

}
