package main.java.leiDina.tec.core;

import main.java.leiDina.tec.core.env.ConfigurableApplicationProviderImpl;
import main.java.leiDina.tec.core.mock.MockSecondServiceServiceKey;
import main.java.leiDina.tec.core.mock.MockServiceServiceKey;
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
        SystemService service = applicationContext.getService(new MockServiceServiceKey());
        Assertions.assertNotNull(service);
        service = applicationContext.getService(new MockSecondServiceServiceKey());
        Assertions.assertNotNull(service);
    }

}
