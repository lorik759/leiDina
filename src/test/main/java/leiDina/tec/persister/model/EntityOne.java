package main.java.leiDina.tec.persister.model;

import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.annotations.Column;
import main.java.leiDina.tec.persister.annotations.Entity;

/**
 * @author vitor.alves
 */
@Entity
public class EntityOne  implements Persistable {

    private Long id;

    private Integer integer;

    private String string;

    private Boolean aBoolean;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInteger() {
        return integer;
    }

    @Column(name = "INTEGER")
    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    @Column(name = "STRING")
    public void setString(String string) {
        this.string = string;
    }

    public Boolean isABoolean() {
        return aBoolean;
    }

    @Column(name = "BOOLEAN")
    public void setABoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

}
