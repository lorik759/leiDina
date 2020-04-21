package main.java.leiDina.biz.controllers.configuration;

import java.net.URL;
import java.util.ResourceBundle;
import main.java.leiDina.biz.dao.ConfiguracaoDAO;
import main.java.leiDina.biz.model.Configuracao;
import main.java.leiDina.tec.fxpersister.controller.EntityPersisterController;
import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.exception.EntityNotFoundException;

/**
 * @author vitor.alves
 */
public class ConfiguracaoController extends EntityPersisterController<ConfiguracaoModel> {

    private ConfiguracaoDAO configuracaoDAO;

    private Configuracao configuracao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        this.configuracaoDAO = this.getDAOFactory().getDAOByClass(ConfiguracaoDAO.class);
        try {
            this.configuracao = configuracaoDAO.findById(1L);
        } catch (EntityNotFoundException e) {
            this.configuracao = configuracaoDAO.createEntity();
            // Nao e o ideal, mas como estamos trabalando com o textBasePersisetr, assim fica mais facil para garantir a unica entidade.
            this.configuracao.setId(1L);
            this.configuracao.setSeconds(0.5);
            this.configuracao.setNumeroPalavras(1);
        }
        ConfiguracaoModel model = this.getModel();
        model.setNumeroPalavras(configuracao.getNumeroPalavras());
        model.setSeconds(configuracao.getSeconds());
    }

    @Override
    protected ConfiguracaoModel createModel() {
        return new ConfiguracaoModel();
    }

    @Override
    protected <E extends Persistable> E getEntityFromModel() {
        ConfiguracaoModel model = this.getModel();
        this.configuracao.setNumeroPalavras(model.getNumeroPalavras());
        this.configuracao.setSeconds(model.getSeconds());
        return (E) this.configuracao;
    }
}
