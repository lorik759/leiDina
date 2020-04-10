package main.java.leiDina.tec.javafx.controller;

import javafx.fxml.FXML;
import javax.annotation.Resource;
import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.Persister;

/**
 * A base controller for persistable entities.
 *
 * @author vitor.alves
 */
public abstract class EntityPersisterController<M> extends BaseModelController<M> {

    @Resource(name = "persister")
    private Persister persister;

    protected abstract <E extends Persistable> E getEntityFromModel();

    @FXML
    public void save() {
        persister.save(this.getEntityFromModel());
    }
}
