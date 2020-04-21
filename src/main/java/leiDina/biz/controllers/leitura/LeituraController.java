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
import javax.annotation.Resource;
import main.java.leiDina.biz.dao.ConfiguracaoDAO;
import main.java.leiDina.biz.model.Configuracao;
import main.java.leiDina.tec.core.utils.StringUtils;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.persister.exception.EntityNotFoundException;
import main.java.leiDina.tec.persister.factory.DAOFactory;

/**
 * @author vitor.alves
 */
public class LeituraController extends BaseModelController<LeituraModel> {

    @Resource(name = "daoFactory")
    private DAOFactory daoFactory;

    private static String[] text;

    private static int next = 0;

    private static String fullText = "";

    public static Configuracao configuracao;

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
        fullText = "";
        next = 0;
        ConfiguracaoDAO configuracaoDAO = daoFactory.getDAOByClass(ConfiguracaoDAO.class);
        try {
            configuracao = configuracaoDAO.findById(1L);
        } catch (EntityNotFoundException e) {
            configuracao = configuracaoDAO.createEntity();
            configuracao.setSeconds(0.5);
            configuracao.setNumeroPalavras(1);
        }
    }

    @FXML
    public void lerTexto(MouseEvent mouseEvent) {
        String msg = this.getModel().getTextoEntrada();
        if (StringUtils.isNotEmpty(msg)) {
            LeituraController.text = msg.split("\\W+");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(configuracao.getSeconds()), event -> {
                String nextString = LeituraController.getNextString();
                textoSaida.setText(nextString);
            }));
            timeline.setCycleCount(text.length);
            timeline.play();
        }
    }

    private static String getNextString() {
        for (int i = 0; i < configuracao.getNumeroPalavras(); i++) {
            if (next < text.length) {
                String s = text[next];
                next++;
                fullText = fullText + " " + s;
            }
        }
        return fullText;
    }
}
