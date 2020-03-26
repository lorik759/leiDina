package main.java.leiDina.biz.model;

import java.io.Serializable;
import main.java.leiDina.tec.core.annotations.Entity;
import main.java.leiDina.tec.core.annotations.ID;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
@Entity
public class Configuracao implements Persistable {

    private Serializable id;

    @ID
    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

}
