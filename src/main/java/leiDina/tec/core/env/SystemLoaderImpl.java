package main.java.leiDina.tec.core.env;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.ResourceUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class SystemLoaderImpl implements SystemLoader {

    private static final String SYSTEM_PROPERTIES = "system-properties.xml";

    private static final String SERVICES = "Services";

    @Override
    public List<SystemService> loadSystemServices() {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemServices(classLoader);
    }

    private List<SystemService> loadSystemServices(ClassLoader classLoader) {
        List<SystemService> systemServices = new ArrayList<>();
        try {
            Enumeration<URL> resources = classLoader.getResources(SYSTEM_PROPERTIES);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                Properties properties = ResourceUtils.getXMLPropertiesFromURL(url);
                String values = (String) properties.get(SERVICES);
                for (String classes : StringUtils.replaceNewLineAndSplitComma(values)) {
                    if (StringUtils.isNotEmpty(classes)) {
                        Class<?> aClass = Class.forName(StringUtils.removeSpaces(classes));
                        SystemService systemService = null;
                        try {
                            systemService = (SystemService) ReflectionUtils.newInstance(aClass);
                        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        systemServices.add(systemService);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load properteis from: " + SYSTEM_PROPERTIES, e);
        }
        return systemServices;
    }
}
