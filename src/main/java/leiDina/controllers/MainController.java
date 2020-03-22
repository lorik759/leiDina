package main.java.leiDina.controllers;

import java.io.IOException;
import javafx.scene.layout.BorderPane;
import main.java.leiDina.tec.javafx.VFXMLLoader;
import main.java.leiDina.tec.javafx.control.SceneButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;

/**
 * @author vitor.alves
 */
public class MainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    public void setScene(MouseEvent mouseEvent) {
        SceneButton sceneButton = (SceneButton) mouseEvent.getSource();
        VFXMLLoader vfxmlLoader = new VFXMLLoader(sceneButton.getScenes());
        try {
            borderPane.setCenter(vfxmlLoader.load());
        } catch (IOException e) {
            throw new VFXException(FXSystemMessages.SCENE_LOADING_EXCEPTION.create(sceneButton.getScenes().getTitle()));
        }
    }
}