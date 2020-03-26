package main.java.leiDina.tec;


import main.java.leiDina.tec.javafx.annotation.TextInput;

/**
 * @author vitor.alves
 */
public class Model {

    private String text;

    public String getText() {
        return text;
    }

    @TextInput(id = "text")
    public void setText(String text) {
        this.text = text;
    }
}
