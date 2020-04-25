package main.java.leiDina.tec.vinjection;

import java.util.List;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemServiceKey;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.vinjection.factory.BeanFactory;
import main.java.leiDina.tec.vinjection.factory.XmlBeanFactory;
import main.java.leiDina.tec.vinjection.service.PackageLoader;

/**
 * @author vitor.alves
 */
public class XmlInjectableApplicationContext implements InjectableApplicationContext {

    private static final String SYSTEM_DEFINITION = "vapplication-definitons.xml";

    private ApplicationDefinitions applicationDefinitions;

    private BeanFactory beanFactory;

    public XmlInjectableApplicationContext() {
    }

    @Override
    public void init() {
        List<String> packages = this.loadAllPackages(SYSTEM_DEFINITION);
        this.beanFactory = new XmlBeanFactory(packages, applicationDefinitions);
        this.beanFactory.init();
    }

    private List<String> loadAllPackages(String packageName) {
        return new PackageLoader(this.applicationDefinitions).loadAllPackages(packageName);
    }

    @Override
    public void setApplicationDefinitions(ApplicationDefinitions applicationDefinitions) {
        this.applicationDefinitions = applicationDefinitions;
    }

    @Override
    public SystemService getService(SystemServiceKey key) {
        return null;
    }

    @Override
    public <T> T getBeanByType(Class<?> type) {
        return beanFactory.getBeanByType(type).getInstance();
    }

    @Override
    public <T> T getBeanById(String id) {
        return beanFactory.getBeanById(id).getInstance();
    }
}
