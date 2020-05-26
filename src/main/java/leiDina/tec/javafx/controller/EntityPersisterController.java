package main.java.leiDina.tec.javafx.controller;

import javafx.fxml.FXML;
import javax.annotation.Resource;
import main.java.leiDina.tec.javafx.controller.BaseModelController;
import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.Persister;
import main.java.leiDina.tec.persister.dao.DAO;
import main.java.leiDina.tec.persister.factory.DAOFactory;

/**
 * A base controller for persistable entities.
 *
 * @author vitor.alves
 */
public abstract class EntityPersisterController<M> extends BaseModelController<M> {

    @Resource(name = "persister")
    private Persister persister;

    @Resource(name = "daoFactory")
    private DAOFactory daoFactory;

    protected abstract <E extends Persistable> E getEntityFromModel();

    @FXML
    public void save() {
        persister.save(this.getEntityFromModel());
    }

    public DAOFactory getDAOFactory() {
        return daoFactory;
    }
}
