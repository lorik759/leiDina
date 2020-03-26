package main.java.leiDina.tec.javafx.control;

import javafx.scene.control.Button;
import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public class SceneButton extends Button {

    private Scenes scene;

    public Scenes getScenes() {
        return scene;
    }

    public void setScenes(Scenes scene) {
        this.scene = scene;
    }

}
