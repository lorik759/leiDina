package main.java.leiDina.tec.persister;

import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.env.PropertyConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.service.BaseSystemService;
import main.java.leiDina.tec.core.service.ClassPropertyResolver;
import main.java.leiDina.tec.core.service.SingleObjectPropertyResolver;
import main.java.leiDina.tec.persister.factory.DAOFactory;
import main.java.leiDina.tec.persister.service.PersisterSystemKey;

/**
 * The system service for the PersisterModule.
 *
 * @author vitor.alves
 */
public class PersisterSystemService extends BaseSystemService {

    private static final String SERVICE_NAME = "PersisterSystemService";

    private static final String PERSISTER_SYSTEM_ENVIRONMENT = "persister-service-properties.xml";

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(ConfigurableApplicationEnvironment environment) {
        ((PropertyConfigurableApplicationEnvironment) environment).setResolver(new SingleObjectPropertyResolver());
        SystemProperty<Object> daoFactoryProperty = environment.loadSystemPropertiesFor(DAOFactory.class.getSimpleName());
        this.add(daoFactoryProperty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemServiceKey getKey() {
        return new PersisterSystemKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEnvironmentName() {
        return PERSISTER_SYSTEM_ENVIRONMENT;
    }
}
