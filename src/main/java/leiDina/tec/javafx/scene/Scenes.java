package main.java.leiDina.tec.javafx.scene;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.exception.VFXException;

/**
 * @author vitor.alves
 */
public interface Scenes {

    URL getLocation();

    String getTitle();

    static Object valueOf(String name) {
        try {
            Class<?> aClass = Class.forName(name);
            return ReflectionUtils.newInstance(aClass);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new VFXException(e);
        }
    }
}
