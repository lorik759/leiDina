package main.java.leiDina.tec.core.env;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import main.java.leiDina.tec.core.model.ClassSystemProperties;
import main.java.leiDina.tec.core.model.SystemProperties;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.core.utils.ResourceUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class ConfigurableApplicationEnvironmentImpl implements ConfigurableApplicationEnvironment {

    private static final String SYSTEM_PROPERTIES = "system-properties.xml";

    private static final Map<String, ClassSystemProperties> cache = new HashMap<>();

    public SystemProperties<Class<?>> loadSystemPropertiesFor(Class<?> type) {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemPropertiesFor(classLoader, type);
    }

    private SystemProperties<Class<?>> loadSystemPropertiesFor(ClassLoader classLoader, Class<?> type) {
        String name = type.getName();
        ClassSystemProperties classSystemProperties = cache.get(name);
        if (classSystemProperties != null) {
            return classSystemProperties;
        }
        classSystemProperties = new ClassSystemProperties(type, name);
        try {
            Enumeration<URL> resources = classLoader.getResources(SYSTEM_PROPERTIES);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties properties = ResourceUtils.getXMLPropertiesFromURL(url);
                String values = (String) properties.get(name);
                for (String classes : StringUtils.replaceNewLineAndSplitComma(values)) {
                    Class<?> aClass = Class.forName(classes.trim());
                    classSystemProperties.addSystemProperty(aClass);
                }
            }
            cache.put(name, classSystemProperties);
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load properteis from: " + SYSTEM_PROPERTIES, e);
        }
        return classSystemProperties;
    }
}
