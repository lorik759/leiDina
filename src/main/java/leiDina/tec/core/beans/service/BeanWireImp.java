package main.java.leiDina.tec.core.beans.service;

import main.java.leiDina.tec.core.beans.factory.BeanFactory;

/**
 * The default implantation of the {@link BeanWire}. This objects knows how to find and wire dependencies of an object based of annotated fields.
 *
 * @author vitor.alves
 */
public class BeanWireImp implements BeanWire {

    private final BeanFactory beanFactory;

    public BeanWireImp(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     *
     *
     * @param bean
     * @param <T>
     */
    @Override
    public <T> void wire(T bean) {
        this.getWireFor(bean).wire();
    }

    private <T> BeanPropertyWire<T> getWireFor(T bean) {
        return new BeanPropertyWire<>(beanFactory, bean);
    }
}
