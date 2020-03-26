package main.java.leiDina.biz.scene;

import java.net.URL;
import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public class MainScene implements Scenes {

    private static final String LOCATION = "/main/java/leiDina/biz/view/MainView.fxml";

    private static final String TITLE = "Leitura Dinamica";

    @Override
    public URL getLocation() {
        return this.getClass().getResource(LOCATION);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
