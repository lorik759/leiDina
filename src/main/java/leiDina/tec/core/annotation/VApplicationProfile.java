package main.java.leiDina.tec.core.annotation;

import main.java.leiDina.tec.core.enums.Profile;

/**
 * Marks the main class with the application profile. Indicating which property's to set up. For more information on what each profile indicates,
 * {@link Profile} has the specific profiles with a run down on what each profile offers.
 *
 * @author vitor.alves
 */
public @interface VApplicationProfile {

    Profile value() default Profile.FULL;

}
