package main.java.leiDina.tec.core.utils;

/**
 * @author vitor.alves
 */
public class ClassUtils {

    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader =  ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }

}
