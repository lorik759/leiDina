package main.java.leiDina.biz.controllers.configuration;

import main.java.leiDina.biz.model.Configuracao;
import main.java.leiDina.tec.javafx.controller.EntityPersisterController;

/**
 * @author vitor.alves
 */
public class ConfiguracaoController extends EntityPersisterController<Configuracao> {

    @Override
    protected Configuracao createModel() {
        return new Configuracao();
    }
}
