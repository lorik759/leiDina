package main.java.leiDina.tec.core.env;

import java.util.List;
import main.java.leiDina.tec.core.mock.SecondSystemServiceMock;
import main.java.leiDina.tec.core.mock.SystemServiceMock;
import main.java.leiDina.tec.core.service.SystemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class SystemLoderImplTest {

    private static final String SYSTEM_PROPERTIES_TEST = "system-properties-test.xml";

    @Test
    public void testLoadSystemServices() {
        SystemLoader systemLoader = new SystemLoaderImpl(SYSTEM_PROPERTIES_TEST);
        List<SystemService> systemServices = systemLoader.loadSystemServices();
        Assertions.assertFalse(systemServices.isEmpty());
        Assertions.assertEquals(2, systemServices.size());
        SystemService mockOne = systemServices.get(0);
        SystemService mockTwo = systemServices.get(1);
        Assertions.assertEquals(SystemServiceMock.class, mockOne.getClass());
        Assertions.assertEquals(SecondSystemServiceMock.class, mockTwo.getClass());
    }

}
