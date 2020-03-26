package main.java.leiDina.tec.core.io;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import main.java.leiDina.tec.core.annotations.Column;
import main.java.leiDina.tec.core.exception.PersistenceException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
public class EntityToTextDigester {

    public String digest(Persistable entity) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        StringBuffer entityLine = new StringBuffer();
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        entityLine = entityLine.append("id:").append(entity.getId());
        for (Field declaredField : declaredFields) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(declaredField.getName(), entity.getClass());
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null) {
                Column column = writeMethod.getDeclaredAnnotation(Column.class);
                if (column != null) {
                    Method readMethod = propertyDescriptor.getReadMethod();
                    if (readMethod == null) {
                        throw new PersistenceException(BaseSystemMessages.NO_GETTER_METHOD.create(entity.getClass(), propertyDescriptor.getName()));
                    }
                    Object value = readMethod.invoke(entity);
                    entityLine = entityLine.append(";").append(column.name()).append(":").append(value);
                }
            }
        }
        return entityLine.toString();
    }
}
