package main.java.leiDina.tec.javafx.controller;

/**
 * @author vitor.alves
 */
public abstract class BaseModelController<M> extends BaseController {

    private M model;

    public BaseModelController() {
        super();
        this.model = createModel();
    }

    protected abstract M createModel();

    public M getModel() {
        return model;
    }
}
