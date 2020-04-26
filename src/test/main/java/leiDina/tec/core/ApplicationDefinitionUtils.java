package main.java.leiDina.tec.core;

import static java.util.Collections.emptySet;

import java.util.logging.Logger;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;

/**
 * @author vitor.alves
 */
public class ApplicationDefinitionUtils {

    private static final Logger logger = Logger.getLogger(VApplication.class.getName());

    public static ApplicationDefinitions getMockApplicationDefinitions(ConfigurableApplicationProvider provider) {
        return new ApplicationDefinitions(logger, ApplicationDefinitionUtils.class, emptySet(), provider);
    }
}
