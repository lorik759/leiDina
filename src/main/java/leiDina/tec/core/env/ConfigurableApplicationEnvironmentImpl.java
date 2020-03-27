package main.java.leiDina.tec.core.env;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import main.java.leiDina.tec.core.model.ClassSystemProperty;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.core.utils.ResourceUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * A base implementation of the {@link ConfigurableApplicationEnvironment} that reads and loads the system properties from within a
 * "system-properties.xml". It's a simple xml properties that holds a key, and a list of classes to be used by the system.
 *
 * @author vitor.alves
 */
public class ConfigurableApplicationEnvironmentImpl implements ConfigurableApplicationEnvironment {

    private static final String SYSTEM_PROPERTIES = "system-properties.xml";

    private static final Map<String, ClassSystemProperty> cache = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    public SystemProperty<Class<?>> loadSystemPropertiesFor(Class<?> type) {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemPropertiesFor(classLoader, type);
    }

    private SystemProperty<Class<?>> loadSystemPropertiesFor(ClassLoader classLoader, Class<?> type) {
        String name = type.getSimpleName();
        ClassSystemProperty classSystemProperties = cache.get(name);
        if (classSystemProperties != null) {
            return classSystemProperties;
        }
        classSystemProperties = new ClassSystemProperty(type, name);
        try {
            Enumeration<URL> resources = classLoader.getResources(SYSTEM_PROPERTIES);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties properties = ResourceUtils.getXMLPropertiesFromURL(url);
                String values = (String) properties.get(name);
                for (String classes : StringUtils.replaceNewLineAndSplitComma(values)) {
                    if (StringUtils.isNotEmpty(classes)) {
                        Class<?> aClass = Class.forName(classes.replaceAll("^\\s+", ""));
                        classSystemProperties.addProperty(aClass);
                    }
                }
            }
            cache.put(name, classSystemProperties);
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load properteis from: " + SYSTEM_PROPERTIES, e);
        }
        return classSystemProperties;
    }
}
