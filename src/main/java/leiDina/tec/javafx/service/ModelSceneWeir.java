package main.java.leiDina.tec.javafx.service;

import javafx.fxml.FXMLLoader;
import main.java.leiDina.tec.core.service.Weir;
import main.java.leiDina.tec.javafx.controller.BaseController;

/**
 * @author vitor.alves
 */
public class ModelSceneWeir implements Weir<BaseController> {

    private final FXMLLoader fxmlLoader;

    public ModelSceneWeir(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    @Override
    public void weir(BaseController object) {
        // TODO:Weir the model of controller to the scene
    }
}
