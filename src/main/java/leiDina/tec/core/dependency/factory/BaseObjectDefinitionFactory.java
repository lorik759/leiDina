package main.java.leiDina.tec.core.dependency.factory;

import main.java.leiDina.tec.core.dependency.exception.DependencyDefinitionException;
import main.java.leiDina.tec.core.dependency.exception.DependencyException;
import main.java.leiDina.tec.core.dependency.model.BaseObjectDefinition;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.core.xml.model.ObjectType;

/**
 * @author vitor.alves
 */
public class BaseObjectDefinitionFactory {

    private final PropertyValueFactory propertyValueFactory = new PropertyValueFactory();

    public BaseObjectDefinition createFor(ObjectType info, String location) throws DependencyException {
        BaseObjectDefinition objectDefinition = this.createFor(info);
        objectDefinition.setLocationName(location);
        return objectDefinition;
    }

    public BaseObjectDefinition createFor(ObjectType info) throws DependencyException {
        final BaseObjectDefinition baseObjectDefinition = new BaseObjectDefinition(info.getId(), getObjectClass(info.getClazz()));
        if (StringUtils.isNotEmpty(info.getParent())) {
            baseObjectDefinition.setParent(info.getParent());
        }
        info.getProperty().forEach(propertyType -> baseObjectDefinition.addPropertyValue(propertyValueFactory.createFor(propertyType)));
        return baseObjectDefinition;
    }

    private Class<?> getObjectClass(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            throw new DependencyDefinitionException(e);
        }
    }
}
