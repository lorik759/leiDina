package main.java.leiDina.biz.scene;

import java.net.URL;
import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public class LeituraScene implements Scenes {

    private final String LOCATION = "/main/java/leiDina/biz/view/Leitura.fxml";

    private final String TITLE = "Leitura";

    @Override
    public URL getLocation() {
        return getClass().getResource(LOCATION);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }
}
