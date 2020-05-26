package main.java.leiDina.tec.core.utils;

/**
 * A utility class that has several methods for class manipulation.
 *
 * @author vitor.alves
 */
public abstract class ClassUtils {

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader =  ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }

}
