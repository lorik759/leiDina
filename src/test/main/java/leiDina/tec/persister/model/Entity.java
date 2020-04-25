package main.java.leiDina.tec.persister.model;

import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.annotations.Column;

/**
 * @author vitor.alves
 */
@main.java.leiDina.tec.persister.annotations.Entity
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
