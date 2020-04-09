package main.java.leiDina.tec.core;

import main.java.leiDina.tec.core.env.ConfigurableApplicationProviderImpl;
import main.java.leiDina.tec.core.mock.MockSecondServiceKey;
import main.java.leiDina.tec.core.mock.MockServiceKey;
import main.java.leiDina.tec.core.service.SystemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class ApplicationContextImplTest {

    @Test
    public void testApplicationContextInit() {
        ApplicationContext applicationContext = new ApplicationContextImpl(null);
        applicationContext.setEnvironmentProvider(new ConfigurableApplicationProviderImpl());
        applicationContext.init();
        SystemService service = applicationContext.getService(new MockServiceKey());
        Assertions.assertNotNull(service);
        service = applicationContext.getService(new MockSecondServiceKey());
        Assertions.assertNotNull(service);
    }

}
