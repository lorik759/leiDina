package main.java.leiDina.tec.javafx;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.javafx.factory.ControllerFactory;
import main.java.leiDina.tec.javafx.scene.Scenes;
import main.java.leiDina.tec.javafx.service.ModelSceneWire;

/**
 * @author vitor.alves
 */
public class VFXMLLoader {

    private ModelSceneWire modelWire;

    private ControllerFactory controllerFactory;

    /**
     * Loads the fxml scene and wires the controller to scene if necessary.
     *
     * @param <T> return type fo the loaded scene.
     * @return the loaded scene
     * @throws IOException if unable to load scene.
     */
    public <T> T load(Scenes scenes) throws Exception {
        FXMLLoader fxmlLoader = this.creatFXMLLoader(scenes);
        T load = fxmlLoader.load();
        this.wireControllerToModelIfNeed(fxmlLoader);
        return load;
    }

    /**
     * Creates a {@link FXMLLoader} for the specified {@link Scenes}.
     *
     * @param scenes the {@link Scenes} of the {@link FXMLLoader}.
     * @return a {@link FXMLLoader}.
     */
    private FXMLLoader creatFXMLLoader(Scenes scenes) {
        FXMLLoader fxmlLoader = new FXMLLoader(scenes.getLocation());
        fxmlLoader.setControllerFactory(controllerFactory::getController);
        this.modelWire.setFxmlLoader(fxmlLoader);
        return fxmlLoader;
    }

    /**
     * Checks if controller is of type {@link BaseModelController}, and if true, than wire the model to scene;
     * @param fxmlLoader
     */
    private void wireControllerToModelIfNeed(FXMLLoader fxmlLoader) throws Exception {
        Object controller = fxmlLoader.getController();
        if (controller instanceof BaseModelController) {
            this.modelWire.wire((BaseModelController<?>) controller);
        }
    }

    public void setModelWire(ModelSceneWire modelWire) {
        this.modelWire = modelWire;
    }

    public void setControllerFactory(ControllerFactory controllerFactory) {
        this.controllerFactory = controllerFactory;
    }
}
