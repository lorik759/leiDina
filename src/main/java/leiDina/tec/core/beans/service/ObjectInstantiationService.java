package main.java.leiDina.tec.core.beans.service;


import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * @author vitor.alves
 */
public class ObjectInstantiationService {

    private final BeanDefinition beanDefinition;

    public ObjectInstantiationService(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }

    public Object createInstance() throws ReflectiveOperationException {
        return ReflectionUtils.newInstance(beanDefinition.getBeanClass());
    }
}
