package main.java.leiDina.biz.service;

import main.java.leiDina.biz.controllers.configuration.ConfiguracaoModel;
import main.java.leiDina.biz.dao.ConfiguracaoDAO;
import main.java.leiDina.biz.model.Configuracao;
import main.java.leiDina.tec.persister.exception.EntityNotFoundException;
import main.java.leiDina.tec.persister.factory.DAOFactory;

/**
 * @author vitor.alves
 */
public class ConfiguracaoService {

    private DAOFactory daoFactory;

    public Configuracao creatConfiguracaoFrom(ConfiguracaoModel model) {
        Configuracao entity = getConfiguracaoDAO().createEntity();
        // Nao e o ideal, mas como estamos trabalando com o textBasePersisetr, assim fica mais facil para garantir a unica entidade.
        entity.setId(1L);
        entity.setNumeroPalavras(model.getNumeroPalavras());
        entity.setSeconds(model.getSeconds());
        return entity;
    }

    public Configuracao findById(Long id) throws EntityNotFoundException {
        return this.getConfiguracaoDAO().findById(id);
    }

    public Configuracao findOrCreatConfiguracaoPadrao() {
        try {
            return this.findById(1L);
        } catch (EntityNotFoundException e) {
            return this.creatDefaultConfiguracao();
        }
    }

    public Configuracao creatDefaultConfiguracao() {
        Configuracao configuracao = getConfiguracaoDAO().createEntity();
        // Nao e o ideal, mas como estamos trabalhando com o textBasePersisetr, assim fica mais facil para garantir a única entidade.
        configuracao.setId(1L);
        configuracao.setSeconds(0.5);
        configuracao.setNumeroPalavras(1);
        return configuracao;
    }

    private ConfiguracaoDAO getConfiguracaoDAO() {
        return daoFactory.getDAOByClass(ConfiguracaoDAO.class);
    }

    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
