package main.java.leiDina.tec.core.model;

import main.java.leiDina.tec.core.annotations.Column;
import main.java.leiDina.tec.core.persist.Persistable;

/**
 * @author vitor.alves
 */
@main.java.leiDina.tec.core.annotations.Entity
public class Entity implements Persistable {

    private Long id;

    private String nome;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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