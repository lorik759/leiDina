package main.java.leiDina.biz.controllers.configuration;

import main.java.leiDina.tec.javafx.annotation.NumberInput;

/**
 * @author vitor.alves
 */
public class ConfiguracaoModel {

    private Double seconds;

    private Integer numeroPalavras;

    public Double getSeconds() {
        return seconds;
    }

    @NumberInput(type = Double.class)
    public void setSeconds(Double seconds) {
        this.seconds = seconds;
    }

    public Integer getNumeroPalavras() {
        return numeroPalavras;
    }

    @NumberInput(type = Integer.class)
    public void setNumeroPalavras(Integer numeroPalavras) {
        this.numeroPalavras = numeroPalavras;
    }
}
