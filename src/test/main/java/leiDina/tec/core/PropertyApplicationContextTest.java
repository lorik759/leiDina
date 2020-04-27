package main.java.leiDina.tec.core;

import main.java.leiDina.tec.core.context.ApplicationContext;
import main.java.leiDina.tec.core.context.PropertyApplicationContext;
import main.java.leiDina.tec.core.service.SystemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class PropertyApplicationContextTest {

    @Test
    public void testApplicationContextInit() {
        ApplicationContext applicationContext = new PropertyApplicationContext();
        applicationContext.setApplicationDefinitions(null);
        applicationContext.init();
        SystemService service = applicationContext.getService(new MockServiceServiceKey());
        Assertions.assertNotNull(service);
        service = applicationContext.getService(new MockSecondServiceServiceKey());
        Assertions.assertNotNull(service);
    }

}
