package test.java.leiDina.tec.javafx.factory;


import main.java.leiDina.tec.javafx.ApplicationContext;
import main.java.leiDina.tec.javafx.ApplicationContextImpl;
import main.java.leiDina.tec.javafx.exception.BuilderException;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * @author vitor.alves
 */
public class ControllerFactoryTest {

    private static ApplicationContext applicationContext;

   @BeforeAll
   protected static void setUp() {
       applicationContext = new ApplicationContextImpl();
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

}
