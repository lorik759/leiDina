package main.java.leiDina.tec.core.env;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.PropertyResolver;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.core.utils.ResourceUtils;

/**
 * A base implementation of the {@link ConfigurableApplicationEnvironment} that reads and loads the system properties from within a
 * the environment location. It's a simple xml properties that holds a key, and a property.
 *
 * @author vitor.alves
 */
@Deprecated
public class PropertyConfigurableApplicationEnvironment implements ConfigurableApplicationEnvironment {

    private final String environmentLocation;

    private PropertyResolver<?> resolver;

    public PropertyConfigurableApplicationEnvironment(String environmentLocation) {
        this.environmentLocation = environmentLocation;
    }

    @Override
    public <T> SystemProperty<T> loadSystemPropertiesFor(String key) {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemPropertiesFor(classLoader, key);
    }

    private <T> SystemProperty<T> loadSystemPropertiesFor(ClassLoader classLoader, String key) {
        SystemProperty<T> systemProperty = null;
        try {
            Enumeration<URL> resources = classLoader.getResources(environmentLocation);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties properties = ResourceUtils.getXMLPropertiesFromURL(url);
                String values = (String) properties.get(key);
                SystemProperty<T> property = resolver.resolve(key, values);
                if (systemProperty == null) {
                    systemProperty = property;
                } else {
                    systemProperty.addProperties(property.getProperties());
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load properteis from: " + environmentLocation, e);
        }
        return systemProperty;
    }

    public void setResolver(PropertyResolver<?> resolver) {
        this.resolver = resolver;
    }
}
