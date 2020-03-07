package main.java.leiDina.tec.javafx;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.javafx.factory.controller.ControllerFactory;
import main.java.leiDina.tec.javafx.scen.Scenes;

/**
 * @author vitor.alves
 */
public class VFXMLLoader {

    private FXMLLoader fxmlLoader;

    public VFXMLLoader(String scene) {
        this.fxmlLoader = new FXMLLoader(getClass().getResource(scene));
        ControllerFactory controllerFactory = ApplicationThreadContext.getApplicationContext().getControllerFactory();
        this.fxmlLoader.setControllerFactory(controllerFactory::getController);
    }

    public VFXMLLoader(Scenes scenes) {
        this(scenes.getLocation());
    }

    public <T> T load() throws IOException {
        T load = this.fxmlLoader.load();
        return load;
    }

}
