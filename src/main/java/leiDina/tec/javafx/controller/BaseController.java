package main.java.leiDina.tec.javafx.controller;

/**
 * A base structure of a Controller.
 *
 * @author vitor.alves
 */
public abstract class BaseController<M> {

    private M model;

    public BaseController() {
        super();
        this.model = createModel();
    }

    protected abstract M createModel();

    public M getModel() {
        return model;
    }
}
