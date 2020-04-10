package main.java.leiDina.biz.controllers.leitura;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.javafx.controller.BaseModelController;

/**
 * @author vitor.alves
 */
public class LeituraController extends BaseModelController<LeituraModel> {

    private static String[] text;

    private static int next = 0;

    private static String fullText = "";

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
        String msg = this.getModel().getTextoEntrada();
        if (StringUtils.isNotEmpty(msg)) {
            LeituraController.text = msg.split("\\W+");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String nextString = LeituraController.getNextString();
                    textoSaida.setText(nextString);
                }
            }));
            timeline.setCycleCount(text.length);
            timeline.play();
        }
    }

    private static String getNextString() {
        String s = text[next];
        next++;
        fullText = fullText + " " + s;
        return fullText;
    }
}
