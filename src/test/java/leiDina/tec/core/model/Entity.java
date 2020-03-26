package java.leiDina.tec.core.model;

import java.io.Serializable;
import main.java.leiDina.tec.core.annotations.Column;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
@main.java.leiDina.tec.core.annotations.Entity
public class Entity implements Persistable {

    private Serializable id;

    private String nome;

    @Override
    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    @Column(name = "NOME")
    public void setNome(String nome) {
        this.nome = nome;
    }
}
