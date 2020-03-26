package main.java.leiDina.tec;

import javafx.fxml.FXML;
import main.java.leiDina.tec.javafx.controller.BaseModelController;

public class ModelController extends BaseModelController<Model> {

    @Override
    protected Model createModel() {
        return new Model();
    }

    @FXML
    public void printText() {
        System.out.println(this.getModel().getText());
    }
}
