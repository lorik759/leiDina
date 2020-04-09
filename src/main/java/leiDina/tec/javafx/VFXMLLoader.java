package main.java.leiDina.tec.javafx;


import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.core.ApplicationContext;
import main.java.leiDina.tec.core.ApplicationThreadContext;
import main.java.leiDina.tec.core.model.SystemProperty;
import main.java.leiDina.tec.core.service.SystemService;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.scene.Scenes;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;
import main.java.leiDina.tec.javafx.service.VFXKey;

/**
 * @author vitor.alves
 */
public class VFXMLLoader {

    private FXMLLoader fxmlLoader;

    private ModelSceneWire modelWire;

    public VFXMLLoader(URL scene) {
        this.fxmlLoader = new FXMLLoader(scene);
        ApplicationContext applicationContext = ApplicationThreadContext.getApplicationContext();
        SystemService fxService = applicationContext.getService(new VFXKey());
        SystemProperty<ModelSceneWire> modelSceneWire = fxService.getPropertyByType(ModelSceneWire.class);
        SystemProperty<ControllerFactory> controllerFactoryService = fxService.getPropertyByType(ControllerFactory.class);
        this.modelWire = modelSceneWire.getProperty();
        ControllerFactory controllerFactory = controllerFactoryService.getProperty();
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
