package main.java.leiDina.tec.javafx;


import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.context.ApplicationThreadContext;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.javafx.scene.Scenes;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;
import main.java.leiDina.tec.javafx.service.VFXServiceKey;
import main.java.leiDina.tec.vinjection.BeanWireThreadContext;
import main.java.leiDina.tec.vinjection.annotations.Injected;
import main.java.leiDina.tec.vinjection.service.BeanWire;

/**
 * @author vitor.alves
 */
public class VFXMLLoader {

    private FXMLLoader fxmlLoader;

    @Injected
    private ModelSceneWire modelWire;

    @Resource(name = "controllerFactory")
    private ControllerFactory controllerFactory;

    public VFXMLLoader(URL scene) {
        this.fxmlLoader = new FXMLLoader(scene);
        BeanWire beanWire = BeanWireThreadContext.getBeanWire();
        try {
            beanWire.wire(this);
        } catch (Exception e) {
            throw new VFXException(FXSystemMessages.FAILED_TO_INITIALIZE_LOADER.create());
        }
        this.fxmlLoader.setControllerFactory(controllerFactory::getController);
        this.modelWire.setFxmlLoader(this.fxmlLoader);
    }

    public VFXMLLoader(Scenes scenes) {
        this(scenes.getLocation());
    }

    /**
     * Loads the fxml scene and wires the controller to scene if necessary.
     *
     * @param <T> return type fo the loaded scene.
     * @return the loaded scene
     * @throws IOException if unable to load scene.
     */
    public <T> T load() throws Exception {
        T load = this.fxmlLoader.load();
        this.wireControllerToModelIfNeed();
        return load;
    }

    /**
     * Checks if controller is of type {@link BaseModelController}, and if true, than wire the model to scene;
     */
    private void wireControllerToModelIfNeed() throws Exception {
        Object controller = this.fxmlLoader.getController();
        if (controller instanceof BaseModelController) {
            this.modelWire.wire((BaseModelController<?>) controller);
        }
    }

}
