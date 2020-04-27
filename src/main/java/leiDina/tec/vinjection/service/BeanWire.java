package main.java.leiDina.tec.vinjection.service;

import java.lang.reflect.Field;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.service.Wire;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.vinjection.annotations.Injected;
import main.java.leiDina.tec.vinjection.factory.BeanFactory;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;

/**
 * @author vitor.alves
 */
public class BeanWire implements Wire<Object> {

    private final BeanFactory beanFactory;

    public BeanWire(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void wire(Object object) throws Exception {
        Class<?> aClass = object.getClass();
        while (aClass != null) {
            for (Field declaredField : aClass.getDeclaredFields()) {
                Resource resource = declaredField.getAnnotation(Resource.class);
                if (resource != null) {
                    String name = resource.name();
                    BeanDefinition beanDefinition = this.beanFactory.getBeanById(name);
                    setValueToField(object, declaredField, beanDefinition);
                } else if (declaredField.getAnnotation(Injected.class) != null) {
                    Class<?> type = declaredField.getType();
                    BeanDefinition beanDefinition = this.beanFactory.getBeanByType(type);
                    this.setValueToField(object, declaredField, beanDefinition);
                }
            }
            aClass = aClass.getSuperclass();
        }
    }

    private void setValueToField(Object object, Field declaredField, BeanDefinition beanDefinition) throws IllegalAccessException {
        ReflectionUtils.set(declaredField, object, beanDefinition.getInstance());
    }
}
