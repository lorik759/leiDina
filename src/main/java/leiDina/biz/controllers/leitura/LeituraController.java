package main.java.leiDina.biz.controllers.leitura;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import main.java.leiDina.tec.javafx.controller.BaseModelController;

/**
 * @author vitor.alves
 */
public class LeituraController extends BaseModelController<LeituraModel> {

    @FXML
    private TextArea textoSaida;

    @Override
    protected LeituraModel createModel() {
        return new LeituraModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        textoSaida.setEditable(false);
    }

    @FXML
    public void lerTexto(MouseEvent mouseEvent) {
        // Todo: add logica para a leitura.
    }
}
