package main.java.leiDina.tec.core.model;

import main.java.leiDina.tec.core.annotations.Column;
import main.java.leiDina.tec.core.annotations.Entity;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
@Entity
public class EntityToo implements Persistable {

    private Long id;

    private Integer integer;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "INTEGER")
    public void setInteger(Integer integer) {
        this.integer = integer;
    }
}
