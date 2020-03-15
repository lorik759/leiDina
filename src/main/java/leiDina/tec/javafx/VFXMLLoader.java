package main.java.leiDina.tec.javafx;


import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.core.ApplicationContext;
import main.java.leiDina.tec.core.ApplicationThreadContext;
import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public class VFXMLLoader {

    private FXMLLoader fxmlLoader;

    public VFXMLLoader(URL scene) {
        this.fxmlLoader = new FXMLLoader(scene);
        ApplicationContext applicationContext = ApplicationThreadContext.getApplicationContext();
        this.fxmlLoader.setControllerFactory(applicationContext.getControllerFactory()::getController);
//        this.fxmlLoader.setBuilderFactory(applicationContext.getBuilderFactory());
    }

    public VFXMLLoader(Scenes scenes) {
        this(scenes.getLocation());
    }

    public <T> T load() throws IOException {
        T load = this.fxmlLoader.load();
        return load;
    }

}
