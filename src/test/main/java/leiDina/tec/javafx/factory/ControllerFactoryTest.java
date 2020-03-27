package main.java.leiDina.tec.javafx.factory;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import main.java.leiDina.tec.core.ApplicationContext;
import main.java.leiDina.tec.core.ApplicationContextImpl;
import main.java.leiDina.tec.core.env.ConfigurableApplicationEnvironment;
import main.java.leiDina.tec.core.model.ApplicationDefinitions;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.factory.builder.GenericControllerBuilder;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

/**
 * @author vitor.alves
 */
public class ControllerFactoryTest {

    private static ApplicationContext applicationContext;

    @BeforeAll
    protected static void setUp() {
        ApplicationDefinitions applicationDefinitions = Mockito.mock(ApplicationDefinitions.class);
        Mockito.when(applicationDefinitions.getLogger()).then((Answer<Logger>) invocation -> Logger.getLogger(ControllerFactoryTest.class.getName()));
        applicationContext = new ApplicationContextImpl(applicationDefinitions);
        applicationContext.setEnvironment(mockConfigurableApplicationEnvironment());
        applicationContext.init();
    }

    @Test
    public void testNotNull() {
        ControllerFactory controllerFactory = applicationContext.getControllerFactory();
        Assertions.assertNotNull(controllerFactory);
    }

    @Test
    public void testGenericController() {
        ControllerFactory controllerFactory = applicationContext.getControllerFactory();
        Object controller = controllerFactory.getController(Controller.class);
        Assertions.assertEquals(controller.getClass(), Controller.class);
    }

    @Test
    public void getGenericController() {
        ControllerFactory controllerFactory = applicationContext.getControllerFactory();
        Executable executable = () -> controllerFactory.getController(ConstructerController.class);
        Assertions.assertThrows(BuilderException.class, executable);
    }

    private class ConstructerController {

        private String string;

        public ConstructerController(String string) {
            this.string = string;
        }
    }

    private static ConfigurableApplicationEnvironment mockConfigurableApplicationEnvironment() {
        SystemProperty systemProperty = Mockito.mock(SystemProperty.class);
        Mockito.when(systemProperty.getProperties()).then((Answer<List<?>>) invocation -> Collections.emptyList());

        final List<Class<?>> builders = new ArrayList<>();
        builders.add(GenericControllerBuilder.class);
        SystemProperty controllerFactorySystemProperty = Mockito.mock(SystemProperty.class);
        Mockito.when(controllerFactorySystemProperty.getProperties()).then((Answer<List<?>>) invocation -> builders);

        ConfigurableApplicationEnvironment configurableApplicationEnvironment = Mockito.mock(ConfigurableApplicationEnvironment.class);
        Mockito.when(configurableApplicationEnvironment.loadSystemPropertiesFor(ControllerFactory.class))
            .then((Answer<SystemProperty>) invocation -> controllerFactorySystemProperty);

        Mockito.when(configurableApplicationEnvironment.loadSystemPropertiesFor(ModelSceneWire.class))
            .then((Answer<SystemProperty>) invocation -> systemProperty);

        return configurableApplicationEnvironment;
    }
}
