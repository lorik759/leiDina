package main.java.leiDina.biz.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import main.java.leiDina.biz.scene.LeiDinamicaScenes;
import main.java.leiDina.tec.core.beans.annotations.Injected;
import main.java.leiDina.tec.javafx.VFXMLLoader;
import main.java.leiDina.tec.javafx.control.SceneButton;
import main.java.leiDina.tec.javafx.exception.VFXException;
import main.java.leiDina.tec.javafx.messages.FXSystemMessages;
import main.java.leiDina.tec.javafx.scene.Scenes;

/**
 * @author vitor.alves
 */
public class MainController implements Initializable {

    @Injected
    private VFXMLLoader vfxmlLoader;

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setScene(LeiDinamicaScenes.LEITURA.getScenes());
    }

    @FXML
    public void setScene(MouseEvent mouseEvent) {
        SceneButton sceneButton = (SceneButton) mouseEvent.getSource();
        setScene(sceneButton.getScenes());
    }

    private void setScene(Scenes scene) {
        try {
            borderPane.setCenter(vfxmlLoader.load(scene));
        } catch (Exception e) {
            throw new VFXException(FXSystemMessages.SCENE_LOADING_EXCEPTION.create(scene.getTitle()), e);
        }
    }
}
