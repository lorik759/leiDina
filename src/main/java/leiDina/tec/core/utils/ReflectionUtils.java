package main.java.leiDina.tec.core.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author vitor.alves
 */
public class ReflectionUtils {

    public static <T> T newInstance(Class<T> clazz, Object... args)
        throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?>[] argsType = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
        return newInstance(clazz.getDeclaredConstructor(argsType), args);
    }

    public static <T> T newInstance(Constructor<T> constructor, Object... args)
        throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return makeAccessible(constructor).newInstance(args);
    }

    private static <T extends AccessibleObject> T makeAccessible(T object) {
        if (!object.isAccessible()) {
            object.setAccessible(true);
        }
        return object;
    }

    public static <T> Object getValueFromFiled(T object, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = object.getClass();
        Field field = aClass.getField(filedName);
        return makeAccessible(field).get(object);
    }

    public static <T> void invoke(Method method, T object, Object... args) throws InvocationTargetException, IllegalAccessException {
        makeAccessible(method).invoke(object, args);
    }
}
