package main.java.leiDina.biz.dao;

import main.java.leiDina.biz.model.Configuracao;
import main.java.leiDina.tec.persister.Persister;
import main.java.leiDina.tec.persister.dao.BaseDAO;

/**
 * @author vitor.alves
 */
public class ConfiguracaoDAO extends BaseDAO<Configuracao> {

    @Override
    protected Class<Configuracao> getPersistableType() {
        return Configuracao.class;
    }
}
