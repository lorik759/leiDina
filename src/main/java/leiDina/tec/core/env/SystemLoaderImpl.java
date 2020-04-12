package main.java.leiDina.tec.core.env;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import main.java.leiDina.tec.core.exception.BaseException;
import main.java.leiDina.tec.core.exception.VApplicationException;
import main.java.leiDina.tec.core.messages.BaseSystemMessages;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.core.utils.ClassUtils;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.core.utils.ResourceUtils;
import main.java.leiDina.tec.core.utils.StringUtils;

/**
 * @author vitor.alves
 */
public class SystemLoaderImpl implements SystemLoader {

    private static final String SERVICES = "Services";

    private final String systemProperties;

    public SystemLoaderImpl(String systemProperties) {
        this.systemProperties = systemProperties;
    }

    @Override
    public List<SystemService> loadSystemServices() {
        ClassLoader classLoader = ClassUtils.getClassLoader();
        return loadSystemServices(classLoader);
    }

    private List<SystemService> loadSystemServices(ClassLoader classLoader) {
        List<SystemService> systemServices = new ArrayList<>();
        try {
            Enumeration<URL> resources = classLoader.getResources(systemProperties);
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
                        } catch (ReflectiveOperationException e) {
                            throw new VApplicationException(BaseSystemMessages.FAILED_TO_CREATE_SYSTEM_SERVICE.create(aClass));
                        }
                        systemServices.add(systemService);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to load properteis from: " + systemProperties, e);
        }
        return systemServices;
    }
}
