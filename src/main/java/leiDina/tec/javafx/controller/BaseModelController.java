package main.java.leiDina.tec.javafx.controller;

/**
 * A base structure of a Controller that contains a model to associate with a scene.
 *
 * @author vitor.alves
 */
public abstract class BaseModelController<M> {

    private M model;

    public BaseModelController() {
        super();
    }

    public void initialize() {
        this.model = createModel();
    }

    protected abstract M createModel();

    public M getModel() {
        return model;
    }
}
