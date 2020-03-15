package main.java.leiDina.tec.javafx.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Node;

/**
 * A base structure of a Controller.
 *
 * @author vitor.alves
 */
public abstract class BaseController {

    private final Map<String, Node> components = new HashMap<>();

    public BaseController() {
    }

    public Node get(String key) {
        return components.get(key);
    }
}
