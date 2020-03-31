package main.java.leiDina.biz.controllers.configuration;

import main.java.leiDina.tec.core.persist.Persistable;
import main.java.leiDina.tec.javafx.controller.EntityPersisterController;

/**
 * @author vitor.alves
 */
public class ConfiguracaoController extends EntityPersisterController<ConfiguracaoModel> {

    @Override
    protected ConfiguracaoModel createModel() {
        return new ConfiguracaoModel();
    }

    @Override
    protected <E extends Persistable> E getEntityFromModel() {
        return null;
    }
}
