package main.java.leiDina.tec.vinjection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.vinjection.enums.DefinitionProperty;

/**
 * @author vitor.alves
 */
public class PackageLoader {

    private final ConfigurableApplicationProvider environmentProvider;

    private final Logger logger;

    private List<String> allPackages = new ArrayList<>();

    public PackageLoader(ApplicationDefinitions applicationDefinitions) {
        this.environmentProvider = applicationDefinitions.getConfigurableApplicationProvider();
        this.logger = applicationDefinitions.getLogger();
    }

    public List<String> loadAllPackages(String packageName) {
        logger.info("Loading XML : " + packageName);
        ConfigurableApplicationEnvironment environment = this.environmentProvider
            .getEnvironmentFor(packageName);
        SystemProperty<String> packagesSystemProperty = environment.loadSystemPropertiesFor(DefinitionProperty.PACKAGES.getName());
        List<String> properties = packagesSystemProperty.getProperties();
        for (String property : properties) {
            this.loadAllPackages(property);
        }
        this.allPackages.addAll(properties);
        return this.allPackages;
    }

}
