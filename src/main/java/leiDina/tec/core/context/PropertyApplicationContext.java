package main.java.leiDina.tec.core.context;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.env.SystemLoader;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * A base implementation of the {@link ApplicationContext} interface. This implementation provides the base implementation of all application context
 * resource.
 *
 * @author vitor.alves
 */
@Deprecated
public class PropertyApplicationContext implements ApplicationContext {

    private static final String SYSTEM_PROPERTIES = "system-properties.xml";

    private ApplicationDefinitions applicationDefinitions;

    private final Map<SystemServiceKey, SystemService> systemServices = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        ConfigurableApplicationProvider environmentProvider = applicationDefinitions.getConfigurableApplicationProvider();
        Logger logger = applicationDefinitions.getLogger();
        SystemLoader systemLoader = environmentProvider.getSystemLoaderFor(SYSTEM_PROPERTIES);
        logger.warning("Loading system services.");
        List<SystemService> systemServices = systemLoader.loadSystemServices();
        for (SystemService systemService : systemServices) {
            logger.warning("Initializing system service: " + systemService.getServiceName());
            systemService.init(environmentProvider.getEnvironmentFor(systemService.getEnvironmentName()));
            this.systemServices.put(systemService.getKey(), systemService);
        }
    }

    @Override
    public void setApplicationDefinitions(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemService getService(SystemServiceKey key) {
        return this.systemServices.get(key);
    }
}
