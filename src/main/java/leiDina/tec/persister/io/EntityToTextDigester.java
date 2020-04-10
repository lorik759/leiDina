package main.java.leiDina.tec.persister.io;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import main.java.leiDina.tec.persister.annotations.Column;
import main.java.leiDina.tec.persister.exception.PersistenceException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.persister.Persistable;

/**
 * @author vitor.alves
 */
public class EntityToTextDigester {

    public String digest(Persistable entity) throws InvocationTargetException, IllegalAccessException {
        StringBuffer entityLine = new StringBuffer();
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        entityLine.append("id:").append(entity.getId());
        for (Field declaredField : declaredFields) {
            PropertyDescriptor propertyDescriptor;
            try {
                propertyDescriptor = new PropertyDescriptor(declaredField.getName(), entity.getClass());
            } catch (Exception e) {
                throw new PersistenceException(e);
            }
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null) {
                Column column = writeMethod.getDeclaredAnnotation(Column.class);
                if (column != null) {
                    Method readMethod = propertyDescriptor.getReadMethod();
                    if (readMethod == null) {
                        throw new PersistenceException(BaseSystemMessages.NO_GETTER_METHOD.create(entity.getClass(), propertyDescriptor.getName()));
                    }
                    Object value = readMethod.invoke(entity);
                    entityLine.append(";").append(column.name()).append(":").append(value);
                }
            }
        }
        return entityLine.toString();
    }
}
