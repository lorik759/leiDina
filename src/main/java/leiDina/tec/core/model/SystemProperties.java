package main.java.leiDina.tec.core.model;

import java.util.List;

/**
 * @author vitor.alves
 */
public interface SystemProperties<T> {

    Class<?> getType();

    String getName();

    List<T> getSystemProperties();

    void addSystemProperty(T systemProperties);
}
