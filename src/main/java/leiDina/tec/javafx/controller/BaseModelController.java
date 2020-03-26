package main.java.leiDina.tec.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * A base structure of a Controller that contains a model to associate with a scene.
 *
 * @author vitor.alves
 */
public abstract class BaseModelController<M> implements Initializable {

    private M model;

    public BaseModelController() {
        super();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.model = createModel();
    }

    protected abstract M createModel();

    public M getModel() {
        return this.model;
    }
}
