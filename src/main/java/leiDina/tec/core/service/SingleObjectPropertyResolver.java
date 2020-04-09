package main.java.leiDina.tec.core.service;

import java.lang.reflect.InvocationTargetException;
import main.java.leiDina.tec.core.model.SingleObjectProperty;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class SingleObjectPropertyResolver implements PropertyResolver<SingleObjectProperty> {

    @Override
    public SingleObjectProperty resolve(String key, String values) {
        try {
            SingleObjectProperty singleObjectProperty = new SingleObjectProperty(key);
            Class<?> aClass = Class.forName(StringUtils.removeSpaces(values));
            Object object = ReflectionUtils.newInstance(aClass);
            singleObjectProperty.addProperty(object);
            return singleObjectProperty;
        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
