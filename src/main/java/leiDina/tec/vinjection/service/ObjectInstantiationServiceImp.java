package main.java.leiDina.tec.vinjection.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.vinjection.exception.VijectionException;
import main.java.leiDina.tec.vinjection.messages.VInjectionSystemMessages;
import main.java.leiDina.tec.vinjection.model.BeanDefinition;
import main.java.leiDina.tec.vinjection.model.PropertyDefinition;
import main.java.leiDina.tec.vinjection.model.PropertyType;

/**
 * @author vitor.alves
 */
public class ObjectInstantiationServiceImp implements ObjectInstantiationService {

    private Map<String, BeanDefinition> beanDefinitionById;

    private List<BeanDefinition> beanDefinitionsAlreadyResolving = new ArrayList<>();

    public ObjectInstantiationServiceImp(Map<String, BeanDefinition> beanDefinitionById) {
        this.beanDefinitionById = beanDefinitionById;
    }

    @Override
    public Object createFor(BeanDefinition beanDefinition) {
        beanDefinitionsAlreadyResolving.add(beanDefinition);
        Class<?> type = beanDefinition.getType();
        Object instance = this.createInstance(type);
        Collection<PropertyDefinition> propertyDefinitions = beanDefinition.getPropertyDefinitions();
        for (PropertyDefinition propertyDefinition : propertyDefinitions) {
            PropertyType propertyType = propertyDefinition.getType();
            Object propertyValue = null;
            if (propertyType.isBean()) {
                BeanDefinition beanRefrence = beanDefinitionById.get(propertyDefinition.getPropertyValue().getValue());
                if (beanDefinitionsAlreadyResolving.contains(beanDefinition)) {
                    throw new VijectionException(VInjectionSystemMessages.CIRCULAR_DEPENDENCY.create(type, beanRefrence.getType()));
                }
                propertyValue = beanRefrence.getInstance();
                if (propertyValue == null) {
                    propertyValue = this.createFor(beanRefrence);
                }
            } else if (propertyType.isMap()) {
                propertyValue = propertyDefinition.getPropertyValue().getValue();
            }
            this.setValueToProperty(type, instance, propertyDefinition, propertyValue);
        }
        beanDefinitionsAlreadyResolving.remove(beanDefinition);
        return instance;
    }

    private void setValueToProperty(Class<?> type, Object instance, PropertyDefinition propertyDefinition, Object propertyValue) {
        Method writeMethod = propertyDefinition.getWriteMethod();
        if (writeMethod == null) {
            throw new VijectionException(VInjectionSystemMessages.NO_SETTER_METHOD_FOR_PROPERTY.create(propertyDefinition.getPropertyName(), type));
        }
        try {
            ReflectionUtils.invoke(writeMethod, instance, propertyValue);
        } catch (ReflectiveOperationException e) {
            throw new VijectionException(
                VInjectionSystemMessages.FAILED_TO_WRITE_VALUE_TO_PROPERTY.create(propertyValue, propertyDefinition.getPropertyName(), type));
        }
    }

    private Object createInstance(Class<?> type) {
        try {
            return ReflectionUtils.newInstance(type);
        } catch (ReflectiveOperationException e) {
            throw new VijectionException(VInjectionSystemMessages.FAILED_TO_CREATE_BEAN_INSTANCE.create(type));
        }
    }
}
