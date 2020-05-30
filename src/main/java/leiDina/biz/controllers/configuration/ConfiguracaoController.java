package main.java.leiDina.biz.controllers.configuration;

import java.net.URL;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import main.java.leiDina.biz.domain.Configuracao;
import main.java.leiDina.biz.service.ConfiguracaoService;
import main.java.leiDina.tec.javafx.controller.EntityPersisterController;
import main.java.leiDina.tec.persister.exception.EntityNotFoundException;

/**
 * @author vitor.alves
 */
public class ConfiguracaoController extends EntityPersisterController<ConfiguracaoModel, Configuracao> {

    @Resource(name = "configuracaoService")
    private ConfiguracaoService configuracaoService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        Configuracao configuracao = findOrCreateConfiguracao();
        ConfiguracaoModel model = this.getModel();
        model.setNumeroPalavras(configuracao.getNumeroPalavras());
        model.setSeconds(configuracao.getSeconds());
    }

    private Configuracao findOrCreateConfiguracao() {
        try {
            return configuracaoService.findById(1L);
        } catch (EntityNotFoundException e) {
            return configuracaoService.creatDefaultConfiguracao();
        }
    }

    @Override
    protected ConfiguracaoModel createModel() {
        return new ConfiguracaoModel();
    }

    @Override
    protected Configuracao getEntityFromModel() {
        return configuracaoService.creatConfiguracaoFrom(this.getModel());
    }
}
