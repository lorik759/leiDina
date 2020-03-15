package main.java.leiDina.tec.core.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import main.java.leiDina.tec.core.exception.ReflectionException;

/**
 * @author vitor.alves
 */
public class ReflectionUtils {

    public static <T> T newInstance(Class<T> clazz, Object... args) {
        try {
            Class<?>[] argsType = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
            return newInstance(clazz.getDeclaredConstructor(argsType), args);
        } catch (Throwable e) {
            throw new ReflectionException(e);
        }
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... args) {
        try {
            return makeAccessible(constructor).newInstance(args);
        } catch (Throwable e) {
            throw new ReflectionException(e);
        }
    }

    private static <T extends AccessibleObject> T makeAccessible(T object) {
        if (!object.isAccessible()) {
            object.setAccessible(true);
        }
        return object;
    }

    public static <T> Object getValueFromFiled(T object, String filedName) {
        try {
            Class<?> aClass = object.getClass();
            Field field = aClass.getField(filedName);
            return makeAccessible(field).get(object);
        } catch (Throwable e) {
            throw new ReflectionException(e);
        }
    }
}
