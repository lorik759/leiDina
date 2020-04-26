package main.java.leiDina.tec.persister;

import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.annotations.Column;
import main.java.leiDina.tec.persister.annotations.Entity;

/**
 * @author vitor.alves
 */
@Entity
public class EntityTwo implements Persistable {

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
