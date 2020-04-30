package main.java.leiDina.tec.vinjection;

import java.util.Set;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.vinjection.factory.BeanFactory;
import main.java.leiDina.tec.vinjection.factory.XmlBeanFactory;
import main.java.leiDina.tec.vinjection.service.PackageLoader;

/**
 * An implementation of the {@link InjectableApplicationContext} that uses an XMl for the definition of beans.
 *
 * @author vitor.alves
 */
public class XmlInjectableApplicationContext implements InjectableApplicationContext {

    private static final String SYSTEM_DEFINITION = "vapplication-definitons.xml";

    private ApplicationDefinitions applicationDefinitions;

    private BeanFactory beanFactory;

    public XmlInjectableApplicationContext() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        Set<String> packages = this.loadAllPackages(SYSTEM_DEFINITION);
        this.beanFactory = new XmlBeanFactory(packages, applicationDefinitions);
        this.beanFactory.init();
    }

    /**
     * Loads all packages from within a specified package name.
     *
     * @param packageName the root package.
     * @return a set of all packages inside and including the root package.
     */
    private Set<String> loadAllPackages(String packageName) {
        return new PackageLoader(this.applicationDefinitions).loadAllPackages(packageName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationDefinitions(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SystemService getService(SystemServiceKey key) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getBeanByType(Class<?> type) {
        return beanFactory.getBeanByType(type).getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T getBeanById(String id) {
        return beanFactory.getBeanById(id).getInstance();
    }
}
