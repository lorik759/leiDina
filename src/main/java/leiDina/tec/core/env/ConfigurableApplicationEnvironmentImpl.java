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
public class ConfigurableApplicationEnvironmentImpl implements ConfigurableApplicationEnvironment {

    private final String environmentLocation;

    private final ApplicationDefinitions applicationDefinitions;

    public ConfigurableApplicationEnvironmentImpl(String environmentLocation,
        ApplicationDefinitions applicationDefinitions) {
        this.environmentLocation = environmentLocation;
        this.applicationDefinitions = applicationDefinitions;
    }

    @Override
    public <T> SystemProperty<T> loadSystemPropertiesFor(Class<?> type, PropertyResolver<?> resolver) {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemPropertiesFor(classLoader, type.getSimpleName(), resolver);
    }

    @Override
    public <T> SystemProperty<T> loadSystemPropertiesFor(String key, PropertyResolver<?> resolver) {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemPropertiesFor(classLoader, key, resolver);
    }

    @Override
    public ApplicationDefinitions getApplicationDefinitions() {
        return this.applicationDefinitions;
    }

    private <T> SystemProperty<T> loadSystemPropertiesFor(ClassLoader classLoader, String key, PropertyResolver<?> resolver) {
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
}
