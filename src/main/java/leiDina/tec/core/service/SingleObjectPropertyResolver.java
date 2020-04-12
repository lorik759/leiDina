package main.java.leiDina.tec.core.service;

import main.java.leiDina.tec.core.exception.ResolverException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.model.SingleObjectProperty;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * A {@link PropertyResolver} that knows to resolve a property for a single object. If the property contains mor3 than one object as a value, an
 * exception is thrown.
 *
 * @author vitor.alves
 */
public class SingleObjectPropertyResolver implements PropertyResolver<SingleObjectProperty> {

    /**
     * Resolves the value of a property of a single object.
     *
     * @param key the key of the property.
     * @param values the values of the property.
     * @return a {@link SingleObjectProperty}.
     */
    @Override
    public SingleObjectProperty resolve(String key, String values) {
        try {
            String[] strings = StringUtils.replaceNewLineAndSplitComma(values);
            if (strings.length > 1) {
                throw new ResolverException(BaseSystemMessages.SINGLE_OBJECT_RESOLVER_ERROR.create(key));
            }
            Class<?> aClass = Class.forName(StringUtils.removeSpaces(strings[0]));
            SingleObjectProperty singleObjectProperty = new SingleObjectProperty(key, aClass);
            Object object = ReflectionUtils.newInstance(aClass);
            singleObjectProperty.addProperty(object);
            return singleObjectProperty;
        } catch (ReflectiveOperationException e) {
            throw new ResolverException(BaseSystemMessages.SINGLE_OBJECT_RESOLVER_ERROR.create(key), e);
        }
    }
}
