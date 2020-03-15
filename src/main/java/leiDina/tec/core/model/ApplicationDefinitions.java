package main.java.leiDina.tec.core.model;

import java.util.Set;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.annotation.VApplicationProfile;
import main.java.leiDina.tec.core.enums.Profile;

/**
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

    public Class<?> getStarterClass() {
        return starterClass;
    }

    public Logger getLogger() {
        return logger;
    }

    public Set<Class<?>> getPrimeryClasses() {
        return primeryClasses;
    }

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
