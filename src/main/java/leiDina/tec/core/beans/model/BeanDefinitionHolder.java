package main.java.leiDina.tec.core.beans.model;


import main.java.leiDina.tec.core.beans.exception.BeanException;

/**
 * @author vitor.alves
 */
public interface BeanDefinitionHolder {

    void addBeanDefinition(BeanDefinition beanDefinition) throws BeanException;

    void removeBeanDefinition(BeanDefinition beanDefinition) throws BeanException;

    boolean existsBeanDefinition(BeanDefinition beanDefinition);

    boolean isBeanDefinitionNameInUse(String beanName);

    boolean isAllowOverwriteBeanDefinition();

    void allowOverwriteBeanDefinition(boolean allowOverwrite);

    BeanDefinition getBeanDefinitionForName(String name) throws BeanException;

    BeanDefinition getBeanDefinitionForType(Class<?> beanClass) throws BeanException;

    String getBeanName(Class<?> type);

}
