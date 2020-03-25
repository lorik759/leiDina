package main.java.leiDina.tec.core.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * A utility class that has several methods for reflection.
 *
 * @author vitor.alves
 */
public class ReflectionUtils {

    /**
     * Instantiates a new instance of the specified class, using the gaven arguments as parameters.
     *
     * @param clazz the class of the instantiated object.
     * @param args the arguments of the constrictor.
     * @param <T> the type of the object that will be instantiated.
     * @return A object of the specified class.
     * @throws NoSuchMethodException {@link NoSuchMethodException}.
     * @throws IllegalAccessException {@link IllegalAccessException}.
     * @throws InstantiationException {@link InstantiationException}.
     * @throws InvocationTargetException {@link InvocationTargetException}.
     */
    public static <T> T newInstance(Class<T> clazz, Object... args)
        throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?>[] argsType = Arrays.stream(args).map(Object::getClass).toArray(Class[]::new);
        return newInstance(clazz.getDeclaredConstructor(argsType), args);
    }

    /**
     * Instantiates a new instance using the specified constructor, using the gaven arguments as parameters for the constrictor.
     *
     * @param constructor the constructor that will be used to instantiate the object.
     * @param args the arguments of the constrictor.
     * @param <T> the type of the object that will be instantiated.
     * @return the object that the constructor belongs to.
     * @throws IllegalAccessException {@link IllegalAccessException}.
     * @throws InvocationTargetException {@link InvocationTargetException}.
     * @throws InstantiationException {@link InstantiationException}.
     */
    public static <T> T newInstance(Constructor<T> constructor, Object... args)
        throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return makeAccessible(constructor).newInstance(args);
    }

    /**
     * Makes the object accessible.
     */
    private static <T extends AccessibleObject> T makeAccessible(T object) {
        if (!object.isAccessible()) {
            object.setAccessible(true);
        }
        return object;
    }

    /**
     * Returns the value from the specified field.
     *
     * @param object the that contains the specified field.
     * @param filedName the name of the field.
     * @param <T> The type of the value of the field.
     * @return the value from the field.
     * @throws NoSuchFieldException {@link NoSuchFieldException}.
     * @throws IllegalAccessException {@link IllegalAccessException}.
     */
    public static <T> Object getValueFromFiled(T object, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> aClass = object.getClass();
        Field field = aClass.getField(filedName);
        return makeAccessible(field).get(object);
    }

    /**
     * Invokes the specified method.
     *
     * @param method method to be invoked.
     * @param object object that contains the method.
     * @param args arguments of the method.
     * @param <T> the return type of the method.
     * @throws InvocationTargetException {@link InvocationTargetException}.
     * @throws IllegalAccessException {@link IllegalAccessException}.
     */
    public static <T> void invoke(Method method, T object, Object... args) throws InvocationTargetException, IllegalAccessException {
        makeAccessible(method).invoke(object, args);
    }
}
