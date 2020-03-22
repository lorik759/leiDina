package main.java.leiDina.tec.core.model;

import java.util.Set;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.annotation.VApplicationProfile;
import main.java.leiDina.tec.core.enums.Profile;

/**
 * A model for the base definitions for a VApplication. This contains all information for the startup of the application.
 *
 * @author vitor.alves
 */
public class ApplicationDefinitions {

    private final Logger logger;

    private final Class<?> starterClass;

    private final Set<Class<?>> primeryClasses;

    private Profile profile;

    public ApplicationDefinitions(Logger logger, Class<?> starterClass, Set<Class<?>> primeryClasses) {
        this.logger = logger;
        this.starterClass = starterClass;
        this.primeryClasses = primeryClasses;
    }

    /**
     * Returns the starting class of the application.
     *
     * @return {@link Class}
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
     * Returns primary classes of the application. Usually the primary classes are the classes within different packages where bean resources are
     * located in.
     *
     * @return a set with classes.
     */
    public Set<Class<?>> getPrimeryClasses() {
        return primeryClasses;
    }

    /**
     * Gets the profile of the application.
     *
     * @return {@link Profile}
     */
    public Profile getProfile() {
        if (profile == null) {
            VApplicationProfile annotation = starterClass.getAnnotation(VApplicationProfile.class);
            if (annotation != null) {
                this.profile = annotation.value();
            } else {
                this.profile = Profile.FULL;
            }
        }
        return this.profile;
    }
}
