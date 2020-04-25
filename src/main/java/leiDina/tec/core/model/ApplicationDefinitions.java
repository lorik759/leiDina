package main.java.leiDina.tec.core.model;

import java.util.Set;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;

/**
 * A model for the base definitions for a VApplication. This contains all information for the startup of the application.
 *
 * @author vitor.alves
 */
public class ApplicationDefinitions {

    private final Logger logger;

    private final Class<?> starterClass;

    private final Set<Class<?>> primeryClasses;

    private ConfigurableApplicationProvider configurableApplicationProvider;

    public ApplicationDefinitions(Logger logger, Class<?> starterClass, Set<Class<?>> primeryClasses) {
        this.logger = logger;
        this.starterClass = starterClass;
        this.primeryClasses = primeryClasses;
    }

    public ApplicationDefinitions(Logger logger, Class<?> starterClass, Set<Class<?>> primeryClasses,
        ConfigurableApplicationProvider configurableApplicationProvider) {
        this.logger = logger;
        this.starterClass = starterClass;
        this.primeryClasses = primeryClasses;
        this.configurableApplicationProvider = configurableApplicationProvider;
    }

    /**
     * @return the starting class of the application.
     */
    public Class<?> getStarterClass() {
        return starterClass;
    }

    /**
     * @return {@link Logger}
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * @return primary classes of the application. Usually the primary classes are the classes within different packages where bean resources are
     * located in.
     */
    public Set<Class<?>> getPrimeryClasses() {
        return primeryClasses;
    }

    public ConfigurableApplicationProvider getConfigurableApplicationProvider() {
        return configurableApplicationProvider;
    }

    public void setConfigurableApplicationProvider(ConfigurableApplicationProvider configurableApplicationProvider) {
        this.configurableApplicationProvider = configurableApplicationProvider;
    }
}
