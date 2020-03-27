package main.java.leiDina.biz.model;

import main.java.leiDina.tec.core.annotations.Entity;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
@Entity
public class Configuracao implements Persistable {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
