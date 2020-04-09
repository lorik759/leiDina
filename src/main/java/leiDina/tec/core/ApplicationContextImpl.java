package main.java.leiDina.tec.core;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.env.ConfigurableApplicationProvider;
import main.java.leiDina.tec.core.env.SystemLoader;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.service.SystemService;

/**
 * A base implementation of the {@link ApplicationContext} interface. This implementation provides the base implementation of all application context
 * resource.
 *
 * @author vitor.alves
 */
public class ApplicationContextImpl implements ApplicationContext {

    private final ApplicationDefinitions applicationDefinitions;

    private static final String SYSTEM_PROPERTIES = "system-properties.xml";

    private ConfigurableApplicationProvider configurableApplicationProvider;

    private final Map<SystemKey, SystemService> systemServices = new HashMap<>();

    public ApplicationContextImpl(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        SystemLoader systemLoader = configurableApplicationProvider.getSysetmLoaderFor(SYSTEM_PROPERTIES);
        List<SystemService> systemServices = systemLoader.loadSystemServices();
        for (SystemService systemService : systemServices) {
            this.systemServices.put(systemService.getKey(), systemService);
            systemService.init(configurableApplicationProvider.getEnvironmentFor(systemService.getEnvironmentName()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnvironmentProvider(ConfigurableApplicationProvider environment) {
        this.configurableApplicationProvider = environment;
    }

    @Override
    public SystemService getService(SystemKey key) {
        return this.systemServices.get(key);
    }
}
