package main.java.leiDina.tec;

import main.java.leiDina.tec.javafx.annotation.TextField;

/**
 * @author vitor.alves
 */
public class Model {

    private String text;

    public String getText() {
        return text;
    }

    @TextField(id = "text")
    public void setText(String text) {
        this.text = text;
    }
}
