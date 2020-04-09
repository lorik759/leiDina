package main.java.leiDina.tec.core;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironmentProvider;
import main.java.leiDina.tec.core.env.SystemLoader;
import main.java.leiDina.tec.core.env.SystemLoaderImpl;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemKey;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.core.service.Wire;
import main.java.leiDina.tec.core.utils.ReflectionUtils;
import main.java.leiDina.tec.javafx.exception.ControllerException;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.factory.ControllerFactoryImpl;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;
import main.java.leiDina.tec.javafx.service.NodeAssociation;

/**
 * A base implementation of the {@link ApplicationContext} interface. This implementation provides the base implementation of all application context
 * resource.
 *
 * @author vitor.alves
 */
public class ApplicationContextImpl implements ApplicationContext {

    private final ApplicationDefinitions applicationDefinitions;

    private ConfigurableApplicationEnvironmentProvider environment;

    private final Map<SystemKey, SystemService> systemServices = new HashMap<>();

    public ApplicationContextImpl(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        SystemLoader systemLoader = new SystemLoaderImpl();
        List<SystemService> systemServices = systemLoader.loadSystemServices();
        for (SystemService systemService : systemServices) {
            this.systemServices.put(systemService.getKey(), systemService);
            systemService.init(environment);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnvironmentProvider(ConfigurableApplicationEnvironmentProvider environment) {
        this.environment = environment;
    }

    @Override
    public SystemService getService(SystemKey key) {
        return this.systemServices.get(key);
    }
}
