package main.java.leiDina.biz.domain;

import main.java.leiDina.tec.persister.Persistable;
import main.java.leiDina.tec.persister.annotations.Column;
import main.java.leiDina.tec.persister.annotations.Table;

/**
 * @author vitor.alves
 */
@Table(name = "Configuracao")
public class Configuracao implements Persistable {

    private Long id;

    private Double seconds;

    private Integer numeroPalavras;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSeconds() {
        return seconds;
    }

    @Column(name = "SEGUNDOS")
    public void setSeconds(Double seconds) {
        this.seconds = seconds;
    }

    public Integer getNumeroPalavras() {
        return numeroPalavras;
    }

    @Column(name = "NUM_PALA")
    public void setNumeroPalavras(Integer numeroPalavras) {
        this.numeroPalavras = numeroPalavras;
    }
}
