package main.java.leiDina.tec.javafx.scene;

import java.io.IOException;
import javafx.scene.Node;
import main.java.leiDina.tec.javafx.VFXMLLoader;

/**
 * @author vitor.alves
 */
public class SceneManager {

    public Node getScene(Scenes scene) throws IOException {
        VFXMLLoader loader = new VFXMLLoader(scene);
        return loader.load();
    }

}
