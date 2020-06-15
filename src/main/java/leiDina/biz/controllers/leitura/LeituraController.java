package main.java.leiDina.biz.controllers.leitura;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import main.java.leiDina.biz.service.LeituraService;
import main.java.leiDina.tec.core.beans.annotations.Injected;
import main.java.leiDina.tec.javafx.controller.BaseModelController;

/**
 * @author vitor.alves
 */
public class LeituraController extends BaseModelController<LeituraModel> {

    @Injected
    private LeituraService leituraService;

    @FXML
    private TextArea textoSaida;

    @Override
    protected LeituraModel createModel() {
        return new LeituraModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }

    @FXML
    public void lerTexto(MouseEvent mouseEvent) {
        leituraService.iniciarLeitura(this.getModel());
        if (leituraService.temAlgumaCoisaALer()) {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(leituraService.getSegundos()), event -> textoSaida.setText(leituraService.getProximaString())));
            timeline.setCycleCount(leituraService.getNumeroDeVezParaLer());
            timeline.play();
        }
    }
}
