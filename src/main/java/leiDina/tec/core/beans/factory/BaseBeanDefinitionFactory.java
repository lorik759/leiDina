package main.java.leiDina.tec.core.beans.factory;

import main.java.leiDina.tec.core.beans.exception.BeanDefinitionException;
import main.java.leiDina.tec.core.beans.exception.BeanException;
import main.java.leiDina.tec.core.beans.model.BaseBeanDefinition;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.core.xml.model.BeanType;

/**
 * @author vitor.alves
 */
public class BaseBeanDefinitionFactory implements BeanDefinitionFactory<BaseBeanDefinition, BeanType> {

    private final PropertyValueFactory propertyValueFactory = new PropertyValueFactory();

    @Override
    public BaseBeanDefinition createFor(BeanType info, String location) throws BeanException {
        BaseBeanDefinition beanDefinition = this.createFor(info);
        beanDefinition.setLocationName(location);
        return beanDefinition;
    }

    @Override
    public BaseBeanDefinition createFor(BeanType info) throws BeanException {
        final BaseBeanDefinition baseBeanDefinition = new BaseBeanDefinition(info.getId(), getBeanClass(info.getClazz()));
        if (StringUtils.isNotEmpty(info.getParent())) {
            baseBeanDefinition.setParent(info.getParent());
        }
        info.getProperty().forEach(propertyType -> baseBeanDefinition.addPropertyValue(propertyValueFactory.createFor(propertyType)));
        return baseBeanDefinition;
    }

    private Class<?> getBeanClass(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new BeanDefinitionException(e);
        }
    }
}
