package main.java.leiDina.biz.controllers.leitura;

import main.java.leiDina.tec.javafx.annotation.TextInput;

/**
 * @author vitor.alves
 */
public class LeituraModel {

    private String textoEntrada;

    public String getTextoEntrada() {
        return textoEntrada;
    }

    @TextInput(id = "textoEntrada")
    public void setTextoEntrada(String textoEntrada) {
        this.textoEntrada = textoEntrada;
    }
}
