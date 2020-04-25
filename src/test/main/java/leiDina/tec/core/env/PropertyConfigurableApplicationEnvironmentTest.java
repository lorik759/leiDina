package main.java.leiDina.tec.core.env;

import java.util.List;
import main.java.leiDina.tec.core.xml.Entity;
import main.java.leiDina.tec.core.xml.EntityOne;
import main.java.leiDina.tec.core.xml.EntityTwo;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.ClassPropertyResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author vitor.alves
 */
public class PropertyConfigurableApplicationEnvironmentTest {

    private static final String SYSTEM_SERVICE_TEST = "test-service-properties.xml";

    private static final String PROPERTY_NAME = "TestName";

    @Test
    public void testLoadSystemServicesForType() {
        ConfigurableApplicationEnvironment configurableApplicationEnvironment = new PropertyConfigurableApplicationEnvironment(SYSTEM_SERVICE_TEST,
            null);
        SystemProperty<Class<?>> systemPropertiesForType = configurableApplicationEnvironment
            .loadSystemPropertiesFor(SystemProperty.class, new ClassPropertyResolver());
        Assertions.assertNotNull(systemPropertiesForType);
        Assertions.assertEquals(Class.class, systemPropertiesForType.getType());
        List<Class<?>> properties = systemPropertiesForType.getProperties();
        Assertions.assertEquals(1, properties.size());
        Class<?> aClass = properties.get(0);
        Assertions.assertEquals(EntityTwo.class, aClass);
    }

    @Test
    public void testLoadSystemServicesForName() {
        ConfigurableApplicationEnvironment configurableApplicationEnvironment = new PropertyConfigurableApplicationEnvironment(SYSTEM_SERVICE_TEST,
            null);
        SystemProperty<Class<?>> systemPropertiesForType = configurableApplicationEnvironment
            .loadSystemPropertiesFor(PROPERTY_NAME, new ClassPropertyResolver());
        Assertions.assertNotNull(systemPropertiesForType);
        Assertions.assertEquals(Class.class, systemPropertiesForType.getType());
        List<Class<?>> properties = systemPropertiesForType.getProperties();
        Assertions.assertEquals(2, properties.size());
        Class<?> aClass = properties.get(0);
        Assertions.assertEquals(EntityOne.class, aClass);
        aClass = properties.get(1);
        Assertions.assertEquals(Entity.class, aClass);
    }

}
