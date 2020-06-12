package main.java.leiDina.tec.core.beans.service;


import main.java.leiDina.tec.core.beans.model.BeanDefinition;
import main.java.leiDina.tec.core.utils.ReflectionUtils;

/**
 * Instantiates a new instance of the {@link BeanDefinition}.
 *
 * @author vitor.alves
 */
//TODO: Add dependency of the instantiation throw specified constructor.
public class ObjectInstantiationService {

    private final BeanDefinition beanDefinition;

    public ObjectInstantiationService(BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
    }

    /**
     * Creates new instance oh the {@link BeanDefinition#getBeanClass()}.
     *
     * @return a instance of {@link BeanDefinition#getBeanClass()}.
     * @throws ReflectiveOperationException a {@link ReflectiveOperationException}.
     */
    public Object newInstance() throws ReflectiveOperationException {
        return ReflectionUtils.newInstance(beanDefinition.getBeanClass());
    }
}
