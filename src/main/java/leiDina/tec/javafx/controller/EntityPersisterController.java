package main.java.leiDina.tec.javafx.controller;

import javafx.fxml.FXML;
import javax.annotation.Resource;
import main.java.leiDina.tec.core.persist.Persistable;
import main.java.leiDina.tec.core.persist.Persister;

/**
 * A base controller for persistable entities.
 *
 * @author vitor.alves
 */
public abstract class EntityPersisterController<M extends Persistable> extends BaseModelController<M> {

    @Resource(name = "persister")
    private Persister persister;

    @FXML
    public void save() {
        persister.save(this.getModel());
    }
}
